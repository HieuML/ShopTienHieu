package com.example.shoptienhieu.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "origin")
@Setter
@Getter
@NoArgsConstructor
public class Origin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "origin_id")
    private Integer id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;

    public Origin(String name) {
        this.name = name;
        this.createdAt = new Date().getTime();
        this.updatedAt = new Date().getTime();
    }
}
