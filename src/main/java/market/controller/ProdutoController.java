package market.controller;

import market.model.Produto;
import market.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService service;

    @PostMapping("/")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Produto inserir(
            @RequestBody Produto produto
    ) {
        return service.inserir(produto);
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public Optional<Produto> alterar(
            @PathVariable String id,
            @RequestBody Produto produto
    ) {
        return service.alterar(id, produto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(
            @PathVariable String id
    ) {
        service.excluir(id);
    }

    @GetMapping
    @ResponseBody
    public List<Produto> listar() {
        return service.listar();
    }

}
