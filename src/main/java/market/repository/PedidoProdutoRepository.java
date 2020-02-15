package market.repository;

import market.model.PedidoProduto;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PedidoProdutoRepository extends MongoRepository<PedidoProduto, String> {

    @Query("{'idPedido': ?0}")
    List<PedidoProduto> findByPedido(ObjectId idPedido);

    @Query("{'idPedido': ?0, 'idProduto': ?1}")
    List<PedidoProduto> findByPedidoProduto(ObjectId idPedido, ObjectId idProduto);

}
