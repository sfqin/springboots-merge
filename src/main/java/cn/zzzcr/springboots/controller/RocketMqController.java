package cn.zzzcr.springboots.controller;

import cn.zzzcr.springboots.service.RocketMqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rocketmq")
public class RocketMqController {

    @Autowired
    private RocketMqProducer rocketMqProducer;

    @GetMapping("send")
    private void sendMsg(String msg, String tag){
        rocketMqProducer.sendMsg(msg,tag);
    }

}
