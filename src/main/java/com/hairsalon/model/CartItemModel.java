package com.hairsalon.model;

import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
public class CartItemModel {
    private Integer id;
    private String productName;
    private Integer quantity;
}
