package com.alex.emails.emailthreadservice.service.Impl;

import com.alex.emails.emailthreadservice.data.CommonTreadData;
import com.alex.emails.emailthreadservice.service.ReceiveEmailsService;
import com.alex.emails.emailthreadservice.threadslist.ReceivingEmailTread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Сервис в отдельном потоке имитирует сторонний источник отправки писем нашему приложению а так же
 * складывает их в очередь
 */
@Slf4j
@Service
public class ReceiveEmailsServiceImpl implements ReceiveEmailsService {

    private CommonTreadData commonTreadData;
    private ReceivingEmailTread receivingEmailTread;

    public ReceiveEmailsServiceImpl(CommonTreadData commonTreadData) {
        this.commonTreadData = commonTreadData;
    }

    /**
     * Запускаем сервис имитации формирования сообщений от стороннего источика
     */
    public void LaunchReceivingEmailService() {
        log.info("start LaunchReceivingEmailService ->>");
        commonTreadData.setIsStart(new AtomicBoolean((true)));
        new ReceivingEmailTread(commonTreadData).start();
    }
}
