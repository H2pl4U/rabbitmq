package com.h2pl4u.rabbitmq.singlequeue.consumer;

import com.h2pl4u.rabbitmq.singlequeue.utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by Liuwei on 2020/8/5 11:01
 */
public class Recv {
    private final static String QUEUE_NAME = "rabbitqueue";

    public static void main(String[] argv) throws Exception {
        Connection connection = RabbitMQUtils.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //通道绑定对象
        channel.queueDeclare(QUEUE_NAME, true, false, true, null);
        //消费消息
        //参数1：消费队列名称
        //参数2：开启消息的自动确认机制
        //参数3：消费时的回调函数
        channel.basicConsume(QUEUE_NAME, true, new DefaultConsumer(channel) {
            /**
             * body 消息队列中取出的消息
             * @param consumerTag
             * @param envelope
             * @param properties
             * @param body
             * @throws IOException
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("new String {body} = " + new String(body));
            }
        });
//        channel.close();
//        connection.close();
//        RabbitMQUtils.closeConnectionAndChannel(channel, connection);
    }
}
