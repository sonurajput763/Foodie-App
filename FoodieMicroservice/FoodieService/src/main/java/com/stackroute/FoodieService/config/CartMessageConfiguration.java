package com.stackroute.FoodieService.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CartMessageConfiguration {
    private String exchangeName="cart_exchange";
    private String registerQueue="cart_queue";



    @Bean
    public Queue registerQueue1()
    {
        return new Queue(registerQueue);
    }

    @Bean
    public Jackson2JsonMessageConverter producerConverter1()
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate1(final ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemplate= new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerConverter1());
        return rabbitTemplate;
    }


}
