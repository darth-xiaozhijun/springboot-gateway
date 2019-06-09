package com.springboot.api.service.manager;

public interface IRabbitMQService {

	String send();
	
	String multiSend();
	
	String multi2Send();
	
	String topicSend1();
	
	String topicSend2();
}
