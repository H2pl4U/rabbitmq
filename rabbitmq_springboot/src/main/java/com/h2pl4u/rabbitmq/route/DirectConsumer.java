package com.h2pl4u.rabbitmq.route;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ilovejava1314
 * @date 2020/8/11 22:34
 */
@Component
public class DirectConsumer {
    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,
                    exchange = @Exchange(value = "directs", type = "direct"),
                    key = {"error", "info", "warning"}
            )
    })
    public void receiver1(String massage) {
        System.out.println("消费者1：" + massage);
    }

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,
                    exchange = @Exchange(value = "directs", type = "direct"),
                    key = {"error"}
            )
    })
    public void receiver2(String massage) {
        System.out.println("消费者2：" + massage);
    }
}
