package com.alex.emails.emailthreadservice;

import com.alex.emails.emailthreadservice.service.ProcessingEmailsService;
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

    @Resource
    ProcessingEmailsService processingEmailsService;


    public static void main(String[] args) {
        SpringApplication.run(EmailThreadServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Можно было здесь написать код, но для демонстрации
        // работы с потоками все таки вынесем его в отдельный процесс
        // Тут запускается отдельный поток формирования сообщений,
        receiveEmailsService.LaunchReceivingEmailService();
        // Тут запускается еще один отдельный поток по обработке входящих сообщений
        processingEmailsService.LaunchProcessingEmailService();
    }
}
