package market.repository;

import market.model.Pedido;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PedidoRepository extends MongoRepository<Pedido, String> {

    @Query("{'idCliente': ?0}")
    List<Pedido> findByClient(ObjectId idCliente);

}
