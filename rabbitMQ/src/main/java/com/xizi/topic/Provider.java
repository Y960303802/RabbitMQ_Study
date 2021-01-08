package com.xizi.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xizi.utils.RabbitUtils;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("topics", "topic");

        //发布消息
        String routekey="user";

        channel.basicPublish("topics", routekey, null, ("这里是动态路由模型，routekey:["+routekey+"]").getBytes());

        RabbitUtils.ColseConnectionAndChanel(connection, channel);
    }
}
