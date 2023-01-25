package com.alex.emails.emailthreadservice;

import com.alex.emails.emailthreadservice.service.ReceiveEmailsServ;
import com.alex.emails.emailthreadservice.service.ReceiveEmailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@Slf4j
@SpringBootApplication
public class EmailThreadServiceApplication implements CommandLineRunner {

    @Resource
    ReceiveEmailsService receiveEmailsService;

    public static void main(String[] args) {
        SpringApplication.run(EmailThreadServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Тут запускается отдельный поток, можно было здесь написать код, но для демонстрации
        // работы с потоками все таки вынесем его в отдельный процесс
        receiveEmailsService.LaunchReceivingEmailService();
    }
}
