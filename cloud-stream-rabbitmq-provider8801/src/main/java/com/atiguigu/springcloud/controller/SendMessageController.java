package com.atiguigu.springcloud.controller;


import com.atiguigu.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {

    @Autowired
    private IMessageProvider messageProvider;

    @GetMapping("/sendMessage")
    public String getMessage(){
        String str = messageProvider.send();
        return str;
    }

}
