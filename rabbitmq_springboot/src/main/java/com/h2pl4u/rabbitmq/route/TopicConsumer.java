package com.h2pl4u.rabbitmq.route;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ilovejava1314
 * @date 2020/8/11 22:43
 */
@Component
public class TopicConsumer {
    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,
                    exchange = @Exchange(value = "topics", type = "topic"),
                    key = {"user.save","user.*"})
    })
    public void receiver1(String massage) {
        System.out.println("消费者1：" + massage);
    }

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,
                    exchange = @Exchange(value = "topics", type = "topic"),
                    key = {"user.#"})
    })
    public void receiver2(String massage) {
        System.out.println("消费者2：" + massage);
    }
}
