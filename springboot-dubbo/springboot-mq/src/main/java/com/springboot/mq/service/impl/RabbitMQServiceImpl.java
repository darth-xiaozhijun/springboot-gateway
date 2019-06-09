package com.springboot.mq.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.amqp.core.AmqpTemplate;

import com.alibaba.dubbo.config.annotation.Service;
import com.springboot.api.service.manager.IRabbitMQService;

@Service
public class RabbitMQServiceImpl implements IRabbitMQService{
	
	@Resource
	private AmqpTemplate amqpTemplate;

	@Override
	public String send() {
		
		String content = "Date：" + new Date();
		amqpTemplate.convertAndSend("queue1",content);
		return content;
	}

	@Override
	public String multiSend() {
		
		StringBuilder times=new StringBuilder();
        for(int i=0;i<10;i++){
            long time=System.nanoTime();
            amqpTemplate.convertAndSend("queue1","��"+i+"�η��͵�ʱ�䣺"+time);
            times.append(time+"<br>");
        }
        return times.toString();
	}

	@Override
	public String multi2Send() {
		
		StringBuilder times=new StringBuilder();
        for(int i=0;i<10;i++){
            long time=System.nanoTime();
            amqpTemplate.convertAndSend("queue1","��"+i+"�η��͵�ʱ�䣺"+time);
            amqpTemplate.convertAndSend("queue2","��"+i+"�η��͵�ʱ�䣺"+time);
            times.append(time+"<br>");
        }
        return times.toString();
	}

	@Override
	public String topicSend1() {
		
		String context = "my topic 1";
        System.out.println("������˵ : " + context);
        this.amqpTemplate.convertAndSend("exchange", "topic.message", context);
        return context;
	}

	@Override
	public String topicSend2() {
		
		String context = "my topic 2";
        System.out.println("������˵ : " + context);
        this.amqpTemplate.convertAndSend("exchange", "topic.messages", context);
        return  context;
	}

}
