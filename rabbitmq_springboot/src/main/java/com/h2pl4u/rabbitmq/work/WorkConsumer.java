package com.h2pl4u.rabbitmq.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Liuwei on 2020/8/11 19:03
 */
@Component
public class WorkConsumer {

    //消费者1
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receive1(String massage) {
        System.out.println("message1 = " + massage);
    }

    //消费者2
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receive2(String massage) {
        System.out.println("message2 = " + massage);
    }
}
