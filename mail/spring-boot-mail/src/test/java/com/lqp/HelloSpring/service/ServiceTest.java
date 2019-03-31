package com.lqp.HelloSpring.service;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.hibernate.validator.cfg.context.ContainerElementConstraintMappingContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.lqp.HelloSpring.Service.MailService;

import ch.qos.logback.classic.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest

public class ServiceTest {
	
	@Resource
	public MailService mailService;
	
	@Resource
	TemplateEngine templateEngine;
	
	@Test
	public void sendSimpleMailTest()
	{
		mailService.sendSimpleMail("1119744330@qq.com", "这是第一封邮件", "你是猪吧，这是我的spring-boot第一封邮件！");
		//mailService.sendHtmlEmail("1119744330@qq.com", "这是第一封邮件", "你是猪吧，这是我的spring-boot第一封邮件！");
	}
	
	@Test
	public void sendHtmlMainTest() throws MessagingException
	{
		String content="<html>\n"+
	"<body>\n"+
				"<h3>hello world,这是一封html邮件！</h3>\n"+
	"</body>\n"+
				"</html>";
		mailService.sendHtmlEmail("1119744330@qq.com", "这是一封HTML邮件", content);
	}
	
	@Test
	public void sendAttachmentMailTest() throws MessagingException
	{
		String filePathString="G:\\lqp\\springboot\\mail\\1.pdf";
		mailService.sendAttachmentEmain("707901160@qq.com", "这是一个带附件的邮件", "你是猪", filePathString);
	}
	
	@Test
	public void sendPictureMailTest()
	{
		
		
		String rscPathString="G:/lqp/springboot/mail/1.jpg";
		String rscIdString="sd";
		mailService.sendPictureEmain("707901160@qq.com", "这是一个带图片的邮件", "hahaha哈哈哈", rscPathString,rscIdString);
	}
	
	
	@Test
	public void testTmeplateMail() throws MessagingException
	{
		Context context = new Context();
		context.setVariable("id", "6666");
		String emailContentString=templateEngine.process("emailTemplate", context);
		mailService.sendHtmlEmail("707901160@qq.com", "这是一个模板邮件", emailContentString);
	}
	
	

}
