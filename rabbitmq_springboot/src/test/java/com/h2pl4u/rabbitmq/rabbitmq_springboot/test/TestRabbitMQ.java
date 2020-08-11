package com.h2pl4u.rabbitmq.rabbitmq_springboot.test;

import com.h2pl4u.rabbitmq.RabbitmqSpringbootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Liuwei on 2020/8/11 18:40
 */
@SpringBootTest(classes = RabbitmqSpringbootApplication.class)
@RunWith(SpringRunner.class)
public class TestRabbitMQ {

    //注入rabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //route 路由模式 topic订阅模式
    @Test
    public void testRouteTopic() {
        rabbitTemplate.convertAndSend("topics","user.save.findAll","user.save的路由消息");
    }

    //route 路由模式 direct直连
    @Test
    public void testRouteDirect() {
        rabbitTemplate.convertAndSend("directs", "info", "发送info的key的路由消息");
    }

    //fanout 广播
    @Test
    public void testFanout() {
        rabbitTemplate.convertAndSend("logs", "","Fanout模型发送的消息");
    }

    //work
    @Test
    public void testWork() {
        //默认公平调度轮询分配
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work", "work 模型");
        }
    }

    //hello world
    @Test
    public void testHello() {
        rabbitTemplate.convertAndSend("hello", "hello world");
    }
}
