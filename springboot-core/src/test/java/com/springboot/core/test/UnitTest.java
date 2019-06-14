package com.springboot.core.test;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
	
	@Autowired
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
	
	/**
     * 附件发送
     */
    @Test
    public void mimeEmail() throws MessagingException {
        // MimeMessage 本身的 API 有些笨重，我们可以使用 MimeMessageHelper
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        // 第二个参数是 true ，表明这个消息是 multipart类型的/
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("15717061273@163.com");
        mimeMessageHelper.setTo("1423854732@qq.com");
        mimeMessageHelper.setSubject("附件邮件主题");
        mimeMessageHelper.setText("附件邮件内容");
        //添加附件,第一个参数表示添加到 Email 中附件的名称，第二个参数是图片资源
        mimeMessageHelper.addAttachment("Averages.jpeg", new ClassPathResource("public/images/Averages.jpeg"));
        javaMailSender.send(mimeMessage);
    }

    /**
     * 富文本发送
     */
    @Test
    public void htmlEmail() throws MessagingException {
        // MimeMessage 本身的 API 有些笨重，我们可以使用 MimeMessageHelper
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        // 第二个参数是 true ，表明这个消息是 multipart类型的/
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("15717061273@163.com");
        mimeMessageHelper.setTo("1423854732@qq.com");
        mimeMessageHelper.setSubject("附件邮件主题");
        // 设置内嵌元素 cid，第一个参数表示 内联图片的标识符，第二个参数标识资源引用
        mimeMessageHelper.addInline("boot", new ClassPathResource("public/images/Averages.jpeg"));
        String html = "<html><body><h4>Hello,SpringBoot</h4><img src='cid:boot' /></body></html>";
        mimeMessageHelper.setText(html, true);
        // 设置内嵌元素 cid，第一个参数表示 内联图片的标识符，第二个参数标识资源引用
        mimeMessageHelper.addInline("boot", new ClassPathResource("public/images/Averages.jpeg"));
        //添加附件,第一个参数表示添加到 Email 中附件的名称，第二个参数是图片资源
        mimeMessageHelper.addAttachment("Averages.jpeg", new ClassPathResource("public/images/Averages.jpeg"));
        javaMailSender.send(mimeMessage);
    }
}
