package com.coding.cat.springsecurityone.repository;

import com.coding.cat.springsecurityone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// CRUD 함수를 JPA Repository가 들고 있음
public interface UserRepository extends JpaRepository<User, Long> {
  // findBy 규칙
  public User findByUsername(String username);
}
