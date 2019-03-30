package cn.zzzcr.springboots.service;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;

@Component
public class RocketMqComsumer {

    @Autowired
    private DefaultMQPushConsumer defaultMQPushConsumer;



    @PostConstruct
    public void comsumerMsg(){

        try {

            defaultMQPushConsumer.registerMessageListener((MessageListenerConcurrently) (list, context) -> {
                for (MessageExt messageExt: list){

                    try {
                        System.out.println("messageExt: " + messageExt);
                        String messageBody = new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);
                        System.out.println("消费响应：msgId : " + messageExt.getMsgId() + ",  msgBody : " + messageBody);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });

            defaultMQPushConsumer.start();

        } catch (MQClientException e) {
            e.printStackTrace();
        }

    }

}
