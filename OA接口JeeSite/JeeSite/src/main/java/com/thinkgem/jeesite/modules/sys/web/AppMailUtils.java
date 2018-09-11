package com.thinkgem.jeesite.modules.sys.web;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AppMailUtils {

	public static final String HOST = "smtp.qiye.163.com";
	public static final String PROTOCOL = "smtp";
	public static final int PORT = 25;
	public static final String FROM = "liujie@zih718.com";// 发件人的email
	public static final String PWD = "LIUliuJIE1";// 发件人密码

	/*
	 * public static final String FROM = "wangyl@zih718.com";// 发件人的email public
	 * static final String PWD = "454958..wyl";// 发件人密码
	 */
	/**
	 * 获取Session
	 * 
	 * @return
	 */
	private static Session getSession() {
		Properties props = new Properties();
		props.put("mail.smtp.host", HOST);// 设置服务器地址
		props.put("mail.store.protocol", PROTOCOL);// 设置协议
		props.put("mail.smtp.port", PORT);// 设置端口
		props.put("mail.smtp.auth", true);

		Authenticator authenticator = new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(FROM, PWD);
			}

		};
		Session session = Session.getDefaultInstance(props, authenticator);

		return session;
	}

	public static void send(String toEmail, String context) {
		Session session = getSession();
		MimeMessage msg = new MimeMessage(session);
		try {
			System.out.println("--send--");
			msg.setFrom(new InternetAddress(FROM));
			msg.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(
					toEmail));
			msg.setSubject("邮箱验证码", "UTF-8");
			msg.setSentDate(new Date());
			msg.setContent(context, "text/html;charset=utf-8");
			msg.saveChanges();
			Transport transport = session.getTransport();
			transport.connect(FROM, PWD);
			transport.sendMessage(msg, msg.getAllRecipients());
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
