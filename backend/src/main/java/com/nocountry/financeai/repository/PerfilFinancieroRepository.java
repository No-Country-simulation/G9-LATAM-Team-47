package com.nocountry.financeai.repository;

import com.nocountry.financeai.entity.PerfilFinancieroEntity;
import com.nocountry.financeai.entity.enums.PerfilFinanciero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilFinancieroRepository extends JpaRepository<PerfilFinancieroEntity, Long> {
    Optional<PerfilFinancieroEntity> findByUsuarioId(Long usuarioId);

}
