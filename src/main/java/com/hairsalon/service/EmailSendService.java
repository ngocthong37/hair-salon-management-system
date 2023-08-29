package com.hairsalon.service;

import com.hairsalon.respository.imp.EmailServiceRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailSendService {
    @Autowired
    EmailServiceRepositoryImp emailServiceRepositoryImp;

    public String sendMail(String to, String[] cc, String subject, String body) {
        return emailServiceRepositoryImp.sendMail(to, cc, subject, body);
    }

}
