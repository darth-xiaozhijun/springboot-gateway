package com.springboot.mq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "queue1")
public class Receiver {

	@RabbitHandler
	public void recevier(String msg){
		System.out.println("reciver��" + msg);
	}
}
