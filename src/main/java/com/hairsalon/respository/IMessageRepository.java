package com.hairsalon.respository;

public interface IMessageRepository {
    String getMessage(Integer id);
    String getMessageByItemCode(String itemCode);
}
