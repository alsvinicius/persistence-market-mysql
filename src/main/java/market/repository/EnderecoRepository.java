package market.repository;

import market.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, String> {

    @Query("SELECT e FROM Endereco e WHERE e.idCliente = :idCliente")
    List<Endereco> findByClient(@Param("idCliente") String idCliente);

}
