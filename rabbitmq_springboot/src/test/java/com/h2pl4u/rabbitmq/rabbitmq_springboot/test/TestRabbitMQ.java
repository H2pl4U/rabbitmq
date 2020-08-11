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

    @Test
    public void testWork() {
        rabbitTemplate.convertAndSend("work", "work 模型");
    }

    //hello world
    @Test
    public void testHello() {
        rabbitTemplate.convertAndSend("hello", "hello world");
    }
}
