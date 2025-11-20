// src/main/java/com/logistica/Logistica/controller/VeiculoController.java
package com.logistica.Logistica.controller;

import com.logistica.Logistica.model.Veiculo;
import com.logistica.Logistica.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
// ... imports ...

@Controller
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService service;
    
    // Métodos listar, novo, editar, salvar, deletar (semelhante ao FuncionarioController)
    // O mapeamento será /veiculos e /veiculos/novo
    
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("veiculos", service.listarTodos());
        return "veiculo/lista"; 
    }
    
    @GetMapping("/novo")
    public String exibirFormulario(Model model) {
        model.addAttribute("veiculo", new Veiculo());
        model.addAttribute("acao", "Cadastrar");
        return "veiculo/form"; 
    }
    
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute @Valid Veiculo veiculo, 
                         BindingResult result, 
                         RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "veiculo/form"; 
        }
        service.salvar(veiculo);
        attributes.addFlashAttribute("mensagemSucesso", "Veículo salvo com sucesso!");
        return "redirect:/veiculos";
    }
    // ... Implementar /editar/{id} e /deletar/{id} ...
}