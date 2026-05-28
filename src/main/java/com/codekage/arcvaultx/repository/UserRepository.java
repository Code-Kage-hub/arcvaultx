package com.codekage.arcvaultx.repository;

import com.codekage.arcvaultx.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
