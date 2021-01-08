package com.xizi.helloworld;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
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
        //参数1:队列名称
        //参数2：队列是否要持久化
        //参数3：是否独占队列
        //参数4： 是否在消费完成之后自动删除队列
        //参数5:额外参数
        channel.queueDeclare("hello",true,false,false,null );

        //发布消息
        //参数1:交换机名称
        //参数2：队列名称
        //参数3:传递消息额外设置
        //参数4:消息的具体内容
        channel.basicPublish("", "hello", MessageProperties.PERSISTENT_TEXT_PLAIN, "hello rabbitmq".getBytes());
        //关闭通道,连接
        RabbitUtils.ColseConnectionAndChanel(connection,channel );
    }

}
