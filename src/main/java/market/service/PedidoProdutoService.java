package market.service;

import market.model.PedidoProduto;
import market.model.Produto;
import market.repository.PedidoProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.UUID;

@Service
public class PedidoProdutoService {

    @Autowired
    PedidoProdutoRepository repository;

    @Autowired
    ProdutoService produtoService;

    @Autowired
    PedidoService pedidoService;

    public PedidoProduto inserir(String idPedido, PedidoProduto pedidoProduto) throws HttpClientErrorException{
        Produto produto = produtoService.obter(pedidoProduto.getIdProduto().toString());
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

        pedidoProduto.setIdPedido(idPedido);
        pedidoProduto.setIdPedidoProduto(UUID.randomUUID().toString());
        repository.save(pedidoProduto);
        pedidoService.adicionarValorProduto(idPedido, produto.getValor());
        return pedidoProduto;
    }

    public void excluir(String idPedido, String idProduto) throws HttpClientErrorException {
        List<PedidoProduto> produtosPedido = repository.findByPedidoProduto(idPedido, idProduto);
        if(produtosPedido != null & produtosPedido.size() > 0) {
            PedidoProduto pedidoProduto = produtosPedido.get(0);
            Produto produto = produtoService.obter(idProduto);
            repository.delete(pedidoProduto);
            pedidoService.removerValorProduto(idPedido, produto.getValor());
            produto.setQuantidade(produto.getQuantidade() + 1);
            produtoService.alterar(idProduto, produto);
        } else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Produto não existente no pedido");
        }
    }

    public List<Produto> listar(String idPedido) {
        List<Produto> produtos = repository.findByPedido(idPedido);

        return produtos;
    }

}
