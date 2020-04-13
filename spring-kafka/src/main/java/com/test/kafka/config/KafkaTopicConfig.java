package com.test.kafka.config;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: liushoulong
 * @Date: 2020/4/9 20:09
 */
@Configuration
public class KafkaTopicConfig {

    /**
     * 创建一个kafka管理类，相当于rabbitMQ的管理类rabbitAdmin,
     * 没有此bean无法自定义的使用adminClient创建topic
     */
    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> props = new HashMap<>();
        //配置Kafka实例的连接地址                                                                //kafka的地址，不是zookeeper
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaEnv.getServers());
        KafkaAdmin admin = new KafkaAdmin(props);
        return admin;
    }

    //kafka客户端，在spring中创建这个bean之后可以注入并且创建topic
    @Bean
    public AdminClient adminClient() {
        return AdminClient.create(kafkaAdmin().getConfig());
    }

    //通过bean创建(bean的名字为)
    // 测试环境 2分区，备份2，
    // 正式环境 4分区
    @Bean
    public NewTopic initialTestTopic() {
        return new NewTopic("test-topic",4, (short) 1 );
    }

    //通过bean创建(bean的名字为)
    @Bean
    public NewTopic initialStartTopic() {
        return new NewTopic("start-topic",4, (short) 1 );
    }

    //通过bean创建(bean的名字为)
    @Bean
    public NewTopic initialEndTopic() {
        return new NewTopic("end-topic",4, (short) 1 );
    }

    @Bean
    public NewTopic initialTestHandlerTopic() {
        return new NewTopic("test-handler-topic",2, (short) 1 );
    }
}
