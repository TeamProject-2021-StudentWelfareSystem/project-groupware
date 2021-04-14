package com.mju.groupware.email;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class EmailImpl implements Email {

	private String host;
	private String userEmail;
	private String Userpw;
	private String toEmail;
	private String toName;
	private String subject;
	private String content;

	public EmailImpl() {
		this.host = "smtp.gmail.com";
		this.userEmail = "lovebus5050@gmail.com";
		this.Userpw = "126479dd";

	}

	@Override
	public void sendEmail(String email, int Num) {
		this.toEmail = email;
		// 제목 설정
		this.subject = "MJU Welfare System인증메일 입니다.";
		// 추후에 DB에 이문구도 저장하여 가져올 예정
		this.content = "MJU Welfare System의 회원가입 인증번호는 " + Num + " 입니다.";
		Properties properties = new Properties();
		properties.put("mail.smtps.auth", true);
		Session session = Session.getDefaultInstance(properties);
		MimeMessage msg = new MimeMessage(session);

		try {
			msg.setSubject(subject);
			msg.setText(content);
			msg.setFrom(new InternetAddress(userEmail));
			msg.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(toEmail));

			Transport transport = session.getTransport("smtps");
			transport.connect(host, userEmail, Userpw);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	public boolean authNum(int authNum, int num) {

		if (authNum == num) {
			return true;
		} else {
			return false;
		}
	}

}
