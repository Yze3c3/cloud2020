package com.atiguigu.springcloud.service.impl;

import com.atiguigu.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

/**
 * 指信道channel和exchange绑定在一起
 */

@EnableBinding(Source.class)  //定义消息的推送管道
public class MessageProviderImpl implements IMessageProvider {

    @Autowired
    private MessageChannel output; //消息发送管道

    @Override
    public String send() {
        String serial=UUID.randomUUID().toString();
        this.output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("*****serial: "+serial);
        return null;
    }
}
