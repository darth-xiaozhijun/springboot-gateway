package com.springboot.task.worker;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.springboot.api.service.IUserService;
import com.springboot.base.entity.User;

@Service
public class TestWorker {

	@Reference
	private IUserService userService;
	
	@Scheduled(cron = "0/1 * * * * ?")
	public void test(){
		
		User user = new User();
		user.setUsername("LuXun");
		user.setPassword("LuXun");
		user.setRealname("张");
		user.setSex(1);
		user.setAge(100);
		userService.addRedisCache(user);
		System.out.println("定时器执行");
	}
}
