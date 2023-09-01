package com.hairsalon.respository;

import com.hairsalon.entity.AppointmentStatus;
import com.hairsalon.model.AppointmentModel;
import com.hairsalon.model.AppointmentStatusModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppointmentStatusRepository extends JpaRepository<AppointmentStatus, Integer> {
}
