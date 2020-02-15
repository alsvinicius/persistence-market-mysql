package market.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pedidos_produtos")
public class PedidoProduto {

    @Id
    private String idPedidoProduto;

    private ObjectId idPedido;

    private ObjectId idProduto;

    public String getIdPedidoProduto() {
        return idPedidoProduto;
    }

    public void setIdPedidoProduto(String idPedidoProduto) {
        this.idPedidoProduto = idPedidoProduto;
    }

    public ObjectId getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(ObjectId idPedido) {
        this.idPedido = idPedido;
    }

    public ObjectId getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(ObjectId idProduto) {
        this.idProduto = idProduto;
    }
}
