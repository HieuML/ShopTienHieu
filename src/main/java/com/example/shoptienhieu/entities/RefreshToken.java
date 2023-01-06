package com.example.shoptienhieu.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;


@Setter
@Getter
@NoArgsConstructor
@Entity(name = "refreshToken")
public class RefreshToken {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
//  @Column(name = "refresh_token_id")
  private Integer id;

  @OneToOne
  @JoinColumn(name = "user_inform_id", referencedColumnName = "id")
  private User user;

  @Column(nullable = false, unique = true)
  private String token;

  @Column(nullable = false)
  private Instant expiryDate;

  @Column(name = "created_at")
  private Long createdAt;

  @Column(name = "updated_at")
  private Long updatedAt;







}
