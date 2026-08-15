package com.nocountry.financeai.repository;

import com.nocountry.financeai.entity.HistorialAnalisisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistorialAnalisisRepository extends JpaRepository<HistorialAnalisisEntity, Long> {
    List<HistorialAnalisisEntity> findByUsuarioId(Long id);
    // Usado para "pull" el análisis más reciente sin recorrer toda la lista
    Optional<HistorialAnalisisEntity> findFirstByUsuarioIdOrderByFechaAnalisisDesc(Long usuarioId);
}
