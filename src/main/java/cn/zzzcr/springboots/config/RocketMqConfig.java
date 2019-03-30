package cn.zzzcr.springboots.config;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RocketMqConfig {


    @Value("${apache.rocketmq.consumer.PushConsumer}")
    private String producerGroup;

    @Value("${apache.rocketmq.namesrvAddr}")
    private String nameSrvAddr;


    @Bean
    public DefaultMQProducer defaultMQProducer(){
        //指定NameServer地址，多个地址以 ; 隔开
        //如 producer.setNamesrvAddr("192.168.100.141:9876;192.168.100.142:9876;192.168.100.149:9876");
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer(producerGroup);
        defaultMQProducer.setNamesrvAddr(nameSrvAddr);
        defaultMQProducer.setVipChannelEnabled(false);

        try {
            /**
             * Producer对象在使用之前必须要调用start初始化，只能初始化一次
             */
            defaultMQProducer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        // producer.shutdown();  一般在应用上下文，关闭的时候进行关闭，用上下文监听器
        return defaultMQProducer;
    }


    @Bean
    public DefaultMQPushConsumer defaultMQPushConsumer(){
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer(producerGroup);
        defaultMQPushConsumer.setNamesrvAddr(nameSrvAddr);

        try {
            defaultMQPushConsumer.subscribe("testTopic","*");

            //CONSUME_FROM_LAST_OFFSET 默认策略，从该队列最尾开始消费，跳过历史消息
            //CONSUME_FROM_FIRST_OFFSET 从队列最开始开始消费，即历史消息（还储存在broker的）全部消费一遍
            defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        } catch (MQClientException e) {
            e.printStackTrace();
        }

        return defaultMQPushConsumer;
    }

}
