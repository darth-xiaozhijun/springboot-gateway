package com.springboot.web.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.springboot.api.service.manager.IRabbitMQService;

@RestController
@RequestMapping("/v1/rabbitmq")
public class RabbitMQController {
	
	@Reference
	private IRabbitMQService rabbitMQService;

	@RequestMapping(value = "/send.do",method = RequestMethod.GET)
	public String send(){
		
		return rabbitMQService.send();
	}
	
	@RequestMapping(value = "/multiSend.do",method = RequestMethod.GET)
    public String multiSend(){
		
		return rabbitMQService.multiSend();
    }
	
	@RequestMapping(value = "/multi2Send.do",method = RequestMethod.GET)
    public String multi2Send(){
		
		return rabbitMQService.multi2Send();
    }
	
	@RequestMapping(value = "/topicSend1.do",method = RequestMethod.GET)
    public String  topicSend1() {
		
		return rabbitMQService.topicSend1();
    }
	
    @RequestMapping(value = "/topicSend2.do",method = RequestMethod.GET)
    public String topicSend2() {
    	
    	return rabbitMQService.topicSend2();
    }
}
