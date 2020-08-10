package com.h2pl4u.rabbitmq.singlequeue.provider;

import com.h2pl4u.rabbitmq.singlequeue.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Liuwei on 2020/8/10 14:15
 */
public class SendTest {

    @Test
    public void sendTest() throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("rabbitqueue",true,false,true,null);
        channel.basicPublish("","rabbitqueue",null,"helloworldzzz".getBytes());

        channel.close();
        connection.close();
    }
}
