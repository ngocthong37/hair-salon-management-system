package com.hairsalon.respository;

import com.hairsalon.entity.ServiceHair;
import com.hairsalon.model.HairServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceHairRepository extends JpaRepository<ServiceHair, Integer> {
    @Query(value = "SELECT S FROM ServiceHair S WHERE S.serviceName LIKE CONCAT('%', :serviceName, '%')")
    List<ServiceHair> findByPartialServiceName(@Param("serviceName") String serviceName);
}
