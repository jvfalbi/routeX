// src/main/java/com/logistica/Logistica/repository/VeiculoRepository.java
package com.logistica.Logistica.repository; // Linha 1

import com.logistica.Logistica.model.Veiculo; // Linha 4
import org.springframework.data.jpa.repository.JpaRepository; // Linha 5
import org.springframework.stereotype.Repository; // Linha 6

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    // O Spring Data JPA cria a implementação automaticamente
}