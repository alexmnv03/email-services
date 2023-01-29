package com.alex.emails.emailthreadservice.threadslist;

import com.alex.emails.emailthreadservice.data.CommonTreadData;
import com.example.commondata.dto.EmailDto;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;

import static com.example.commondata.data.EmailConstants.WRITE_TIME_OUT;

/**
 * Поток для сервиса обработки входящих сообщений. Как только он получает входящее сообщение, он
 * формирует новый поток на отправку ответа на это сообщение
 */
@Slf4j
public class ProcessingEmailTread extends Thread {

    private CommonTreadData commonTreadData;

    public ProcessingEmailTread(CommonTreadData commonTreadData) {
        this.commonTreadData = commonTreadData;
    }

    public void run() {
        log.info("ProcessingEmailTread->start");
        BlockingQueue<EmailDto> emailQueue = commonTreadData.getEmailQueue();
        while(commonTreadData.getIsStart().get()) {
            log.debug("ProcessingEmailTread->new while");





//            try {
//                emailDto = createEmailDto();
//                emailQueue.add(emailDto);
//                Thread.sleep(WRITE_TIME_OUT);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        log.info("ReceivingEmailTread->finish");
    }

}
