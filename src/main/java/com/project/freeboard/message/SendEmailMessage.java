package com.project.freeboard.message;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailMessage {

	public final static String EMAIL_ORIGEN = "ridel007@gmail.com";

	private final Properties properties = new Properties();

	private String password;

	private Session session;

	public SendEmailMessage() {

	}

	public void init() {
		properties.put("mail.smtp.host", "mail.gmail.com");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port", 25);
		properties.put("mail.smtp.mail.sender", EMAIL_ORIGEN);
		properties.put("mail.smtp.user", "usuario");
		properties.put("mail.smtp.auth", "true");

		session = Session.getDefaultInstance(properties);
	}

	public boolean sendMessage(String emailRecipient, String subject, String messageToSend) {
		init();

		boolean enviado = false;

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailRecipient));
			message.setSubject(subject);
			message.setText(messageToSend);
			Transport t = session.getTransport("smtp");
			t.connect((String) properties.get("mail.smtp.user"), "password");
			t.sendMessage(message, message.getAllRecipients());
			t.close();
			enviado = true;
		} catch (MessagingException me) {
			enviado = false;
			me.getStackTrace();
		}

		return enviado;

	}

}
