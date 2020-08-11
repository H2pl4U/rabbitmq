package com.h2pl4u.rabbitmq.routing.config;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by Liuwei on 2020/8/10 16:51
 */
public class RabbitMQConfig {
    private static ConnectionFactory connectionFactory;

    static {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems1");
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("root");
    }

    /**
     * 获取connect连接
     * @return
     */
    public static Connection getConnection() {
        try {
            return connectionFactory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭连接
     * @param channel
     * @param connection
     */
    public static void closeConnectionAndChannel(Channel channel, Connection connection) {
        try {
            if (channel != null)
                channel.close();
            if (connection != null)
                connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
