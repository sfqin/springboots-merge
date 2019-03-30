package cn.zzzcr.springboots.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqComsumer {


    @JmsListener(destination = "comment.queue")
    private void receiveMsg(String msg){
        System.out.println("我接收到消息了 ==> " + msg);
    }


    @JmsListener(destination = "news.topic")
    private void receivetMsg1(String msg){
        System.out.println("receivetMsg1接收到topic消息了 ==> " + msg);
    }


    @JmsListener(destination = "news.topic")
    private void receivetMsg2(String msg){
        System.out.println("receivetMsg2接收到topic消息了 ==> " + msg);
    }


    @JmsListener(destination = "news.topic")
    private void receivetMsg3(String msg){
        System.out.println("receivetMsg3接收到topic消息了 ==> " + msg);
    }

}
