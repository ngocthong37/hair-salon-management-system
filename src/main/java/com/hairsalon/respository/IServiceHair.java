package com.hairsalon.respository;

import com.hairsalon.entity.ServiceHair;
import com.hairsalon.model.HairServiceModel;

import java.util.List;

public interface IServiceHair {
    List<HairServiceModel> getAllService();
    HairServiceModel findById(Integer id);
    Integer add(ServiceHair serviceHair);
    Integer update(ServiceHair serviceHair);
}
