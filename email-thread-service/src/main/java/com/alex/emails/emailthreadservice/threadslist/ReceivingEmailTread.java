package com.alex.emails.emailthreadservice.threadslist;

import com.alex.emails.emailthreadservice.data.CommonTreadData;
import com.example.commondata.dto.EmailDto;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

import static com.example.commondata.data.EmailConstants.*;
import static com.example.commondata.data.EmailVariable.emailList;
import static com.example.commondata.data.EmailVariable.nameList;


/**
 * Поток для сервиса имитации отправки писем из стороннего источника
 */
@Slf4j
public class ReceivingEmailTread extends Thread {

    private CommonTreadData commonTreadData;

    public ReceivingEmailTread(CommonTreadData commonTreadData) {
        this.commonTreadData = commonTreadData;
    }

    public void run() {
        log.info("ReceivingEmailTread->start");
        BlockingQueue<EmailDto> emailQueue = commonTreadData.getEmailQueue();
        EmailDto emailDto;

        while(commonTreadData.getIsStart().get()) {
            log.debug("ReceivingEmailTread->new while");
            try {
                emailDto = createEmailDto();
                // TODO проверить как работает put, при необходимости заменить на offer с
                //  ожиданием, чтобы не блокировать на долго поток
                // используем put вместо add, т.к. он будет ждать когда в очереди появится
                // свободное место, если очередь заполнена, в отличии от add которое в этом
                // случае выбросит исключение
                emailQueue.put(emailDto);
                Thread.sleep(WRITE_TIME_OUT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("ReceivingEmailTread->finish");
    }

    private String getValueFromArray(String[] array, Integer size) {
        Random rand = new Random();
        return array[rand.nextInt(size)];
    }

    private EmailDto createEmailDto() {
        EmailDto emailDto = new EmailDto();
        String fullName = getValueFromArray(nameList, COUNT_NAMES);
        emailDto.setTo(getValueFromArray(emailList, COUNT_EMAILS));
        emailDto.setSubject(EMAIL_SUBJECT);
        emailDto.setFullName(fullName);
        emailDto.setBody(BODY_MESSAGE);
        emailDto.setCode(generateCode());
        return emailDto;
    }

    private static Integer generateCode(){
        Random rand = new Random();
        return rand.nextInt(CODE_MAX_VALUE);
    }

}
