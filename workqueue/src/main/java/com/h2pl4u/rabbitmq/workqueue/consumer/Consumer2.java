package com.h2pl4u.rabbitmq.workqueue.consumer;

import com.h2pl4u.rabbitmq.workqueue.config.RabbitMQConfig;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by Liuwei on 2020/8/10 16:17
 */
public class Consumer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQConfig.getConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1);
        channel.queueDeclare("work", true, false, false, null);
        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("消费者-2:" + new String(body));
                //参数1：确认队列中的具体消息 参数2：是否开启多个消息同时确认
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });

    }
}
