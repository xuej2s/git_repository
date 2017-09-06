package com.lpy.JMail;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

/**
 * Hello world!
 *
 */
public class App {
	static final Map<String, String> MAIL_CONFIG;
	static{
		MAIL_CONFIG = new HashMap<String, String>();
		MAIL_CONFIG.put("qqHost", "smtp.qq.com");
		MAIL_CONFIG.put("163Host", "smtp.163.com");
		MAIL_CONFIG.put("qqMail", "test@qq.com");
		MAIL_CONFIG.put("163Mail", "test@163.com");
		MAIL_CONFIG.put("qqPwd", "pwd");
		MAIL_CONFIG.put("163Pwd", "pwd");
	}
	public static void main(String[] args) {
		
		
		// 发件人邮箱
		String fromMail = "test@qq.com";

		// 收件人邮箱
		String toMail = "test@163.com";

		// qq邮箱第三方登录使用密码
		// yxcyqctlhcfibfde
		// POP3服务器（端口110）pop.qq.com
		// SMTP服务器（端口25） smtp.qq.com
		
		//163
		// POP3服务器: pop.163.com
		// SMTP服务器: smtp.163.com
		// IMAP服务器: imap.163.com
		
		// 获取系统属性
		Properties properties = System.getProperties();

		// 设置服务器
		properties.setProperty("mail.smtp.host", MAIL_CONFIG.get("qqHost"));
		properties.put("mail.smtp.auth", "true");
		
		//还要设置SSL加密
		MailSSLSocketFactory sf;
		try {
			sf = new MailSSLSocketFactory();
		
		sf.setTrustAllHosts(true);
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.ssl.socketFactory", sf);

		// 获取session对象----new className(){}???????
		Session session = Session.getDefaultInstance(properties, new Authenticator(){
			 public PasswordAuthentication getPasswordAuthentication()
		        {
		         return new PasswordAuthentication(MAIL_CONFIG.get("qqMail"), MAIL_CONFIG.get("qqPwd")); //发件人邮件用户名、密码
		        }
		});

		// 邮件各项信息设置对象
		MimeMessage message = new MimeMessage(session);

		
			// from
			message.setFrom(new InternetAddress(fromMail));
			// to
			message.addRecipients(Message.RecipientType.TO, toMail);

			/*
			 * //发送给多个人 InternetAddress[] addresses = new InternetAddress[5];
			 * message.addRecipients(Message.RecipientType.TO, addresses);
			 */

			// 主题
			message.setSubject("test mail send");

			// 内容
			message.setText("bilibili!");

			// 发送
			Transport.send(message);
			System.out.println("look if success");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
	}
	
}
