package market.repository;

import market.model.PedidoProduto;
import market.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoProdutoRepository extends JpaRepository<PedidoProduto, String> {

    @Query("SELECT p from PedidoProduto pp, Produto p WHERE pp.idPedido = :idPedido AND pp.idProduto = p.idProduto")
    List<Produto> findByPedido(@Param("idPedido") String idPedido);

    @Query("SELECT pp from PedidoProduto pp WHERE idPedido = :idPedido AND idProduto = :idProduto")
    List<PedidoProduto> findByPedidoProduto(@Param("idPedido") String idPedido, @Param("idProduto") String idProduto);

}
