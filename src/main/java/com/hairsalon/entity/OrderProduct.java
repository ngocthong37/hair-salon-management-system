package com.hairsalon.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_product")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "product_item_id")
    private ProductItem productItem;
    @Column(name = "quantity")
    private Integer quantity;
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;

    @JsonIgnore
    @OneToMany(mappedBy = "orderProduct", fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Collection<OrderHistory> orderHistories;
}
