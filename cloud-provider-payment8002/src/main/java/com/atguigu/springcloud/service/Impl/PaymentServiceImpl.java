package com.atguigu.springcloud.service.Impl;

import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment){
        int i = paymentDao.create(payment);
         return  i;
    }
    public Payment getPaymentById(Long id){
        Payment payment = paymentDao.getPaymentById(id);
        return  payment;
    }
}
