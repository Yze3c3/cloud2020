package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
public class OrderZKController {
    public static final String INVOKE_URL="http://cloud-provider-payment";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(INVOKE_URL+"/payment/create",payment,CommonResult.class,payment);
    }
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment>getPayment(@PathVariable("id")Long id){
        return  restTemplate.getForObject(INVOKE_URL+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/zk")
    public String getPaymentInfo(){
        return  restTemplate.getForObject(INVOKE_URL+"/payment/zk",String.class);
    }
}
