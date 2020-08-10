package com.h2pl4u.rabbitmq.workqueue.consumer;

import com.h2pl4u.rabbitmq.workqueue.config.RabbitMQConfig;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by Liuwei on 2020/8/10 16:15
 */
public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQConfig.getConnection();
        Channel channel = connection.createChannel();
        //每次只消费一个消息
        channel.basicQos(1);
        channel.queueDeclare("work", true, false, false, null);
        //参数1：队列名称  参数2：消息自动确认  消费者自动向rabbitmq确认消息消费
        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-1:" + new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });

    }
}
