package com.h2pl4u.rabbitmq.routing.direct.provider;

import com.h2pl4u.rabbitmq.routing.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * Routing的订阅模型-Direct直连
 * -不同的消息被不同的队列所消费
 * -队列于交换机绑定，需要指定一个RoutingKey
 * -消息的发送方在向交换机发送消息时也必须指定消息的RoutingKey
 * -交换机不再把消息交给每个绑定的队列，而是根据消息的RoutingKey进行判断，只有队列的RoutingKey和消息的RoutingKey完全一致才会收到消息流程
 * Created by Liuwei on 2020/8/11 13:53
 */
public class Provider4RoutingDierct {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQConfig.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机 参数1：交换机名称  参数2：交换机类型 基于指令的RoutingKey转发 路由模式
        channel.exchangeDeclare("logs_direct", "direct");
        String routingkey = "warning";
        //发布消息 参数1：交换机名称 参数2：路由key
        channel.basicPublish("logs_direct", routingkey, null, ("Direct模型发布基于RoutingKey:[" + routingkey + "]发送的消息").getBytes());
        //关闭资源
        RabbitMQConfig.closeConnectionAndChannel(channel, connection);
    }
}
