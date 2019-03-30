package cn.zzzcr.springboots.controller;

import cn.zzzcr.springboots.service.ActiveMqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activemq")
public class ActiveMqController {

    @Autowired
    private ActiveMqProducer activeMqProducer;

    @GetMapping("send")
    public void sendMsg(String msg){
        activeMqProducer.sendMsg(msg);
    }


    @GetMapping("sendTop")
    public void sendTopicMsg(String msg){
        activeMqProducer.publish(msg);
    }

}
