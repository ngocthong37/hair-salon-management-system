package com.hairsalon.respository;

import com.hairsalon.model.CustomerModel;

import java.util.List;

public interface ICustomer {
    List<CustomerModel> findAll();
    CustomerModel findById(Integer id);
}
