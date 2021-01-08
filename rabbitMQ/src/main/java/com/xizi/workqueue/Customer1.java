package com.xizi.workqueue;

import com.rabbitmq.client.*;
import com.xizi.utils.RabbitUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Customer1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接对象
        Connection connection = RabbitUtils.getConnection();


        //获取连接的通道
        final Channel channel = connection.createChannel();
        //通道绑定对应消息队列
        //参数1:队列名称
        //参数2：队列是否要持久化
        //参数3：是否独占队列
        //参数4： 是否在消费完成之后自动删除队列
        //参数5:额外参数
        channel.queueDeclare("work",true,false,false,null );
        channel.basicQos(1); //每次通道里只存放一个消息
        //消费消息
        //参数1: 消费哪个队列的消息名称
        //参数2： 开始消息的自动确认直接
        //参数3: 消费时的回调接口
        channel.basicConsume("work",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者-2=="+new String(body));
                //参数1： 确认队列中那个具体消息
                //参数2： 是否开启多个消息同时确认
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });

        //关闭通道,连接
//        channel.close();
//        connection.close();
    }

}
