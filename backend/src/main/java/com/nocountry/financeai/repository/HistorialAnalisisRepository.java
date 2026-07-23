package com.nocountry.financeai.repository;

import com.nocountry.financeai.model.HistorialAnalisis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialAnalisisRepository extends JpaRepository<HistorialAnalisis, Long> {

}
