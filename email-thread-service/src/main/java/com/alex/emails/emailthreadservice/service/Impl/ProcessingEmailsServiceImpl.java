package com.alex.emails.emailthreadservice.service.Impl;

import com.alex.emails.emailthreadservice.service.ProcessingEmailsService;

/**
 * Сервис обработки входящих сообщений. Он достает полученные сообщения из
 * очереди и запускает в отдельном потоке сервис отправки каждого сообщения. Этот сервис так же
 * работает в отдельном потоке.
 */
public class ProcessingEmailsServiceImpl implements ProcessingEmailsService {
}
