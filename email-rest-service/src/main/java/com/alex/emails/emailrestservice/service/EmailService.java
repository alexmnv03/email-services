package com.alex.emails.emailrestservice.service;

import com.example.commondata.dto.EmailDto;

public interface EmailService {

    void sendFixMail(String toEmail, String subject, String message);
    //    boolean sendMail(String toEmail, String subject, String fullName, Integer code);
    boolean sendMail(EmailDto emailDto);

}
