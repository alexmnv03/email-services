package com.alex.emails.emailthreadservice.threadslist;

import com.alex.emails.emailthreadservice.data.CommonTreadData;
import com.example.commondata.dto.EmailDto;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

import static com.example.commondata.data.EmailConstants.EMAIL_SUBJECT;

@Slf4j
public class ReceivingEmailTread extends Thread {

    // TODO: Переенсти в пакет с данными EmailConstants
    private static final Integer WRITE_TIME_OUT = 30000;
    private static final Integer READ_TIME_OUT = 40000;
    private static final Integer COUNT_NAMES = 40;
    private static final Integer COUNT_EMAILS = 6;
    private static final Integer CODE_MAX_VALUE = 99999;
    private static final String BODY_MESSAGE = "You received this email because an account with " +
            "this email address was created in the 'Name company' service.";

    // TODO: Переенсти в пакет с данными EmailVariable
    private String[] nameList = {
            "Emily",
            "Hannah",
            "Kaitlyn",
            "Sarah",
            "Madison",
            "Brianna",
            "Kaylee",
            "Hailey",
            "Alexis",
            "Elizabeth",
            "Taylor",
            "Lauren",
            "Ashley",
            "Katherine",
            "Jessica",
            "Anna",
            "Samantha",
            "Alissa",
            "Kayla",
            "Madeline",
            "Michael",
            "James",
            "Matthew",
            "Nicholas",
            "Christopher",
            "Joseph",
            "Zachary",
            "Joshua",
            "Andrew",
            "William",
            "Daniel",
            "Christian",
            "Tyler",
            "Ryan",
            "Anthony",
            "Alexander",
            "Jonathan",
            "David",
            "Dylan",
            "Jacob"
    };

    // TODO: Переенсти в пакет с данными EmailVariable
    private String[] emailList = {
            "alex@gmail.com",
            "lysan@gmail.com"
    };

    private CommonTreadData commonTreadData;

    public ReceivingEmailTread(CommonTreadData commonTreadData) {
        this.commonTreadData = commonTreadData;
    }

    public void run() {
        log.info("ReceivingEmailTread->start");
        BlockingQueue<EmailDto> emailQueue = commonTreadData.getEmailQueue();
        EmailDto emailDto;

        while(commonTreadData.getIsStart().get()) {
            log.info("ReceivingEmailTread->while");
            try {
                emailDto = createEmailDto();
                emailQueue.add(emailDto);
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
