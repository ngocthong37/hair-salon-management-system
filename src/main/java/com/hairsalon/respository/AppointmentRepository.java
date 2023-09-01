package com.hairsalon.respository;

import com.hairsalon.entity.Appointment;
import com.hairsalon.model.AppointmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    @Query(value = "select A from Appointment A where A.appointmentStatus.id = :statusId")
    List<Appointment> findAppointmentByStatusId(@Param("statusId") Integer statusId);


    @Query(value = "select A from Appointment A where A.customer.id = :customerId")
    List<Appointment> findAppointmentByCustomerId(@Param("customerId") Integer customerId);

}
