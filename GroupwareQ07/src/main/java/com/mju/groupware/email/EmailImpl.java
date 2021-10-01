package com.mju.groupware.email;

import java.io.UnsupportedEncodingException;
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

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.mju.groupware.constant.ConstantAdminEmail;
import com.mju.groupware.dto.UserEmail;

@Component
public class EmailImpl implements Email {
	private String Host;
	private String UserEmail;
	private String UserPwd;
	private String ToEmail;
	private String Subject;
	private String Content;
	private Store store;
	private List<UserEmail> list;
	private ConstantAdminEmail Constant;

	public EmailImpl() {
		// 컨테이너 생성 및 xml 파일 로드
		GenericXmlApplicationContext CTX = new GenericXmlApplicationContext();
		CTX.load("classpath:/xmlForProperties/Email.xml");
		CTX.refresh();

		// 빈 객체 받아오기
		this.Constant = (ConstantAdminEmail) CTX.getBean("emailCertification");

		// 프로퍼티 값 확인
		this.Host = Constant.getHost();
		this.UserEmail = Constant.getHostEmail();
		this.UserPwd = Constant.getHostPwd();
	}

	@Override
	public void sendEmail(String email, int Num) {
		this.ToEmail = email;
		// 제목 설정
		this.Subject = this.Constant.getSubject();
		try {
			Subject = new String(Subject.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 추후에 DB에 이문구도 저장하여 가져올 예정
		this.Content = Constant.getContentBeforeNum() + Num + Constant.getContentAfterNum();
		try {
			Content = new String(Content.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Properties Properties = new Properties();
		Properties.put(this.Constant.getMailSmtpAuth(), true);
		Session session = Session.getDefaultInstance(Properties);
		MimeMessage Msg = new MimeMessage(session);

		try {
			Msg.setSubject(Subject);
			Msg.setText(Content);
			Msg.setFrom(new InternetAddress(UserEmail));
			Msg.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(ToEmail));

			Transport transport = session.getTransport(this.Constant.getSmtps());
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
	public boolean CheckEmailLogin(String id, String pw) {
		boolean CheckEmailLogin = false;
		// Create empty properties
		Properties props = new Properties();
		// POP3 주소
		String host = this.Constant.getHostURL();
		// session생성
		Session session = Session.getDefaultInstance(props, null);
		// store생성
		this.store = null;

		try {
			store = session.getStore(this.Constant.getPop3Store());
			store.connect(host, id, pw);
			if (store.isConnected()) {
				CheckEmailLogin = true;
			} else {
				CheckEmailLogin = false;
			}
		} catch (Exception e) {
		}

		return CheckEmailLogin;
	}

	@Override
	public List<UserEmail> printEmailList() {
		list = new ArrayList<UserEmail>();
		// 게시판 번호 설정
		int counter = 1;
		// folder생성
		Folder folder = null;

		try {
			// Get folder
			folder = store.getFolder(this.Constant.getINBOX());
			folder.open(Folder.READ_ONLY);
			// 이메일 박스
			Message message[] = folder.getMessages();

			// Get directory(여기서 이메일 내용들 주르륵 보여주는 거임)
			for (int i = 0, n = message.length; i < n; i++) {
				// read를 하면서 userEmail을 reset해준다.
				UserEmail userEmail = new UserEmail();
				userEmail.setCounter(counter);
				int location = 0;
				int location2 = 0;

				Address from = message[i].getFrom()[0];
				String sFrom = from + "\t";
				// 온전한 이메일주소찾오는거

				if (sFrom.indexOf("<") != -1) {
					location = sFrom.indexOf("<");
					if (sFrom.indexOf(">") != -1) {
						location2 = sFrom.indexOf(">");
					}
				}

				if (location != 0 && location2 != 0 && sFrom.contains("<") && sFrom.contains(">")) {
					sFrom = sFrom.substring(location + 1, location2); // 그냥 location하면 /까지 출력됨
				}
				userEmail.setFrom(sFrom);

				String Title = message[i].getSubject();
				userEmail.setTitle(Title);

				String contentType = message[i].getContentType();
				String content = "";
				if (contentType.contains(this.Constant.getMultipart())) {
					Multipart multipart = (Multipart) message[i].getContent();
					int numofparts = multipart.getCount();
					for (int k = 0; k < numofparts; k++) {
						MimeBodyPart part = (MimeBodyPart) multipart.getBodyPart(k);
						content = part.getContent().toString();
					}
				} else if (contentType.contains(this.Constant.getTextPlain())
						|| contentType.contains(this.Constant.getTextHtml())) {
					String ObjectContent = message[i].getContent().toString();
					if (ObjectContent != null) {
						content = ObjectContent.toString();
					}
				}
				userEmail.setContent(content);

				String date = message[i].getSentDate().toString();

				date = date.replaceAll(this.Constant.getMon(), this.Constant.getKoreanMon());
				date = date.replaceAll(this.Constant.getTue(), this.Constant.getKoreanTue());
				date = date.replaceAll(this.Constant.getWed(), this.Constant.getKoreanWed());
				date = date.replaceAll(this.Constant.getThu(), this.Constant.getKoreanThu());
				date = date.replaceAll(this.Constant.getFri(), this.Constant.getKoreanFri());
				date = date.replaceAll(this.Constant.getSat(), this.Constant.getKoreanSat());
				date = date.replaceAll(this.Constant.getSun(), this.Constant.getKoreanSun());

				date = date.replaceAll(this.Constant.getJan(), this.Constant.getNumJan());
				date = date.replaceAll(this.Constant.getFeb(), this.Constant.getNumFeb());
				date = date.replaceAll(this.Constant.getMar(), this.Constant.getNumMar());
				date = date.replaceAll(this.Constant.getApr(), this.Constant.getNumApr());
				date = date.replaceAll(this.Constant.getMay(), this.Constant.getNumMay());
				date = date.replaceAll(this.Constant.getJun(), this.Constant.getNumJun());
				date = date.replaceAll(this.Constant.getJul(), this.Constant.getNumJul());
				date = date.replaceAll(this.Constant.getAug(), this.Constant.getNumAug());
				date = date.replaceAll(this.Constant.getSep(), this.Constant.getNumSep());
				date = date.replaceAll(this.Constant.getOct(), this.Constant.getNumOct());
				date = date.replaceAll(this.Constant.getNov(), this.Constant.getNumNov());
				date = date.replaceAll(this.Constant.getDec(), this.Constant.getNumDec());
				
				date = new String(date.getBytes("iso-8859-1"), "utf-8");
				// 일반 빈칸을 html이 인식할 수 있는 공백으로 변환
				date = date.replaceAll(" ", "&nbsp;");
				// KST를 제거한다.
				date = date.replaceAll("KST", "");

				userEmail.setDate(date);
				// 첨부파일이 있을 경우 파일에다가 mail을 붙인다.
				list.add(userEmail);
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
	public List<UserEmail> GetEmailList() {
		return list;
	}
}