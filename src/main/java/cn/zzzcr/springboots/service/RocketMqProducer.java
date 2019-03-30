package cn.zzzcr.springboots.service;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RocketMqProducer {


    @Autowired
    private DefaultMQProducer defaultMQProducer;



    public void sendMsg(String msg, String tag) {
        Message testTopic;
        SendResult sendRes = null;
        try {
            testTopic = new Message("testTopic", tag, msg.getBytes(RemotingHelper.DEFAULT_CHARSET));
            sendRes = defaultMQProducer.send(testTopic);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("发送响应：MsgId:" + sendRes.getMsgId() + "，发送状态:" + sendRes.getSendStatus());
    }

}
