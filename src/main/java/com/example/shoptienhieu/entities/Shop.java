package com.example.shoptienhieu.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "shop")
@Setter
@Getter
@NoArgsConstructor
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_inform_id",referencedColumnName = "id")
    private User user;

    @Column(length = 45)
    private String name;

    @Column(precision = 2, scale = 1)
    @Type(type = "big_decimal")
    private BigDecimal rating;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id",referencedColumnName = "id")
    City city;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;

    public Shop(User user) {
        this.user = user;
        this.name = "Shop" + user.getId();
        this.rating = new BigDecimal(0);
        this.createdAt = new Date().getTime();
        this.updatedAt = new Date().getTime();
    }




}
