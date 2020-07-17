package com.atgui.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentinfo_Ok(Integer id){
       return "线程池：   "+Thread.currentThread().getName()+" paymentinfo_Ok,id:  "+id+"\t"+"运行正常";
    }

    @HystrixCommand(fallbackMethod = "paymentinfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentinfo_TimeOut(Integer id){

    int timeNumber=3;
    try {
        TimeUnit.SECONDS.sleep(timeNumber);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    /*int age =10 / 0;*/
        return "线程池：   "+Thread.currentThread().getName()+" paymentinfo_TimeOut,id:  "+id+"\t"+"耗时"+timeNumber+"(秒）:正常 ";
    }

    public String paymentinfo_TimeOutHandler(Integer id){
        return "线程池：   "+Thread.currentThread().getName()+" 8001系统繁忙或者运行报错，请稍后再试:  "+id+"\t"+"使用降级处理TimeOutHandler";
    }



    // 服务熔断
@HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),              //是否开启断路器
@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //请求次数,请求数达到后才计算
@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //休眠时间窗
@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),  //错误率达到多少跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if(id < 0){
            throw  new RuntimeException("****id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return  Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能为负数,请稍后再试， o(╥﹏╥)o id: " + id;
    }
}
