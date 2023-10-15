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
        EmailDto emailDto;
        log.info("commonTreadData. IsStart {}", commonTreadData.getIsStart());
        while(commonTreadData.getIsStart()) {
            log.info("ReceivingEmailTread->new while");
            log.debug("ReceivingEmailTread->new while");
            try {
                emailDto = createEmailDto();
                // используем put вместо add, т.к. он будет ждать когда в очереди появится
                // свободное место, если очередь заполнена, в отличии от add которое в этом
                // случае выбросит исключение
                // Добавление делаем через класс commonTreadData
                commonTreadData.addToEmailQueue(emailDto);
                Thread.sleep(WRITE_TIME_OUT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("ReceivingEmailTread->finish");
    }

    /**
     * Получаем случайную строку из массива
     * @param array массив
     * @return
     */
    private String getValueFromArray(String[] array) {
        int size = array.length;
        Random rand = new Random();
        return array[rand.nextInt(size)];
    }

    /**
     * Создание случайного письма по одному из заранее закотовленных шаблоном
     * @return EmailDto
     */
    private EmailDto createEmailDto() {
        EmailDto emailDto = new EmailDto();
        String fullName = getValueFromArray(nameList);
        emailDto.setTo(getValueFromArray(emailList));
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
