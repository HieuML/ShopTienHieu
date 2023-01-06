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
@Table(name = "shipping_address")
@Setter
@Getter
@NoArgsConstructor
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false,referencedColumnName = "id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
    User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_id", nullable = false, referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    City city;

    private boolean type;
    @Column(name = "is_default")
    private boolean isDefault;
    @Column(name = "address_detail")
    private String addressDetail;


    @Column(length = 20)
    private String phone;
    @Column(name = "created_at")
    private Long createdAt;
    @Column(name = "update_at")
    private Long updatedAt;

    public ShippingAddress(User user, City city, boolean type, boolean isDefault, String addressDetail, String phone) {
        this.user = user;
        this.city = city;
        this.type = type;
        this.isDefault = isDefault;
        this.addressDetail = addressDetail;
        this.phone = phone;
        this.createdAt = new Date().getTime();
        this.updatedAt = new Date().getTime();
    }
}
