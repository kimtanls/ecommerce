package com.example.Ecommerce.repository;

import com.example.Ecommerce.entity.User;
import com.example.Ecommerce.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    Optional<User> findByUserRole(UserRole userRole);

    User findByUsername(String username);

    User findByEmail(String email);
}
