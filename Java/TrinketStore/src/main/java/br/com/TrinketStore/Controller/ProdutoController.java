package br.com.TrinketStore.Controller;

import br.com.TrinketStore.Model.Produto;
import br.com.TrinketStore.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Criar produto
    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto) {
        return (Produto) produtoRepository.save(produto);
    }

    // Listar todos os produtos
    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    // Buscar produto por ID
    @GetMapping("/{id}")
    public Produto buscarProduto(@PathVariable Long id) {
        return (Produto) produtoRepository.findById(id).orElse(null);
    }

    // Atualizar produto
    @PutMapping("/{id}")
    public Produto atualizarProduto(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
        Produto produto = (Produto) produtoRepository.findById(id).orElse(null);
        if (produto != null) {
            produto.setNome(produtoAtualizado.getNome());
            produto.setDescricao(produtoAtualizado.getDescricao());
            produto.setPreco(produtoAtualizado.getPreco());
            produto.setId(produtoAtualizado.isAtivo());
            return (Produto) produtoRepository.save(produto);
        }
        return null;
    }

    // Deletar produto
    @DeleteMapping("/{id}")
    public String deletarProduto(@PathVariable Long id) {
        produtoRepository.deleteById(id);
        return "Produto removido com sucesso!";
    }
}