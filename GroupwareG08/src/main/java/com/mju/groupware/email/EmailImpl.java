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

	private String Host;
	private String UserEmail;
	private String UserPwd;
	private String ToEmail;
	private String ToName;
	private String Subject;
	private String Content;

	public EmailImpl() {
		this.Host = "smtp.gmail.com";
		this.UserEmail = "lovebus5050@gmail.com";
		this.UserPwd = "126479dd!@#$";

	}

	@Override
	public void sendEmail(String email, int Num) {
		this.ToEmail = email;
		// 제목 설정
		this.Subject = "MJU Welfare System인증메일 입니다.";
		// 추후에 DB에 이문구도 저장하여 가져올 예정
		this.Content = "MJU Welfare System의 회원가입 인증번호는 " + Num + " 입니다.";
		Properties Properties = new Properties();
		Properties.put("mail.smtps.auth", true);
		Session session = Session.getDefaultInstance(Properties);
		MimeMessage Msg = new MimeMessage(session);

		try {
			Msg.setSubject(Subject);
			Msg.setText(Content);
			Msg.setFrom(new InternetAddress(UserEmail));
			Msg.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(ToEmail));

			Transport transport = session.getTransport("smtps");
			transport.connect(Host, UserEmail, UserPwd);
			transport.sendMessage(Msg, Msg.getAllRecipients());
			transport.close();

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	public boolean AuthNum(int authNum, int num) {
		if (authNum == num) {
			return true;
		} else {
			return false;
		}
	}

}
