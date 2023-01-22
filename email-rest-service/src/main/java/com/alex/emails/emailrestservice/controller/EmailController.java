package com.alex.emails.emailrestservice.controller;

import com.alex.emails.emailrestservice.service.EmailService;
import com.example.commondata.dto.EmailDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
@Slf4j
public class EmailController {

    private final EmailService emailService;

    /**
     * Отправка письма на фиксированный mail
     * @return
     */
    @PostMapping(value = "/send-fix-mail")
    public ResponseEntity<?> sendFixMail(@RequestBody EmailDto emailDto) {
        log.info("sendFixMail {}", emailDto);
        emailService.sendFixMail(emailDto.getTo(),
                emailDto.getSubject(), emailDto.getBody());
        log.info("Ушло");
        return new ResponseEntity<>("Сообщение отправлено", HttpStatus.OK);
    }

    /**
     * Получен запрос на отправку письма на  mail
     * @return
     */
    @PostMapping(value = "/sendmail")
    public ResponseEntity<Object> sendMail(@RequestBody EmailDto emailDto) {
        log.info("sendmail -- {}", emailDto);
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
            log.info("Data is checked");

            if (emailService.sendMail(emailDto)) {
                return new ResponseEntity<>("Сообщение отправлено пользователю "
                        + emailDto.getFullName() + " по адресу " + emailDto.getTo()
                        , HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Сообщение неотправлено",
                        HttpStatus.REQUEST_TIMEOUT);
            }
        } else {
            return new ResponseEntity<>("Объект EmailDto пустой", HttpStatus.NOT_ACCEPTABLE);
        }

    }
}
