package com.h2pl4u.rabbitmq.routing.direct.consumer;

import com.h2pl4u.rabbitmq.routing.config.RabbitMQConfig;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by Liuwei on 2020/8/11 13:52
 */
public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQConfig.getConnection();
        Channel channel = connection.createChannel();

        String exchangeName = "logs_direct";
        //通道声明交换机以及交换的类型
        channel.exchangeDeclare(exchangeName, "direct");
        //创建要给临时队列
        String queue = channel.queueDeclare().getQueue();
        //基于routekey绑定队列和交换机
        channel.queueBind(queue, exchangeName, "error");

        //获取消费的消息
        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1：" + new String(body));
            }
        });
    }
}
