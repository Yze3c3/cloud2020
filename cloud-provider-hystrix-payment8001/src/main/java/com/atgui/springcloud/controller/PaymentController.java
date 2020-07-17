package com.atgui.springcloud.controller;

import cn.hutool.log.Log;
import com.atgui.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String  paymentinfo_Ok(@PathVariable("id") Integer id){
        String result=paymentService.paymentinfo_Ok(id);
       return  result;
    }
    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String  paymentinfo_TimeOut(@PathVariable("id") Integer id){
        String result=paymentService.paymentinfo_TimeOut(id);
        return  result;
    }

    //服务熔断
    @GetMapping(value = "/payment/circuit/{id}")
    public String  paymentCircuitBreaker(@PathVariable("id") Integer id){
        String result=paymentService.paymentCircuitBreaker(id);
        return  result;
    }
}
