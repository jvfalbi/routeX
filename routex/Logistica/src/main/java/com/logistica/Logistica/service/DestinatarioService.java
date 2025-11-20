// src/main/java/com/logistica/Logistica/service/DestinatarioService.java
package com.logistica.Logistica.service;

import com.logistica.Logistica.model.Destinatario;
import com.logistica.Logistica.repository.DestinatarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinatarioService {

    @Autowired
    private DestinatarioRepository repository;

    public List<Destinatario> listarTodos() {
        return repository.findAll();
    }

    public Destinatario salvar(Destinatario destinatario) {
        return repository.save(destinatario);
    }

    public Optional<Destinatario> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void deletarPorId(Long id) {
        repository.deleteById(id);
    }
}