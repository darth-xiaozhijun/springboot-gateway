package com.springboot.core.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springboot.api.service.IUserService;
import com.springboot.base.entity.User;
import com.springboot.core.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@Rollback
public class UnitTest {

	@Resource
	private IUserService userService;
	
	private JavaMailSender javaMailSender;
	
	@Test
	public void testAdd(){
		User user = new User();
		user.setUsername("xiaozhijun");
		user.setPassword("xiao1027");
		user.setRealname("肖智军");
		user.setSex(1);
		user.setAge(23);
		
		String result = userService.addUser(user);
		System.out.println(result);
	}
	
	@Test
	public void testAddRedisCache(){
		User user = new User();
		user.setUsername("Huangfeihong");
		user.setPassword("Huangfeihong");
		user.setRealname("黄飞鸿");
		user.setSex(1);
		user.setAge(101);
		
		String result = userService.addRedisCache(user);
		System.out.println(result);
	}
	
	@Test
	public void sendSimpleEmail(){
		// 构造Email消息
	    SimpleMailMessage message = new SimpleMailMessage();
	    message.setFrom("15717061273@163.com");
	    message.setTo("1423854732@qq.com");
	    message.setSubject("邮件主题");
	    message.setText("邮件内容");
	    javaMailSender.send(message);
	}
}
