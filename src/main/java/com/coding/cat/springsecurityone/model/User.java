package com.coding.cat.springsecurityone.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id // primary key
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String username;
  private String password;
  private String email;
  private String role; //ROLE_USER, ROLE_ADMIN

  // OAuth를 위해 구성한 추가 필드 2개
  private String provider;
  private String providerId;

  @CreationTimestamp
  private Timestamp createDate;
}