package com.h2pl4u.rabbitmq.hello;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Liuwei on 2020/8/11 18:44
 */
@Component                                          //queue 默认持久化 非独占 非自动删除
@RabbitListener(queuesToDeclare = @Queue(value = "hello",durable = "true",autoDelete = "false"))
public class HelloConsumer {

    @RabbitHandler
    public void receive(String message) {
        System.out.println("message = " + message);
    }

}
