package com.test.kafka.consumer;

import com.test.kafka.entity.MessageKafka;
import com.test.kafka.thread.ThreadPoolExecutorSingleton;
import com.test.kafka.thread.ThreadPoolExecutorTestTopicConsumerOne;
import com.test.kafka.thread.ThreadPoolExecutorTestTopicConsumerTwo;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author lsl
 * @des test-topic主题消费者
 * 使用多个线程池，为了避免最大线程数上不去，始终等待核心线程数的处理
 * 又因为多消费者offset提交的限时，在争夺线程时可能耗费的时间累积超过offset最大限制时间。
 * 为了避免该问题，就将线程池资源划分开处理
 */
@Component
public class KafkaConsumer {

    private static Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    private ThreadPoolExecutor threadPoolExecutorOne = null;
    private ThreadPoolExecutor threadPoolExecutorTwo = null;
    @PostConstruct
    public void init(){
        threadPoolExecutorOne = ThreadPoolExecutorTestTopicConsumerOne.getInstance();
        threadPoolExecutorTwo = ThreadPoolExecutorTestTopicConsumerTwo.getInstance();
    }

    /**
     * 监听track_topic主题,有消息就读取
     * , Acknowledgment acknowledgment, Consumer consumer
     * @param records
     */
    @KafkaListener(topics = "${kafka.topic.id}",containerFactory = "kafkaListenerContainerFactory")
    public void receiveOneMessage(List<ConsumerRecord> records, Consumer consumer,Acknowledgment acknowledgment) {
        logger.info("One----ReceivingSize:{},当前consumer对象:{}", records.size(),consumer.toString());
        CountDownLatch latch = new CountDownLatch(records.size() < 100 ? records.size() : 100);
        for (ConsumerRecord record : records) {
            MessageKafka messageKafka = new MessageKafka();
            messageKafka.setTopic(record.topic());
            messageKafka.setContent(record.value());
            messageKafka.setExtendInfo(new HashMap<String, Object>() {{
                put("partition", String.valueOf(record.partition()));
                put("offset", String.valueOf(record.offset()));
            }});
            messageKafka.setCommitFlag(records.indexOf(record) == (records.size() - 1));
            try {
                // 如果排队队列 容量已满，那么就阻塞1秒钟
                if (threadPoolExecutorOne.getQueue().size() > 100) {
                    Thread.sleep(1000);
                }
                threadPoolExecutorOne.submit(() -> {
                    try{
                        //System.out.println("consume---->"+messageKafka);
                        logger.info("One----消费者--->{},当前线程:{}",messageKafka, Thread.currentThread().getName());
                        Thread.sleep(1 * 60 * 1000);
                    }catch (Exception e){
                        // 处理异常
                        e.printStackTrace();
                    }finally {
                        latch.countDown();
                        logger.info("One----当前线程:{},当前递减锁数量：{}",Thread.currentThread().getName(),latch.getCount());
                    }
                });

            } catch (RejectedExecutionException ree){
                // 避免死锁
                latch.countDown();
                ree.printStackTrace();
                logger.error("One---------RejectedExecutionException error---------------");
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("One---------Execute condition process error-----------------");
            }
        }
        try {
            latch.await();
            acknowledgment.acknowledge();//提交offset
            logger.info("One------offset提交");
        } catch (Exception e) {
            logger.error("One----Commit offset to kafka error");
            e.printStackTrace();
        }
    }


    /**
     * 监听track_topic主题,有消息就读取
     * , Acknowledgment acknowledgment, Consumer consumer
     * @param records
     */
    @KafkaListener(topics = "${kafka.topic.id}",containerFactory = "kafkaListenerContainerFactory")
    public void receiveTwoMessage(List<ConsumerRecord> records, Consumer consumer,Acknowledgment acknowledgment) {
        logger.info("Two----ReceivingSize:{},当前consumer对象:{}", records.size(),consumer.toString());
        CountDownLatch latch = new CountDownLatch(records.size() < 100 ? records.size() : 100);
        for (ConsumerRecord record : records) {
            MessageKafka messageKafka = new MessageKafka();
            messageKafka.setTopic(record.topic());
            messageKafka.setContent(record.value());
            messageKafka.setExtendInfo(new HashMap<String, Object>() {{
                put("partition", String.valueOf(record.partition()));
                put("offset", String.valueOf(record.offset()));
            }});
            messageKafka.setCommitFlag(records.indexOf(record) == (records.size() - 1));
            try {
                // 如果排队队列 容量已满，那么就阻塞1秒钟
                if (threadPoolExecutorTwo.getQueue().size() > 100) {
                    Thread.sleep(1000);
                }
                threadPoolExecutorTwo.submit(() -> {
                    try{
                        //System.out.println("consume---->"+messageKafka);
                        logger.info("Two----消费者--->{},当前线程:{}",messageKafka, Thread.currentThread().getName());
                        Thread.sleep(5 * 60 * 1000);
                    }catch (Exception e){
                        // 处理异常
                        e.printStackTrace();
                    }finally {
                        latch.countDown();
                        logger.info("Two----当前线程:{},当前递减锁数量：{}",Thread.currentThread().getName(),latch.getCount());
                    }
                });

            } catch (RejectedExecutionException ree){
                // 避免死锁
                latch.countDown();
                ree.printStackTrace();
                logger.error("Two---------RejectedExecutionException error---------------");
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Two---------Execute condition process error-----------------");
            }
        }
        try {
            latch.await();
            acknowledgment.acknowledge();//提交offset
            logger.info("Two------offset提交");
        } catch (Exception e) {
            logger.error("Two----Commit offset to kafka error");
            e.printStackTrace();
        }
    }
}
