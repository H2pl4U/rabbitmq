package com.h2pl4u.rabbitmq.exchange.consumer;

import com.h2pl4u.rabbitmq.exchange.config.RabbitMQConfig;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by Liuwei on 2020/8/11 13:39
 */
public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQConfig.getConnection();
        Channel channel = connection.createChannel();
        //通道绑定交换机 也可声明
        channel.exchangeDeclare("logs", "fanout");
        //临时队列
        String queue = channel.queueDeclare().getQueue();
        //绑定交换机和队列
        channel.queueBind(queue, "logs", "");
        //消费消息
        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1：" + new String(body));
            }
        });
    }
}
