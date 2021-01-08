package com.xizi;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootRabbitmqApplicationTests {

    //注入rabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;


    //topic 动态订阅 订阅模式
    @Test
    public void testTopic(){
        rabbitTemplate.convertAndSend("topic","user.save","topic订阅模式");
    }

    //路由模式 route
    @Test
    public void testRoute(){
        rabbitTemplate.convertAndSend("direct","info","发送info的路由信息");
    }
    //fanout 关播
    @Test
    void testfanout() {
        rabbitTemplate.convertAndSend("logs_fanout","","fanout模型发送的消息");
    }

    //公平消费 work
    @Test
    void testWork() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work","work模型"+i);
        }
    }

    @Test
    void testhello() {
        rabbitTemplate.convertAndSend("hello","hello world");
    }



}
