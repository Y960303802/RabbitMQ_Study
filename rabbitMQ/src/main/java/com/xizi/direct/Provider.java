package com.xizi.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xizi.utils.RabbitUtils;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        //通道声明交换机
        //参数1：交换机名称
        //参数2: direct 路由模式
        channel.exchangeDeclare("logs_direct", "direct");

        //发送消息
        String routingkey="error";
        channel.basicPublish("logs_direct", routingkey, null, ("这是direct模型发布的基于route key:["+routingkey+"]发送消息").getBytes());

        RabbitUtils.ColseConnectionAndChanel(connection, channel);

    }
}
