package com.hairsalon.service;

import com.hairsalon.respository.EmailServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmailSendService {
    @Autowired
    EmailServiceImp emailServiceImp;

    public String sendMail(String to, String[] cc, String subject, String body) {
        return emailServiceImp.sendMail(to, cc, subject, body);
    }

}
