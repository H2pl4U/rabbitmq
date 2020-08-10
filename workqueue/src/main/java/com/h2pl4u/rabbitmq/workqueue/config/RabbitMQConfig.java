package com.h2pl4u.rabbitmq.workqueue.config;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Properties;

/**
 * Created by Liuwei on 2020/8/10 16:07
 */
public class RabbitMQConfig {
    private static ConnectionFactory connectionFactory;
    private static Properties properties;

    static {
        connectionFactory = new ConnectionFactory();
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
