package com.xizi.workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xizi.utils.RabbitUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Provider {
    //生成消息
    @Test
    public void testSendMessage() throws IOException, TimeoutException {

        //获取连接对象
        Connection connection = RabbitUtils.getConnection();
        //获取连接的通道
        Channel channel = connection.createChannel();
        //通道绑定对应消息队列
        channel.queueDeclare("work",true,false,false,null );

        //发布消息
        for (int i = 1; i <= 20; i++) {
            channel.basicPublish("", "work", null, (i+"hello workqueuq").getBytes());
        }
        //关闭通道,连接
        RabbitUtils.ColseConnectionAndChanel(connection,channel );
    }
}
