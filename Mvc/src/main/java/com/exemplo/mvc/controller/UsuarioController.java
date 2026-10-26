package com.exemplo.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.exemplo.mvc.model.exception.RegraDeNegocioException;
import com.exemplo.mvc.model.service.UsuarioService;

/**
 * CONTROLLER: recebe a requisição, aciona o Model e escolhe a View.
 * Não tem regra de negócio nem SQL.
 */
@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarios;

    public UsuarioController(UsuarioService usuarios) {
        this.usuarios = usuarios;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarios.listar());
        return "usuarios"; // View: templates/usuarios.html
    }

    @PostMapping
    public String cadastrar(@RequestParam String nome, RedirectAttributes mensagens) {
        try {
            var usuario = usuarios.cadastrar(nome);
            mensagens.addFlashAttribute("sucesso", "Usuário " + usuario.getId() + " cadastrado.");
        } catch (RegraDeNegocioException e) { // regra violada no Model
            mensagens.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/usuarios";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam String id, RedirectAttributes mensagens) {
        try {
            return "redirect:/usuarios/" + Long.parseLong(id.trim());
        } catch (NumberFormatException e) { // validação da ENTRADA (formato)
            mensagens.addFlashAttribute("erro", "O id deve ser um número.");
            return "redirect:/usuarios";
        }
    }

    @GetMapping("/{id}")
    public String buscarPorId(@PathVariable Long id, Model model, RedirectAttributes mensagens) {
        try {
            model.addAttribute("usuario", usuarios.buscarPorId(id));
            return "usuario-detalhe"; // View: templates/usuario-detalhe.html
        } catch (RegraDeNegocioException e) {
            mensagens.addFlashAttribute("erro", e.getMessage());
            return "redirect:/usuarios";
        }
    }

    @PostMapping("/{id}")
    public String atualizar(@PathVariable Long id, @RequestParam String nome,
                            RedirectAttributes mensagens) {
        try {
            usuarios.atualizar(id, nome);
            mensagens.addFlashAttribute("sucesso", "Usuário atualizado.");
        } catch (RegraDeNegocioException e) {
            mensagens.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/usuarios/" + id;
    }

    @PostMapping("/{id}/deletar")
    public String deletar(@PathVariable Long id, RedirectAttributes mensagens) {
        try {
            usuarios.deletar(id);
            mensagens.addFlashAttribute("sucesso", "Usuário " + id + " deletado.");
        } catch (RegraDeNegocioException e) {
            mensagens.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/usuarios";
    }
}
