package market.controller;

import market.model.Endereco;
import market.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clientes/{id_cliente}/enderecos")
public class EnderecoController {

    @Autowired
    EnderecoService service;

    @PostMapping("/")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Endereco inserir(
            @PathVariable("id_cliente") String idCliente,
            @RequestBody Endereco endereco
    ) {
        return service.inserir(endereco, idCliente);
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public Optional<Endereco> alterar(
            @PathVariable("id_cliente") String idCliente,
            @PathVariable String id,
            @RequestBody Endereco endereco
    ) {
        return service.alterar(id, idCliente, endereco);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(
            @PathVariable("id_cliente") String idCliente,
            @PathVariable String id
    ) {
        service.excluir(id, idCliente);
    }

    @GetMapping
    @ResponseBody
    public List<Endereco> listar(
            @PathVariable("id_cliente") String idCliente
    ) {
        return service.listar(idCliente);
    }
    
}
