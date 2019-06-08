package com.springboot.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqTopicConfig {

	//ֻ��һ��topic
    final static String message = "topic.message";
    //���ն��topic
    final static String messages = "topic.messages";
    
    @Bean
    public Queue queueMessage() {
        return new Queue(RabbitMqTopicConfig.message);
    }
 
    @Bean
    public Queue queueMessages() {
        return new Queue(RabbitMqTopicConfig.messages);
    }
 
    @Bean
    TopicExchange exchange() {
        return new TopicExchange("exchange");
    }
 
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }
 
    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        //�����#��ʾ��������ʡ�
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }

}
