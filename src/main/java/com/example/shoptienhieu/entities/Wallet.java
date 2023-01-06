package com.example.shoptienhieu.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "wallet")
@Setter
@Getter
@NoArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "wallet_id")
    private Integer id;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;


    public Integer deposit(Integer money) {
        this.money += money;
        return this.money;
    }

    public Integer withdraw(Integer money) {
        this.money -= money;
        return this.money;
    }
    private Integer money;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;

    public Wallet(User user) {
        this.user = user;
        this.money = 0;
        this.createdAt = new Date().getTime();
        this.updatedAt = new Date().getTime();
    }
}

