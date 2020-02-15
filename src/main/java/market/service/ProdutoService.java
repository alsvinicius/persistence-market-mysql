package market.service;

import market.model.Produto;
import market.repository.ProdutoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository repository;

    public Produto inserir(Produto produto) {
        produto.setIdProduto(new ObjectId().toString());
        repository.insert(produto);
        return produto;
    }

    public Optional<Produto> alterar(String id, Produto produto) {
        produto.setIdProduto(id);
        repository.save(produto);
        return repository.findById(id);
    }

    public void excluir(String id) {
        Produto produto = new Produto();
        produto.setIdProduto(id);
        repository.delete(produto);
    }

    public Produto obter(String id) {
        Optional<Produto> optionalProduto = repository.findById(id);
        if (optionalProduto.isPresent()) {
            return optionalProduto.get();
        } else {
            return null;
        }
    }

    public List<Produto> listar() {
        return repository.findAll();
    }
}
