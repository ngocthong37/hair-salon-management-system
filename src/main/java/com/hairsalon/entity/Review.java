package com.hairsalon.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "rating")
    private Integer ratingValue;
    @Column(name = "comment")
    private String comment;


    @ManyToOne()
    @JoinColumn(name = "service_id")
    private ServiceHair serviceHair;
    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
