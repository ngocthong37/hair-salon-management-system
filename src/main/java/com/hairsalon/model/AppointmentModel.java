package com.hairsalon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AppointmentModel {
    private Integer id;
    private CustomerModel customerModel;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private HairServiceModel hairServiceModel;
    private AppointmentStatusModel appointmentStatusModel;
    private SalonModel salonModel;
    private UserModel userModel;
}
