package com.alex.emails.emailthreadservice.threadslist;

import com.alex.emails.emailthreadservice.data.CommonTreadData;
import com.example.commondata.dto.EmailDto;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;

import static com.example.commondata.data.EmailConstants.READ_TIME_OUT;
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
            // TODO проверить как работает take, при необходимости заменить на offer с ожиданием,
            //  чтобы не блокировать на долго поток
            // используем take вместо remove, т.к. он будет ждать когда в очереди появится
            // новый элемент, если очередь пустая, в отличии от remove которое в этом
            // случае выбросит исключение и для remove нужно выполнить сначала чтение, т.к. он
            // просто удаляет элемент
            try {
                EmailDto emailDto = emailQueue.take();
                // Запускаем поток отправки ответного сообщения

                // TODO добавить код после добавления нового сервиса



                Thread.sleep(READ_TIME_OUT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



        }
        log.info("ReceivingEmailTread->finish");
    }

}
