package com.springboot.task.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.springboot.api.service.IUserService;
import com.springboot.base.entity.User;

@Service
public class TestWorker {
	
	private static final Logger LOG = LoggerFactory.getLogger(TestWorker.class);

	@Reference
	private IUserService userService;
	
	@Scheduled(cron = "0/5 * * * * ?")
	public void test(){
		
		User user = new User();
		user.setUsername("LuXun");
		user.setPassword("LuXun");
		user.setRealname("张");
		user.setSex(1);
		user.setAge(100);
		String result = userService.addRedisCache(user);
		LOG.info("定时器执行结束,请求参数：user:{},返回参数：result:{}", 
				new Object[]{JSON.toJSONString(user),JSON.toJSONString(result)});
	}
}
