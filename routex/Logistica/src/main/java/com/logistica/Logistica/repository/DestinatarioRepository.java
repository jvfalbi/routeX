// src/main/java/com/logistica/Logistica/repository/DestinatarioRepository.java
package com.logistica.Logistica.repository;

import com.logistica.Logistica.model.Destinatario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinatarioRepository extends JpaRepository<Destinatario, Long> {

    public List<Destinatario> findAll();
    // Métodos CRUD básicos herdados
}