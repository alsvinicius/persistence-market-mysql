package market.controller;

import market.model.PedidoProduto;
import market.service.PedidoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Controller
@RequestMapping("/clientes/{id_cliente}/pedidos/{id_pedido}/produtos")
public class PedidoProdutoController {

    @Autowired
    PedidoProdutoService service;

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoProduto inserir(
            @PathVariable("id_pedido") String idPedido,
            @RequestBody PedidoProduto pedidoProduto
    ) throws HttpClientErrorException {
        return service.inserir(idPedido, pedidoProduto);
    }

    @DeleteMapping("/{id_produto}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(
            @PathVariable("id_pedido") String idPedido,
            @PathVariable("id_produto") String id
    ) {
        service.excluir(idPedido, id);
    }

    @GetMapping
    @ResponseBody
    public List<PedidoProduto> listar(
            @PathVariable("id_pedido") String idPedido
    ) {
        return service.listar(idPedido);
    }
    
}
