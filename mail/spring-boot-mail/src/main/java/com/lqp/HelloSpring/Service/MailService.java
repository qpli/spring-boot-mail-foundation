package com.lqp.HelloSpring.Service;

import java.beans.JavaBean;
import java.io.File;
//import java.util.logging.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
//@SpringBootApplication
@JavaBean
@Component
public class MailService {
	
	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Value("${spring.mail.username}")
	private String from;
	
	
	@Resource
	public JavaMailSender mailSender;
	
	public void sayHello() {
	System.out.println("Hello World!");
	}
	
	public void sendSimpleMail(String to,String subject,String content)
	{
		SimpleMailMessage message=new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(content);
		message.setFrom(from);
		mailSender.send(message);
	}
	
	
	public void sendHtmlEmail(String to,String subject,String content) throws MessagingException
	{
		MimeMessage message=mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message,true);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(content,true);
		helper.setFrom(from);
		mailSender.send(message);
	}
	
	public void sendAttachmentEmain(String to,String subject,String content,String filePath) throws MessagingException
	{
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(content,true);
		helper.setFrom(from);
		FileSystemResource file=new FileSystemResource(new File(filePath));
	    String filename = file.getFilename();
	    helper.addAttachment(filename, file);
	    mailSender.send(message);
	}

	
	public void sendPictureEmain(String to,String subject,String content,String rscPath,String rscId)
	{
		logger.info("发送的静态邮件开始： {},{},{},{},{}",to,subject,content,rscPath,rscId);
//		org.apache.logging.log4j.Logger.info("发送的静态邮件开始： {},{},{},{}",to,content,rscPath,rscId);
		MimeMessageHelper helper=null;
		try {
			MimeMessage message = mailSender.createMimeMessage();
			helper = new MimeMessageHelper(message,true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content,true);
			helper.setFrom(from);
			FileSystemResource resource=new FileSystemResource(new File(rscPath));
			helper.addInline(rscId, resource);
		    mailSender.send(message);
		    logger.info("静态图片邮件发送成功");
		    
		} catch (Exception e) {
			// TODO: handle exception
			
			logger.error("静态图片邮件发送失败");
		}
		
	}
	
	
	
	
	
	
}
