package com.h2pl4u.rabbitmq.singlequeue.provider;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Liuwei on 2020/8/5 10:28
 */
public class Send {

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接mq的连接工厂对象
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置连接rabbitmq主机
        connectionFactory.setHost("127.0.0.1");
        //设置端口号
        connectionFactory.setPort(5672);
        //设置虚拟主机
        connectionFactory.setVirtualHost("/ems1");
        //用户名密码
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("root");
        //获取连接对象
        Connection connection = connectionFactory.newConnection();
        //获取连接中通道
        Channel channel = connection.createChannel();
        //通道绑定对应消息队列
        //参数1：队列名称 若不存在则自动创建
        //参数2：用来定义队列特性是否要持久化 队列不会丢失但是消息会丢失
        //参数3：exclusive 是否独占队列
        //参数4：autoDelete 是否再消费完成后自动删除队列
        //参数5：额外附加参数
        channel.queueDeclare("hello",false,false,false,null);
        //发布消息
        //参数1：交换机名称
        //参数2：队列名称
        //参数3：参数消息额外设置  MessageProperties.PERSISTENT_TEXT_PLAIN:持久化队列消息
        //参数4：消息具体内容的二进制编码
        channel.basicPublish("","hello", MessageProperties.PERSISTENT_TEXT_PLAIN,"helloworld1".getBytes());

        channel.close();
        connection.close();
    }
}
