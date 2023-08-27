package com.hairsalon.constants;

import com.hairsalon.respository.imp.MessageRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Message {
    @Autowired
    MessageRepositoryImp messageRepositoryImpl;

    public String getMessage(Integer id) {
        return messageRepositoryImpl.getMessage(id);
    }
    public String getMessageByItemCode(String itemCode) {
        return messageRepositoryImpl.getMessageByItemCode(itemCode);
    }


}
