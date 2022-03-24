package com.company.olx.service;

import com.company.olx.entity.EmailHistoryEntity;
import com.company.olx.enums.EmailStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String toAccount, String jwt) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Salom jigar qalaysan\n");
        stringBuilder.append("Agar bu sen bo'lsang shu linkga bos : ");
        stringBuilder.append("http://localhost:8080/profile/verification/" + jwt);

        String title = "Registration olx test";
        String text = stringBuilder.toString();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toAccount);
        message.setSubject(title);
        message.setText(text);
        javaMailSender.send(message);

    }

    public void createEmailHistory(SimpleMailMessage message){
        EmailHistoryEntity entity = new EmailHistoryEntity();
        entity.setFromAccount(message.getFrom());
        entity.setToAccount(Arrays.toString(message.getTo()));
        entity.setStatus(EmailStatus.NOT_USED);

    }





}
