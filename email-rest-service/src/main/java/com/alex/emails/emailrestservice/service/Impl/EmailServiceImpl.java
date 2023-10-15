package com.alex.emails.emailrestservice.service.Impl;

import com.alex.emails.emailrestservice.config.EmailServerConfig;
import com.alex.emails.emailrestservice.service.EmailService;
import com.example.commondata.data.EmailConstants;
import com.example.commondata.dto.EmailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

import static com.example.commondata.data.EmailConstants.FIX_FROM_EMAIL;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    private String username;

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

        mailMessage.setFrom(FIX_FROM_EMAIL);

        log.info("отправляем ");
        javaMailSender.send(mailMessage);
        log.info("отправлено");
    }

    /**
     * Отправка письма email клиента
     * @param emailDto data
     */
    public ResponseEntity<Object> sendMail(EmailDto emailDto) {
        log.info("sendMail {}", emailDto);
        log.info("EmailServiceImpl ---> username = {0}:{}" , username);
        ResponseEntity<Object> response = verificationEmailData(emailDto);
        if (response.getStatusCode() != HttpStatus.OK) {
            return response;
        }
        log.info("Data is checked");
        try {
            // SimpleMailMessage mailMessage = new SimpleMailMessage();

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

            return new ResponseEntity<>("Сообщение отправлено пользователю "
                    + emailDto.getFullName() + " по адресу " + emailDto.getTo()
                    , HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Ошибка отправки сообщения " + e.getMessage(),
                    HttpStatus.REQUEST_TIMEOUT);
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
                EmailConstants.EMAIL_TEXT,
                emailDto.getFullName(),
                emailDto.getCode()));
        return email;
    }

    private ResponseEntity<Object> verificationEmailData(EmailDto emailDto) {
        if (emailDto != null) {
            if (emailDto.getFullName() == null) {
                return new ResponseEntity<>("Не заполнено поле с именем",
                        HttpStatus.NOT_ACCEPTABLE);
            }
            if (emailDto.getTo() == null) {
                return new ResponseEntity<>("Не заполнено поле email",
                        HttpStatus.NOT_ACCEPTABLE);
            }
            if (emailDto.getSubject() == null) {
                return new ResponseEntity<>("Не заполнено поле темы",
                        HttpStatus.NOT_ACCEPTABLE);
            }
            if ((emailDto.getCode() == null) || (emailDto.getCode() == 0)) {
                return new ResponseEntity<>("Нет кода подтвержения",
                        HttpStatus.NOT_ACCEPTABLE);
            }
        } else {
            return new ResponseEntity<>("Объект EmailDto пустой", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Объект проверен", HttpStatus.OK);
    }

}
