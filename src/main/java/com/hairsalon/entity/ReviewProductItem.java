package com.hairsalon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class ReviewProductItem extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "rating")
    private Integer ratingValue;
    @Column(name = "comment")
    private String comment;
    @ManyToOne()
    @JoinColumn(name = "product_item_id")
    private ProductItem productItem;
    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private User customer;
}
