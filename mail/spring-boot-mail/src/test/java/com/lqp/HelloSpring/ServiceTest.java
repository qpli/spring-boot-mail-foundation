package com.lqp.HelloSpring;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lqp.HelloSpring.Service.MailService;

@RunWith(SpringRunner.class)
@SpringBootTest

public class ServiceTest {
	@Autowired
   MailService mailService;
	
	@Test
	public void sendSimpleMailTest()
	{
		mailService.sendSimpleMail("1596925431@qq.com", "这是第一封邮件", "你是猪吧，这是我的spring-boot第一封邮件！");
	}
	

}
