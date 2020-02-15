package market.service;

import market.model.PedidoProduto;
import market.model.Produto;
import market.repository.PedidoProdutoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class PedidoProdutoService {

    @Autowired
    PedidoProdutoRepository repository;

    @Autowired
    MongoTemplate mongoTemplate;

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

        pedidoProduto.setIdPedido(new ObjectId(idPedido));
        pedidoProduto.setIdPedidoProduto(new ObjectId().toString());
        repository.insert(pedidoProduto);
        pedidoService.adicionarValorProduto(idPedido, produto.getValor());
        return pedidoProduto;
    }

    public void excluir(String idPedido, String idProduto) {
        List<PedidoProduto> produtosPedido = repository.findByPedidoProduto(new ObjectId(idPedido), new ObjectId(idProduto));
        if(produtosPedido != null) {
            PedidoProduto pedidoProduto = produtosPedido.get(0);
            Produto produto = produtoService.obter(idProduto);
            repository.delete(pedidoProduto);
            pedidoService.removerValorProduto(idPedido, produto.getValor());
            produto.setQuantidade(produto.getQuantidade() + 1);
            produtoService.alterar(idProduto, produto);
        }
    }

    public List<PedidoProduto> listar(String idPedido) {
        List<PedidoProduto> listaPedidoProduto = repository.findByPedido(new ObjectId(idPedido));

        LookupOperation lookupOperation = LookupOperation
                .newLookup()
                .from("produtos")
                .localField("idProduto")
                .foreignField("_id")
                .as("produtos");

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("idPedido").is(new ObjectId(idPedido))),
                lookupOperation
        );

        AggregationResults<Produto> produtos = mongoTemplate.aggregate(
                aggregation,
                "produtos",
                Produto.class
        );

        List<Produto> listaProdutos = produtos.getMappedResults();

        return listaPedidoProduto;
    }

}
