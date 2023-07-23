package com.hairsalon.respository;

import com.hairsalon.model.SalonModel;

import java.util.List;

public interface ISalon {
    List<SalonModel> findAll();
    SalonModel findById(Integer id);
}
