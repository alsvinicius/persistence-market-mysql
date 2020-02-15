package market.service;

import market.model.Pedido;
import market.model.Produto;
import market.repository.PedidoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository repository;

    @Autowired
    ProdutoService produtoService;

    public Pedido inserir(String idCliente, Pedido pedido) {
        pedido.setIdCliente(new ObjectId(idCliente));
        pedido.setIdPedido(new ObjectId().toString());
        pedido.setProdutos(new ArrayList<Produto>());
        repository.insert(pedido);
        return pedido;
    }

    public Pedido adicionarProduto(String idPedido, String idProduto) {
        Produto produto = produtoService.obter(idProduto);
        if(produto == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Produto não existente");
        } else {
            if (produto.getQuantidade() <= 0) {
                throw new HttpClientErrorException(HttpStatus.PRECONDITION_FAILED, "Produto não possui quantidade disponível");
            } else {
                produto.setQuantidade(produto.getQuantidade()-1);
                produtoService.alterar(produto.getIdProduto(), produto);
            }
        }

        Pedido pedido = repository.findById(idPedido).get();
        pedido.getProdutos().add(produto);
        repository.save(pedido);
        adicionarValorProduto(idPedido, produto.getValor());
        return pedido;
    }

    public Optional<Pedido> alterar(String idCliente, String id, Pedido pedido) {
        pedido.setIdCliente(new ObjectId(idCliente));
        pedido.setIdPedido(id);
        repository.save(pedido);
        return repository.findById(id);
    }

    public void excluir(String idCliente, String id) {
        Pedido pedido = new Pedido();
        pedido.setIdCliente(new ObjectId(idCliente));
        pedido.setIdPedido(id);
        repository.delete(pedido);
    }

    public List<Pedido> listar(String idCliente) {
        return repository.findByClient(new ObjectId(idCliente));
    }

    public Optional<Pedido> consultar(String idPedido) {
        return repository.findById(idPedido);
    }

    private void adicionarValorProduto(String idPedido, double valor) {
        Optional<Pedido> optionalPedido = repository.findById(idPedido);
        optionalPedido.ifPresent(pedido -> {
            pedido.setValor(pedido.getValor() + valor);
            alterar(pedido.getIdCliente().toString(), idPedido, pedido);
        });
    }

    private void removerValorProduto(String idPedido, double valor) {
        Optional<Pedido> optionalPedido = repository.findById(idPedido);
        optionalPedido.ifPresent(pedido -> {
            pedido.setValor(pedido.getValor() - valor);
            alterar(pedido.getIdCliente().toString(), idPedido, pedido);
        });
    }

}
