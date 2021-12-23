package com.team7.leave.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.team7.leave.helper.Email;

public interface SendMailService {

	void sendMail(Email mail);

    void sendMailWithAttachments(Email mail) throws MessagingException;
}
