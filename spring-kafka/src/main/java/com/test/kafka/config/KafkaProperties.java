package com.test.kafka.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;


@Configuration
@EnableKafka
public class KafkaProperties implements InitializingBean {

    @Value("${kafka.producer.servers}")
    private String servers;
    @Value("${kafka.enable}")
    private String enable;
    /**
     * 设置激活的配置文件，区分不同环境
     */
    @Value("${kafka.profile}")
    private String profile;

    @Override
    public void afterPropertiesSet() throws Exception
    {
        KafkaEnv.setServers(servers);
        KafkaEnv.setEnabled("Y".equals(enable) ? true : false);
        KafkaEnv.setProfile(profile);
    }

}