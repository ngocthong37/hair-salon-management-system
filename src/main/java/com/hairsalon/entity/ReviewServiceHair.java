package com.hairsalon.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class ReviewServiceHair extends BaseEntity {
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
    private User customer;
}
