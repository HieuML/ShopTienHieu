package com.example.shoptienhieu.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cart_item")
@Setter
@Getter
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "cart_item_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shoppingCart_id", nullable = false,referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ShoppingCart shoppingCart;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product product;

    private Integer quantity;

    @Column(name = "is_check")
    private Boolean isCheck;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;

    public CartItem(ShoppingCart shoppingCart, Product product, int quantity, boolean isCheck) {
        this.shoppingCart = shoppingCart;
        this.product = product;
        this.quantity = quantity;
        this.isCheck = isCheck;
        this.createdAt = new Date().getTime();
        this.updatedAt = new Date().getTime();
    }
}
