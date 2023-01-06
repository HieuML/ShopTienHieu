package com.example.shoptienhieu.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product_comment")
@Setter
@Getter
@NoArgsConstructor
public class ProductComment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "product_comment_id")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_inform_id",referencedColumnName = "id")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false,referencedColumnName = "id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
    private Product product;

    private String content;

    private String image;


    @Column(precision = 2, scale = 1)
    @Type(type = "big_decimal")
    private Float rating;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;

    public ProductComment(User user, Product product, String content, String image, float rating) {
        this.user = user;
        this.product = product;
        this.content = content;
        this.image = image;
        this.rating = rating;
        this.createdAt = new Date().getTime();
        this.updatedAt = new Date().getTime();
    }
}
