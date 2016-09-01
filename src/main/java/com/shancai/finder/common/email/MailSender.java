package com.shancai.finder.common.email;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailSender {

	@Value(value = "${spring.mail.username}")
	String mailFrom;

	@Resource
	JavaMailSenderImpl javaMailSenderImpl;

	public void simpleMail(String toAddr, String title, String content) {
		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setFrom(mailFrom);
		smm.setTo(toAddr);
		smm.setSubject(title);
		smm.setText(content);
		javaMailSenderImpl.send(smm);
	}

	public void mimeMessage(String toAddr, String title, String content) throws MessagingException {
		MimeMessage message = javaMailSenderImpl.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setTo(toAddr);
		helper.setSubject(title);
		helper.setText(content);
		javaMailSenderImpl.send(message);
	}

}
