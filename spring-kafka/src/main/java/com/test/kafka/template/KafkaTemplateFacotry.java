package com.test.kafka.template;

import com.test.kafka.config.KafkaEnv;
import org.apache.kafka.clients.producer.Producer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lsl
 */
public class KafkaTemplateFacotry {

    private static final Logger logger = LogManager.getLogger(KafkaTemplateFacotry.class);

    private static KafkaTemplate<String, String> kafkaTemplate;

    private static KafkaTemplate<String, String> getInstance() {

        if (null == kafkaTemplate) {
            synchronized (KafkaTemplateFacotry.class){
                if (null == kafkaTemplate) {
                    try {
                        Map<String, Object> props = new HashMap<>();
                        props.put("bootstrap.servers", KafkaEnv.getServers());
                        // 消息发送最大尝试次数
                        props.put("retries", "3");
                        /** 0表示producer无需等待leader的确认，
                            1代表需要leader确认写入它的本地log并立即确认，
                           -1代表所有的备份都完成后确认
                         **/
                        props.put("acks", "0");
                        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
                        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
                        // 增加发送延迟
                        props.put("linger.ms", "5");
                        ProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(props);
                        kafkaTemplate = new KafkaTemplate<String, String>(producerFactory);
                    } catch (Exception e) {
                        System.out.println(e.toString());
                        logger.error("初始化kafka生产者错误！");
                    }
                }
            }
        }
        return kafkaTemplate;
    }

    /**
     * 发送消息
     * @param topic
     * @param message
     */
    public static void send(String topic, String message) {
        KafkaTemplate<String, String> kafkaTemplate = getInstance();
        if (kafkaTemplate != null) {
            kafkaTemplate.send(topic, message);
            logger.info("生产者发送的消息:{}",message.toString());

        }
    }
}
