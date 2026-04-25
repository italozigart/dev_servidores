package br.com.fatec.catalogo.services;

import br.com.fatec.catalogo.models.ProdutoModel;
import br.com.fatec.catalogo.repositories.CategoriaRepository;
import br.com.fatec.catalogo.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository,
                          CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<ProdutoModel> listarTodos() {
        return produtoRepository.findAll();
    }

    public List<ProdutoModel> buscarPorNome(String nome) {
        if (nome == null || nome.isBlank()) {
            return produtoRepository.findAll();
        }

        return produtoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public ProdutoModel buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + id));
    }

    public ProdutoModel salvar(ProdutoModel produto) {
        if (produto.getNome() == null || produto.getNome().isBlank()) {
            throw new RuntimeException("O nome do produto é obrigatório.");
        }

        if (produto.getValor() == null) {
            throw new RuntimeException("O valor do produto é obrigatório.");
        }

        if (produto.getCategoria() == null || produto.getCategoria().getIdCategoria() == null) {
            throw new RuntimeException("A categoria do produto é obrigatória.");
        }

        categoriaRepository.findById(produto.getCategoria().getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));

        if (produto.getIdProduto() != null) {
            ProdutoModel produtoExistente = produtoRepository.findById(produto.getIdProduto())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

            produto.setDataCadastro(produtoExistente.getDataCadastro());
        }

        return produtoRepository.save(produto);
    }

    public void excluir(Long id) {
        produtoRepository.deleteById(id);
    }
}