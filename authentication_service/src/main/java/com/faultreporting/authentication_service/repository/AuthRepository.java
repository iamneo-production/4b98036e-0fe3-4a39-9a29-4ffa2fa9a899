package com.faultreporting.authentication_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faultreporting.authentication_service.model.User;
import com.google.common.base.Optional;

public interface AuthRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
