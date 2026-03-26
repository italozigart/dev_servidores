package br.com.fatec.catalogo.controllers;

import br.com.fatec.catalogo.models.ProdutoModel;
import br.com.fatec.catalogo.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping("/")
    public String redirecionarParaProdutos() {
        return "redirect:/produtos";
    }

    @GetMapping("/produtos")
    public String listarProdutos(@RequestParam(required = false) String nome, Model model) {
        model.addAttribute("produtos", service.buscarPorNome(nome));
        model.addAttribute("nome", nome);
        return "lista-produtos";
    }

    @GetMapping("/produtos/novo")
    public String abrirCadastro(Model model) {
        model.addAttribute("produto", new ProdutoModel());
        return "cadastro-produto";
    }

    @PostMapping("/produtos/novo")
    public String salvarProduto(@ModelAttribute ProdutoModel produto) {
        service.salvar(produto);
        return "redirect:/produtos";
    }

    @GetMapping("/produtos/editar/{id}")
    public String abrirEdicao(@PathVariable Long id, Model model) {
        ProdutoModel produto = service.buscarPorId(id);
        model.addAttribute("produto", produto);
        return "editar-produto";
    }

    @PostMapping("/produtos/editar/{id}")
    public String atualizarProduto(@PathVariable Long id, @ModelAttribute ProdutoModel produto) {
        produto.setIdProduto(id);
        service.salvar(produto);
        return "redirect:/produtos";
    }

    @GetMapping("/produtos/excluir/{id}")
    public String excluirProduto(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/produtos";
    }
}