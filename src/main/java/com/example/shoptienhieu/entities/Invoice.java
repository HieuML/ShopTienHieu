package com.example.shoptienhieu.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "invoice")
@Setter
@Getter
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(generator = "my_generator")
    @GenericGenerator(name = "my_generator", parameters = {@org.hibernate.annotations.Parameter(name = "prefix", value = "OD")}, strategy = "com.example.shoptienhieu.utils.generatorIDHandler")
//    @Column(name ="invoice_id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_inform_id", nullable = false, referencedColumnName = "id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "delivery_id", nullable = false, referencedColumnName = "id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Delivery delivery;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_id", nullable = false,referencedColumnName = "id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shop_id", nullable = false,referencedColumnName = "id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Shop shop;

    private String status;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "shipping_fee")
    private Integer shippingFee;

    @Column(name = "total_amount")
    private Integer totalAmount;

    @Column(name = "paid_amount")
    private Integer paidAmount;

    @Column(name = "receiver_phone", length = 20)
    private String receiverPhone;

    @Column(name = "receiver_name", length = 100)
    private String receiverName;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;

    public Invoice(User user, Delivery delivery, Payment payment, Shop shop, String status, String shippingAddress
            , Integer shippingFee, String receiverPhone, String receiverName) {
        this.user = user;
        this.delivery = delivery;
        this.payment = payment;
        this.shop = shop;
        this.status = status;
        this.shippingAddress = shippingAddress;
        this.shippingFee = shippingFee;
        this.receiverPhone = receiverPhone;
        this.receiverName = receiverName;
        this.paidAmount = 0;
        this.createdAt = new Date().getTime();
        this.updatedAt = new Date().getTime();
    }
}
