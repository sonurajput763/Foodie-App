package com.stackroute.FoodieService.config;

import com.stackroute.FoodieService.DTODomain.CartDTO;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartProducer {
    private RabbitTemplate rabbitTemplate;
    private DirectExchange exchange;

    @Autowired
    public CartProducer(RabbitTemplate rabbitTemplate, DirectExchange exchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
    }

    public void sendMessageTORabbitMQ(CartDTO cartDTO)
    {
        rabbitTemplate.convertAndSend(exchange.getName(),"cart_routing",cartDTO);
    }
}
