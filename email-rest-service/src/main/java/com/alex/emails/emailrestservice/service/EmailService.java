package com.alex.emails.emailrestservice.service;

import com.example.commondata.dto.EmailDto;
import org.springframework.http.ResponseEntity;

public interface EmailService {

    void sendFixMail(String toEmail, String subject, String message);
    //    boolean sendMail(String toEmail, String subject, String fullName, Integer code);
    ResponseEntity<Object> sendMail(EmailDto emailDto);

}
