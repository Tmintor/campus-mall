package com.devcloud.mall;

import com.devcloud.mall.constant.RabbitConstant;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 吴员外
 * @date 2022/11/27 22:34
 */
@SpringBootTest
public class TestApplication {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test() {
        System.out.println(RabbitConstant.DELAYED_ROUTING_KEY);
        rabbitTemplate.convertAndSend(RabbitConstant.DELAYED_EXCHANGE,RabbitConstant.DELAYED_ROUTING_KEY,"eee");
    }

}
