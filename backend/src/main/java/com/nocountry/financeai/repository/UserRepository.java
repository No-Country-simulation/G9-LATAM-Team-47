package com.nocountry.financeai.repository;

import com.nocountry.financeai.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // Eliminamos findByUsername y dejamos EXCLUSIVAMENTE findByEmail
    Optional<UserEntity> findByEmail(String email);
}