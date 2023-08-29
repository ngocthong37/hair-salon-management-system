package com.hairsalon.respository;

import org.springframework.web.multipart.MultipartFile;

public interface IEmailServiceRepository {
    String sendMail(String to, String[] cc, String subject, String body);
}
