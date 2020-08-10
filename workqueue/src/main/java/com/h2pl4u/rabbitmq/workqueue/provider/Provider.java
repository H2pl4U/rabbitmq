package com.h2pl4u.rabbitmq.workqueue.provider;

import com.h2pl4u.rabbitmq.workqueue.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * Created by Liuwei on 2020/8/10 16:11
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQConfig.getConnection();
        Channel channel = connection.createChannel();
        //通过通道声明队列
        channel.queueDeclare("work", true, false, false, null);
        for (int i = 0; i < 20; i++) {
            //生产消息
            channel.basicPublish("", "work", null, (i + "hello work queue").getBytes());
        }
        //关闭资源
        RabbitMQConfig.closeConnectionAndChannel(channel, connection);
    }
}
