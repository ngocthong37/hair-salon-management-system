package com.hairsalon.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "service_id")
    private ServiceHair serviceHair;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "apm_status_id")
    private AppointmentStatus appointmentStatus;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "salon_id")
    private Salon salon;
    @Column(name = "appointment_date")
    private LocalDate appointmentDate;
    @Column(name = "appointment_time")
    private LocalTime appointmentTime;
    @Column(name = "created_at")
    private Timestamp createAt;
    @Column(name = "updated_at")
    private Timestamp updateAt;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;


}
