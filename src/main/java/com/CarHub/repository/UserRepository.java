package com.CarHub.repository;

import com.CarHub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String user);
    Optional<User> findByEmailId(String email);
    Optional<User> findByMobile(String mobile);
}