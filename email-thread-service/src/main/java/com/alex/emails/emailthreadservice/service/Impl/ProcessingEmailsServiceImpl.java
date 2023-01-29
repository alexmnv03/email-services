package com.alex.emails.emailthreadservice.service.Impl;

import com.alex.emails.emailthreadservice.data.CommonTreadData;
import com.alex.emails.emailthreadservice.service.ProcessingEmailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * Сервис обработки входящих сообщений. Он достает полученные сообщения из
 * очереди и запускает в отдельном потоке сервис отправки каждого сообщения. Этот сервис так же
 * работает в отдельном потоке.
 */
@Slf4j
@Service
public class ProcessingEmailsServiceImpl implements ProcessingEmailsService {

    private CommonTreadData commonTreadData;

    public ProcessingEmailsServiceImpl(CommonTreadData commonTreadData) {
        this.commonTreadData = commonTreadData;
    }

    /**
     * Запускаем сервис обработки входящих сообщений
     */
    public void LaunchProcessingEmailService() {
        log.info("start LaunchProcessingEmailService ->>");

//        commonTreadData.setIsStart(new AtomicBoolean((true)));
//        new ReceivingEmailTread(commonTreadData).start();
    }

}
