package com.h2pl4u.rabbitmq.routing.topics.consumer;

import com.h2pl4u.rabbitmq.routing.config.RabbitMQConfig;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 *
 * Created by Liuwei on 2020/8/11 16:54
 */
public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQConfig.getConnection();
        Channel channel = connection.createChannel();

        String exchangeName = "topics";
        channel.exchangeDeclare(exchangeName, "topic");
        String queue = channel.queueDeclare().getQueue();
        //绑定队列和交换机  动态通配符形式
        channel.queueBind(queue,exchangeName,"user.*");
        //消费消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1："+new String(body));
            }
        });

    }
}
