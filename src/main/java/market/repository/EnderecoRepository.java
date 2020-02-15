package market.repository;

import market.model.Endereco;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface EnderecoRepository extends MongoRepository<Endereco, String> {

    @Query("{'idCliente': ?0}")
    List<Endereco> findByClient(ObjectId idCliente);

}
