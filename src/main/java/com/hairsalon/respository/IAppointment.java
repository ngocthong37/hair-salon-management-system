package com.hairsalon.respository;

import com.hairsalon.entity.Appointment;
import com.hairsalon.entity.AppointmentStatus;
import com.hairsalon.model.AppointmentModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Repository
public interface IAppointment {
    List<AppointmentModel> getAll();
    Integer insert(Appointment appointment);
    AppointmentModel findById(Integer id);
    List<AppointmentModel> getAllByStatusId(Integer id);
    List<AppointmentModel> getAllByCustomerId(Integer id);
    Integer updateStatusAppointment(Appointment appointment);
    Appointment findAppointmentById(Integer id);

}
