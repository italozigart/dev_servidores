package br.com.fatec.catalogo.controllers;

import br.com.fatec.catalogo.models.CategoriaModel;
import br.com.fatec.catalogo.services.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/categorias")
    public String listarCategorias(Model model) {
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "lista-categorias";
    }

    @GetMapping("/categorias/nova")
    public String abrirCadastro(Model model) {
        model.addAttribute("categoria", new CategoriaModel());
        return "cadastro-categoria";
    }

    @PostMapping("/categorias/nova")
    public String salvarCategoria(@ModelAttribute CategoriaModel categoria, Model model) {
        try {
            categoriaService.salvar(categoria);
            return "redirect:/categorias";
        } catch (RuntimeException e) {
            model.addAttribute("categoria", categoria);
            model.addAttribute("erro", e.getMessage());
            return "cadastro-categoria";
        }
    }

    @GetMapping("/categorias/editar/{id}")
    public String abrirEdicao(@PathVariable Long id, Model model) {
        model.addAttribute("categoria", categoriaService.buscarPorId(id));
        return "editar-categoria";
    }

    @PostMapping("/categorias/editar/{id}")
    public String atualizarCategoria(@PathVariable Long id,
                                     @ModelAttribute CategoriaModel categoria,
                                     Model model) {
        try {
            categoria.setIdCategoria(id);
            categoriaService.salvar(categoria);
            return "redirect:/categorias";
        } catch (RuntimeException e) {
            model.addAttribute("categoria", categoria);
            model.addAttribute("erro", e.getMessage());
            return "editar-categoria";
        }
    }

    @GetMapping("/categorias/excluir/{id}")
    public String excluirCategoria(@PathVariable Long id) {
        categoriaService.excluir(id);
        return "redirect:/categorias";
    }
}