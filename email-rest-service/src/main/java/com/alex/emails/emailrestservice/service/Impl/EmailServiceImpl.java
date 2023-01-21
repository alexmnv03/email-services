package com.alex.emails.emailrestservice.service.Impl;

import com.alex.emails.emailrestservice.config.EmailServerConfig;
import com.alex.emails.emailrestservice.service.EmailService;
import com.example.commondata.dto.EmailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ru.octory.marketplace.common.data.AuthConstants;

import javax.mail.internet.MimeMessage;
import java.nio.charset.Charset;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    private String username;

    private static final String FROM_EMAIL = "lysanaymlv@mail.ru";
//    private static final String FROM_EMAIL = "alexmnv02@gmail.com";
//    private static final String FROM_EMAIL = "tech_support@octory.ru";

    // LATEST: Заижестить через конструктор обычном способом - и проверить
    @Autowired
    private JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender, EmailServerConfig serverConfig) {
        this.javaMailSender = javaMailSender;
        this.username = serverConfig.getUsername();
    }


    /**
     * Тестовая отправка письма на фиксированный email
     * @param toEmail
     * @param subject
     * @param message
     */
    public void sendFixMail(String toEmail, String subject, String message) {
        log.info("sendFixMail");

        // Простой вариант отправки писем

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailMessage.setFrom(FROM_EMAIL);

        log.info("отправляем ");
        javaMailSender.send(mailMessage);
        log.info("отправлено");


//        SmtpClient client =
//                new SmtpClient("smtp.mail.ru", Convert.ToInt32(0x19)) // сервер,порт
//                {
//                    Credentials = new NetworkCredential("Ваш Email", "Ваш пароль"),
//                    EnableSsl = true // обязательно!
//                };

    }

    /**
     * Отправка письма email клиента
     * @param emailDto data
     */
//    public boolean sendMail(String toEmail, String subject, String fullName, Integer code) {
    public boolean sendMail(EmailDto emailDto) {
        log.info("EmailServiceImpl ---> sendMail");
        log.info("EmailServiceImpl ---> username = {0}:{}" , username);
        try {
//            SimpleMailMessage mailMessage = new SimpleMailMessage();

            EmailDto emailBodyDto = addBodyForEmailDto(emailDto);

            final MimeMessage mailMessage = javaMailSender.createMimeMessage();

            final MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,
                    true,"utf8");

            messageHelper.setFrom(username);
            messageHelper.setTo(emailBodyDto.getTo());
            messageHelper.setSubject(emailBodyDto.getSubject());
            // обычный тескст
            // messageHelper.setText(emailBodyDto.getBody());

            mailMessage.setContent(emailBodyDto.getBody(), "text/html; charset=utf-8");
            javaMailSender.send(mailMessage);
            log.info("EmailServiceImpl ---> sendMail письмо отправлено клиенту");
            return true;

        } catch (Exception e) {
            log.error("EmailServiceImpl ---> sendMail {0}: {}", e.getMessage());
            return false;
        }
        // LATER: Пробросить наверх исключение SocketTimeoutException, чтобы отличать их
        // LATER: Пробросить наверх исключение catch (MailSendException exc) {
    }

    /**
     * Формирование DTO объекта для email-сервиса.
     * @param emailDto data
     * @return dto
     */
    private EmailDto addBodyForEmailDto(EmailDto emailDto) {
        // LATER: переделать в конвертер.
        var email = new EmailDto();
        email.setTo(emailDto.getTo());
        email.setSubject(emailDto.getSubject());
        email.setBody(String.format(
                AuthConstants.EMAIL_TEXT,
                emailDto.getFullName(),
                emailDto.getCode()));
        return email;
    }

}
