package com.hairsalon.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class ProductItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "product_item_name")
    private String productItemName;
    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "productItem", fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Collection<OrderItem> orderItems;

    @OneToMany(mappedBy = "productItem", fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Collection<CartItem> cartItems;

    @Column(name = "quantity_in_stock")
    private Integer quantityInStock;
    @Column(name = "warranty_time")
    private Integer warrantyTime;
    private String status;
    private String imageUrl;
    private String description;
}
