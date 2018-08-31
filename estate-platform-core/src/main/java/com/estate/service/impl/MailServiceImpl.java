package com.estate.service.impl;

import com.estate.constant.ConfigurationConstant;
import com.estate.core.entity.ConfigurationEntity;
import com.estate.core.repository.ConfigurationRepository;
import com.estate.dto.MailDTO;
import com.estate.service.MailService;
import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.util.List;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {

	private final Logger LOG = Logger.getLogger(MailServiceImpl.class);

	private String userName;
	private String password;
	private String host;
	private String port;
	private String subject;

	@Autowired
	private ConfigurationRepository configurationRepository;

	@Override
	public void sendEmail(MailDTO mail) {
		List<ConfigurationEntity> configurations = configurationRepository.findByType(ConfigurationConstant.EMAIL_CONFIGURATION);
		for (ConfigurationEntity item: configurations) {
			if (item.getKey().equals(ConfigurationConstant.MAIL_USERNAME)) {
				userName = item.getValue();
			} else if (item.getKey().equals(ConfigurationConstant.MAIL_PASSWORD)) {
				password = item.getValue();
			} else if (item.getKey().equals(ConfigurationConstant.MAIL_HOST)) {
				host = item.getValue();
			} else if (item.getKey().equals(ConfigurationConstant.MAIL_PORT)) {
				port = item.getValue();
			} else if (item.getKey().equals(ConfigurationConstant.SUBJECT_USER_CREATE)) {
				subject = item.getValue();
			}
 		}
		Properties properties = initProperties(host, port);
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};
		Session session = Session.getInstance(properties, auth);
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mail.getMailFrom()));
			if (mail.getMailTo() != null && mail.getMailTo().length > 0) {
				InternetAddress[] toAddresses = new InternetAddress[mail.getMailTo().length];
				for (int i = 0; i < mail.getMailTo().length; i++) {
					toAddresses[i] = new InternetAddress(mail.getMailTo()[i]);
				}
				message.setRecipients(Message.RecipientType.TO, toAddresses);
			}
			if (mail.getMailCc() != null && mail.getMailCc().length > 0) {
				InternetAddress[] ccAddresses = new InternetAddress[mail.getMailCc().length];
				for (int i = 0; i < mail.getMailCc().length; i++) {
					ccAddresses[i] = new InternetAddress(mail.getMailCc()[i]);
				}
			}
			if (mail.getMailBcc() != null && mail.getMailBcc().length > 0) {
				InternetAddress[] bccAddresses = new InternetAddress[mail.getMailBcc().length];
				for (int i = 0; i < mail.getMailBcc().length; i++) {
					bccAddresses[i] = new InternetAddress(mail.getMailBcc()[i]);
				}
			}
			message.setSubject(subject, "UTF-8");
			message.setContent(getTemplate(mail.getTemplate(), mail), "text/html; charset=utf-8");
			Transport.send(message);
		} catch (MessagingException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	private Properties initProperties(String host, String port) {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.debug", "true");
		return properties;
	}

	private String getTemplate(String template, MailDTO mail) {
		Velocity.init();
		VelocityContext context = new VelocityContext();
		mail.getModel().forEach((key,value) -> {
			context.put(key, value);
		});
		StringWriter stringWriter = new StringWriter();
		Velocity.evaluate(context, stringWriter, "template", template);
		return stringWriter.toString();
	}
}
