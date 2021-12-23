package com.team7.leave.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team7.leave.services.SendMailService;
import com.team7.leave.helper.Email;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/v1/mail/")
public class EmailController {
    SendMailService service;

    public EmailController(SendMailService service) {
        this.service = service;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMail(@RequestBody Email mail) {
        service.sendMail(mail);
        return new ResponseEntity<>("Email Sent successfully", HttpStatus.OK);
    }

    @PostMapping("/attachment")
    public ResponseEntity<String> sendAttachmentEmail(@RequestBody Email mail) throws MessagingException {
        service.sendMailWithAttachments(mail);
        return new ResponseEntity<>("Attachment mail sent successfully", HttpStatus.OK);
    }
}
