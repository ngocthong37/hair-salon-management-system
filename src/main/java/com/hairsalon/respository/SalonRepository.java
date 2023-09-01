package com.hairsalon.respository;

import com.hairsalon.entity.Salon;
import com.hairsalon.model.SalonModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalonRepository extends JpaRepository<Salon, Integer> {
}
