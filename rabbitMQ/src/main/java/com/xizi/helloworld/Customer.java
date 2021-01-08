package com.xizi.helloworld;

import com.rabbitmq.client.*;
import com.xizi.utils.RabbitUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Customer {

    public static void main(String[] args) throws IOException, TimeoutException {
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


            //消费消息
            //参数1: 消费哪个队列的消息名称
            //参数2： 开始消息的自动确认直接
            //参数3: 消费时的回调接口
            channel.basicConsume("hello",true,new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println("========================"+new String(body));
                }
            });

            //关闭通道,连接
//        channel.close();
//        connection.close();
    }




}
