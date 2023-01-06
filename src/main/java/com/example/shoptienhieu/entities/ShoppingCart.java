package com.example.shoptienhieu.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "shopping_cart")
@Setter
@Getter
@NoArgsConstructor
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "shop_cart_id")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_inform_id",referencedColumnName = "id")
    private User user;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;

    public ShoppingCart(User user) {
        this.user = user;
        this.createdAt = new Date().getTime();
        this.updatedAt = new Date().getTime();
    }


}
