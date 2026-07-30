package com.nocountry.financeai.repository;

import com.nocountry.financeai.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // Utilizado en el Login para buscar al usuario
    Optional<UserEntity> findByEmail(String email);

    // Utilizado en el Registro para evitar correos duplicados
    boolean existsByEmail(String email);
}