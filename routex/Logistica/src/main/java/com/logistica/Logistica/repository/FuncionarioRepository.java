package com.logistica.Logistica.repository;
import com.logistica.Logistica.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {}