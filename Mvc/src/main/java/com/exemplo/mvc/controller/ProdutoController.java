package com.exemplo.mvc.controller;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.exemplo.mvc.model.service.ProdutoService;
import com.exemplo.mvc.model.exception.RegraDeNegocioException;

/**
 * CONTROLLER: recebe a requisição, aciona o Model e escolhe a View.
 * Não tem regra de negócio e nem SQL: só converte a entrada e coordena o fluxo.
 */
@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtos;

    public ProdutoController(ProdutoService produtos) {
        this.produtos = produtos;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("produtos", produtos.listar());
        return "produtos"; // View: templates/produtos.html
    }

    @PostMapping
    public String cadastrar(@RequestParam String nome, @RequestParam String preco,
                            @RequestParam String idUsuario, RedirectAttributes mensagens) {
        try {
            var produto = produtos.cadastrar(nome, lerPreco(preco), Long.parseLong(idUsuario.trim()));
            mensagens.addFlashAttribute("sucesso", "Produto " + produto.getId() + " cadastrado.");
        } catch (NumberFormatException e) { // validação da ENTRADA (formato)
            mensagens.addFlashAttribute("erro", "Preço e id do usuário devem ser números.");
        } catch (RegraDeNegocioException e) { // regra violada no Model
            mensagens.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/produtos";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam String id, RedirectAttributes mensagens) {
        try {
            return "redirect:/produtos/" + Long.parseLong(id.trim());
        } catch (NumberFormatException e) { // validação da ENTRADA (formato)
            mensagens.addFlashAttribute("erro", "O id deve ser um número.");
            return "redirect:/produtos";
        }
    }

    @GetMapping("/{id}")
    public String buscarPorId(@PathVariable Long id, Model model, RedirectAttributes mensagens) {
        try {
            model.addAttribute("produto", produtos.buscarPorId(id));
            return "produto-detalhe"; // View: templates/produto-detalhe.html
        } catch (RegraDeNegocioException e) {
            mensagens.addFlashAttribute("erro", e.getMessage());
            return "redirect:/produtos";
        }
    }

    @PostMapping("/{id}")
    public String atualizar(@PathVariable Long id, @RequestParam String nome,
                            @RequestParam String preco, @RequestParam String idUsuario,
                            RedirectAttributes mensagens) {
        try {
            produtos.atualizar(id, nome, lerPreco(preco), Long.parseLong(idUsuario.trim()));
            mensagens.addFlashAttribute("sucesso", "Produto atualizado.");
        } catch (NumberFormatException e) {
            mensagens.addFlashAttribute("erro", "Preço e id do usuário devem ser números.");
        } catch (RegraDeNegocioException e) {
            mensagens.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/produtos/" + id;
    }

    @PostMapping("/{id}/deletar")
    public String deletar(@PathVariable Long id, RedirectAttributes mensagens) {
        try {
            produtos.deletar(id);
            mensagens.addFlashAttribute("sucesso", "Produto " + id + " deletado.");
        } catch (RegraDeNegocioException e) {
            mensagens.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/produtos";
    }

    /** Converte o texto digitado (aceita vírgula ou ponto). A regra do preço fica no Model. */
    private BigDecimal lerPreco(String texto) {
        return new BigDecimal(texto.trim().replace(",", "."));
    }
}
