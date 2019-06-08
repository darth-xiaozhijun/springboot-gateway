package com.springboot.mq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfig {

	@Bean
	public Queue queue1(){
		return new Queue("queue1");
	}
	
	@Bean
	public Queue queue2(){
		return new Queue("queue2");
	}
}
