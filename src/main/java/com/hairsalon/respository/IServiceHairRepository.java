package com.hairsalon.respository;

import com.hairsalon.entity.ServiceHair;
import com.hairsalon.model.HairServiceModel;

import java.util.List;

public interface IServiceHairRepository {
    List<HairServiceModel> getAllService();
    HairServiceModel findById(Integer id);
    List<HairServiceModel> findByServiceName(String serviceName);
    Integer add(ServiceHair serviceHair);
    Integer update(ServiceHair serviceHair);
}
