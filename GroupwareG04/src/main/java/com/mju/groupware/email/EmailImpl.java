package com.mju.groupware.email;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
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

	@Override
	public List<String> printEmailList(String id, String pw) {
		// Create empty properties
		Properties props = new Properties();
		// 다운로드된 파일이 저장되는 경우
//		String downloadPath = "F:\\mailDown";
		// POP3 주소
		String host = "outlook.office365.com";
		List<String> list = new ArrayList<String>();
		
		// Get session
		Session session = Session.getDefaultInstance(props, null);

		Store store = null;
		Folder folder = null;
		try {
			// Pop3 서버 가져오기
			store = session.getStore("pop3s");
			store.connect(host, id, pw);

			// Get folder
			folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);

			// 이메일 박스
			Message message[] = folder.getMessages();
			int messageLength = 0;

			// 제목 길이를 가져온다.
			messageLength = message.length;

			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			// Get directory(여기서 이메일 내용들 주르륵 보여주는 거임)
			for (int i = 0, n = message.length; i < n; i++) {
				Address from = message[i].getFrom()[0];
				String subject = message[i].getSubject();
				String fromWithSubject = from + "\t" + subject + "\n" + message[i].getContent().toString();

				// 이메일 Stream정보 제공
				// message[i].writeTo(System.out);

				// from = message[i].getFrom()[0];
				subject = message[i].getSubject();
				
				list.add(fromWithSubject);
				
				// 첨부파일이 있을 경우 파일에다가 mail을 붙인다.
//				POP3Test.appendToFile(downloadPath + "in.mbx", message[i]);
			}

			// Close connection
			folder.close(false);
			store.close();

		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		
		return list;

	}

}
