package market.repository;

import market.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, String> {

    @Query("SELECT p from Pedido p where idCliente = :idCliente")
    List<Pedido> findByClient(@Param("idCliente") String idCliente);

}
