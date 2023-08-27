package com.hairsalon.respository;

import com.hairsalon.model.AppointmentModel;
import com.hairsalon.model.AppointmentStatusModel;


public interface IAppointmentStatusRepository {
    AppointmentStatusModel findById(Integer id);
}
