package br.com.fatec.catalogo.services;

import br.com.fatec.catalogo.models.ProdutoModel;
import br.com.fatec.catalogo.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<ProdutoModel> listarTodos() {
        return repository.findAll();
    }

    public List<ProdutoModel> buscarPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return repository.findAll();
        }

        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public ProdutoModel buscarPorId(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public ProdutoModel salvar(ProdutoModel produto) {
        return repository.save(produto);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}