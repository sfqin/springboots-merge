package cn.zzzcr.springboots.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;

@Component
public class ActiveMqProducer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Topic topic;

    @Autowired
    private Queue queue;


    public void sendMsg(String message){
        System.out.println("msg => " + message);
        jmsMessagingTemplate.convertAndSend(queue,message);
    }


    public void publish(String msg){
        jmsMessagingTemplate.convertAndSend(topic,msg);
    }

}
