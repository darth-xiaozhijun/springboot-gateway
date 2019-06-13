package com.springboot.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

@ComponentScan(basePackages={"com.springboot"}/*必须写，否则默认此类的 package*/)
@SpringBootApplication
@EnableDubbo
@MapperScan("com.springboot.dao")
@EnableConfigurationProperties
public class Application {

	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
