package com.hairsalon.respository;

import com.hairsalon.model.AppointmentModel;
import com.hairsalon.model.AppointmentStatusModel;


public interface IAppointmentStatus {
    AppointmentStatusModel findById(Integer id);
}
