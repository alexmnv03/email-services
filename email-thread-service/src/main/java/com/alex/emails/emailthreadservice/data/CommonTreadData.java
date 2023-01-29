package com.alex.emails.emailthreadservice.data;

import com.example.commondata.dto.EmailDto;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.example.commondata.data.EmailConstants.CAPACITY_QUEUE;

@Component
public class CommonTreadData {

    private AtomicBoolean isStart;

    private BlockingQueue<EmailDto> emailQueue;

    public CommonTreadData() {
        this.emailQueue = new ArrayBlockingQueue<>(CAPACITY_QUEUE, true);;
    }

    public BlockingQueue<EmailDto> getEmailQueue() {
        return emailQueue;
    }

    public void setEmailQueue(BlockingQueue<EmailDto> emailQueue) {
        this.emailQueue = emailQueue;
    }

    public AtomicBoolean getIsStart() {
        return isStart;
    }

    public void setIsStart(AtomicBoolean isStart) {
        this.isStart = isStart;
    }

    public void addToEmailQueue(EmailDto emailDto) {
        try {
            emailQueue.put(emailDto);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public EmailDto getFromEmailQueue() {
        try {
            return emailQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

}
