// src/main/java/com/logistica/Logistica/controller/DestinatarioController.java
package com.logistica.Logistica.controller;

import com.logistica.Logistica.model.Destinatario;
import com.logistica.Logistica.service.DestinatarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.logistica.Logistica.service.FuncionarioService;
import com.logistica.Logistica.service.VeiculoService;

@Controller
@RequestMapping("/destinatarios")
public class DestinatarioController {

 @Autowired
 private DestinatarioService service;
 @Autowired
 private FuncionarioService funcionarioService;
@Autowired 
private VeiculoService veiculoService;

private void adicionarDadosDeEntrega(Model model) {
model.addAttribute("funcionarios", funcionarioService.listarTodos());
model.addAttribute("veiculos", veiculoService.listarTodos());
}

    // 1. Listar todos os destinatários
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("destinatarios", service.listarTodos());
        return "destinatario/lista";
    }

    // 2. Form de novo cadastro (GET) - VERSÃO CORRETA E MODIFICADA
    @GetMapping("/novo")
    public String exibirFormularioDeCadastro(Model model) {
        model.addAttribute("destinatario", new Destinatario());
        model.addAttribute("acao", "Cadastrar");
        adicionarDadosDeEntrega(model); // Carrega Funcionários e Veículos
        return "destinatario/form";
    }

    // 3. Salvar (POST)
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Destinatario destinatario, RedirectAttributes attributes) {
        // Se você não usou @Valid e BindingResult, o salvamento acontece aqui:
        service.salvar(destinatario);
        attributes.addFlashAttribute("mensagemSucesso", "Destinatário salvo com sucesso!");
        return "redirect:/destinatarios";
    }

    // 4. Form de edição (GET) - VERSÃO CORRETA E MODIFICADA
    @GetMapping("/editar/{id}")
    public String exibirFormularioDeEdicao(@PathVariable Long id, Model model, RedirectAttributes attributes) {
        return service.buscarPorId(id).map(destinatario -> {
            model.addAttribute("destinatario", destinatario);
            model.addAttribute("acao", "Editar");
            adicionarDadosDeEntrega(model); // Carrega Funcionários e Veículos
            return "destinatario/form";
        }).orElseGet(() -> {
            attributes.addFlashAttribute("mensagemErro", "Destinatário não encontrado.");
            return "redirect:/destinatarios";
        });
    }

    // 5. Deletar (GET)
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes attributes) {
        try {
            service.deletarPorId(id);
            attributes.addFlashAttribute("mensagemSucesso", "Destinatário excluído com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("mensagemErro", "Erro ao excluir: " + e.getMessage());
        }
        return "redirect:/destinatarios";
    }
}