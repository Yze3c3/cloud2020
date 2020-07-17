package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements  PaymentHystrixService{
    @Override
    public String paymentinfo_Ok(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_OK,ToT";
    }

    @Override
    public String paymentinfo_TimeOut(Integer id) {
        return "-----PaymentFallbackService fall back-back_paymentInfo_TimeOut,ToT";
    }
}
