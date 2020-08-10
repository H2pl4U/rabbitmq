package com.h2pl4u.rabbitmq.exchange.provider;

import com.h2pl4u.rabbitmq.exchange.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * Created by Liuwei on 2020/8/10 16:53
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMQConfig.getConnection();
        Channel channel = connection.createChannel();

        //将通道声明指定的交换机
        channel.exchangeDeclare("")
    }
}
