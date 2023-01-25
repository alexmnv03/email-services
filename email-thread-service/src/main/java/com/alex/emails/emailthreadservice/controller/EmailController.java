package com.alex.emails.emailthreadservice.controller;

import com.alex.emails.emailthreadservice.data.CommonTreadData;
import com.example.commondata.dto.EmailDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
@Slf4j
public class EmailController {

    private final CommonTreadData commonTreadData;

    /**
     * Остановка сервиса генерации писем
     */
    @PostMapping(value = "/stop")
    public void stopReceivingEmails() {
        log.info("stopReceivingEmails->start ");
        commonTreadData.setIsStart(new AtomicBoolean((false)));
    }

}
