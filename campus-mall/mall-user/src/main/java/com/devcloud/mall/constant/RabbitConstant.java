package com.devcloud.mall.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 吴员外
 * @date 2022/11/26 16:47
 */
@ConfigurationProperties(prefix = "rabbit")
@Component
public class RabbitConstant {

    public static String DELAYED_EXCHANGE;

    public static String DELAYED_QUEUE;

    public static String DELAYED_ROUTING_KEY;

    public static String DEAD_LETTER_QUEUE;

    public static String DEAD_LETTER_EXCHANGE;

    public static String DEAD_LETTER_ROUTING_KEY;

    public void setDelayedExchange(String delayedExchange) {
        RabbitConstant.DELAYED_EXCHANGE = delayedExchange;
    }

    public void setDelayedQueue(String delayedQueue) {
        RabbitConstant.DELAYED_QUEUE = delayedQueue;
    }

    public void setDelayedRoutingKey(String delayedRoutingKey) {
        RabbitConstant.DELAYED_ROUTING_KEY = delayedRoutingKey;
    }

    public void setDeadLetterQueue(String deadLetterQueue) {
        RabbitConstant.DEAD_LETTER_QUEUE = deadLetterQueue;
    }

    public void setDeadLetterExchange(String deadLetterExchange) {
        RabbitConstant.DEAD_LETTER_EXCHANGE = deadLetterExchange;
    }

    public void setDeadLetterRoutingKey(String deadLetterRoutingKey) {
        RabbitConstant.DEAD_LETTER_ROUTING_KEY = deadLetterRoutingKey;
    }
}
