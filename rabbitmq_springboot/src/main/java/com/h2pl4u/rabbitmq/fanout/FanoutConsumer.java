package com.h2pl4u.rabbitmq.fanout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ilovejava1314
 * @date 2020/8/11 22:24
 */
@Component

public class FanoutConsumer {
    //                            不@Queue指定队列名称则为临时队列         绑定交换机
    @RabbitListener(bindings = @QueueBinding(value = @Queue, exchange = @Exchange(value = "logs", type = "fanout")))
    public void receiver1(String massage) {
        System.out.println("消费者1:" + massage);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue, exchange = @Exchange(value = "logs", type = "fanout")))
    public void receiver2(String massage) {
        System.out.println("消费者2:" + massage);
    }
}
