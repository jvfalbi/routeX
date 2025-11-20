// src/main/java/com/logistica/Logistica/controller/FuncionarioController.java
package com.logistica.Logistica.controller;

import com.logistica.Logistica.model.Funcionario;
import com.logistica.Logistica.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("funcionarios", service.listarTodos());
        return "funcionario/lista"; 
    }

    @GetMapping("/novo")
    public String exibirFormulario(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        model.addAttribute("acao", "Cadastrar");
        return "funcionario/form"; 
    }

    @GetMapping("/editar/{id}")
    public String exibirFormularioEdicao(@PathVariable Long id, Model model) {
        Funcionario funcionario = service.buscarPorId(id)
            .orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));
        model.addAttribute("funcionario", funcionario);
        model.addAttribute("acao", "Editar");
        return "funcionario/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute @Valid Funcionario funcionario, 
                         BindingResult result, 
                         RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagemErro", "Erro na validação do formulário.");
            return "funcionario/form"; 
        }
        service.salvar(funcionario);
        attributes.addFlashAttribute("mensagemSucesso", "Funcionário salvo com sucesso!");
        return "redirect:/funcionarios";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes attributes) {
        service.deletarPorId(id);
        attributes.addFlashAttribute("mensagemSucesso", "Funcionário excluído com sucesso!");
        return "redirect:/funcionarios";
    }
}