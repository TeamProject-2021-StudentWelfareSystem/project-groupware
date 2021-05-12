package com.mju.groupware.email;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

import com.mju.groupware.dto.UserEmail;

@Component
public class EmailImpl implements Email {

	private String Host;
	private String UserEmail;
	private String UserPwd;
	private String ToEmail;
	private String Subject;
	private String Content;
	private List<String> ContentList;
	private List<String> DateList;
	private List<String> SubjectList;
	private List<String> Fromlist;

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
		// POP3 주소
		String host = "outlook.office365.com";
		List<String> list = new ArrayList<String>();
		Fromlist = new ArrayList<String>();
		SubjectList = new ArrayList<String>();
		ContentList = new ArrayList<String>();
		DateList = new ArrayList<String>();
		int counter = 1;

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

			// Get directory(여기서 이메일 내용들 주르륵 보여주는 거임)

			UserEmail userEmail = new UserEmail();
			int location = 0;
			int location2 = 0;
			for (int i = 0, n = message.length; i < n; i++) {
				Address from = message[i].getFrom()[0];
				String sFrom = from + "\t";
				// 온전한 이메일주소찾오는거
				if (sFrom.indexOf("<") != -1) {
					location = sFrom.indexOf("<");
				}
				if (sFrom.indexOf(">") != -1) {
					location2 = sFrom.indexOf(">");
				}

				if (location != 0 && location2 != 0 && sFrom.contains("<") && sFrom.contains(">")) {

					sFrom = sFrom.substring(location + 1, location2); //그냥 location하면 /까지 출력됨
				} // bad 
				Fromlist.add(sFrom);

				String subject = message[i].getSubject();
				subject = subject.replaceAll(" ", "&nbsp;");
				SubjectList.add(subject);

				String contentType = message[i].getContentType();
				String content = "";
				if (contentType.contains("multipart")) {
					Multipart multipart = (Multipart) message[i].getContent();
					int numofparts = multipart.getCount();
					for (int k = 0; k < numofparts; k++) {
						MimeBodyPart part = (MimeBodyPart) multipart.getBodyPart(k);
						content = part.getContent().toString();

					}
					ContentList.add(content);
				} else if (contentType.contains("text/plain") || contentType.contains("text/html")) {
					String ObjectContent = message[i].getContent().toString();
					if (ObjectContent != null) {
						content = ObjectContent.toString();
						ContentList.add(content);

					}
				}
				String date = message[i].getSentDate().toString();

				date = date.replaceAll("Mon", "월");
				date = date.replaceAll("Tue", "화");
				date = date.replaceAll("Wed", "수");
				date = date.replaceAll("Thu", "목");
				date = date.replaceAll("Fri", "금");
				date = date.replaceAll("Sat", "토");
				date = date.replaceAll("Sun", "일");

				date = date.replaceAll("Jan", "1/");
				date = date.replaceAll("Feb", "2/");
				date = date.replaceAll("Mar", "3/");
				date = date.replaceAll("Apr", "4/");
				date = date.replaceAll("May", "5/");
				date = date.replaceAll("Jun", "6/");
				date = date.replaceAll("Jul", "7/");
				date = date.replaceAll("Aug", "8/");
				date = date.replaceAll("Sep", "9/");
				date = date.replaceAll("Oct", "10/");
				date = date.replaceAll("Nov", "11/");
				date = date.replaceAll("Dec", "12/");

				date = date.replaceAll(" ", "&nbsp;");
				date = date.replaceAll("KST", "");

				DateList.add(date);
				// 첨부파일이 있을 경우 파일에다가 mail을 붙인다.
			}

			for (int i = 0; i < Fromlist.size(); i++) {
				list.add(Integer.toString(counter));
				list.add(Fromlist.get(i));
				list.add(SubjectList.get(i));
				counter++;
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

	@Override
	public List<String> getContent() {

		return this.ContentList;
	}

	@Override
	public List<String> getFrom() {
		// TODO Auto-generated method stub
		return this.Fromlist;
	}

	@Override
	public List<String> getsubject() {
		// TODO Auto-generated method stub
		return this.SubjectList;
	}

	@Override
	public List<String> getDate() {
		// TODO Auto-generated method stub
		return this.DateList;
	}

}