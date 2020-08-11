package com.h2pl4u.rabbitmq.routing.topics.provider;

import com.h2pl4u.rabbitmq.routing.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * Routing的订阅模型Topic  动态路由
 * 本质上与Direct模型相比多了通配符
 * *：匹配一个单词  #：匹配任意多个单词
 * Created by Liuwei on 2020/8/11 16:30
 */
public class Provider4RoutingTopic {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQConfig.getConnection();
        Channel channel = connection.createChannel();
        String exchangeName = "topics";

        channel.exchangeDeclare(exchangeName, "topic");
        //发布消息
        String routekey = "user.save.findAll";
        channel.basicPublish(exchangeName, routekey, null, ("topic动态路由模型：[" + routekey + "]发送了消息").getBytes());
        RabbitMQConfig.closeConnectionAndChannel(channel, connection);
    }
}
