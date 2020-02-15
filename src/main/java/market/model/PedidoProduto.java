package market.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PedidoProduto {

    @Id
    private String idPedidoProduto;

    private String idPedido;

    private String idProduto;

    public String getIdPedidoProduto() {
        return idPedidoProduto;
    }

    public void setIdPedidoProduto(String idPedidoProduto) {
        this.idPedidoProduto = idPedidoProduto;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public String getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }
}
