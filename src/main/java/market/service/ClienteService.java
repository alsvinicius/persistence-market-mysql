package market.service;

import market.model.Cliente;
import market.repository.ClienteRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository repository;

    public Cliente inserir(Cliente cliente) {
        cliente.setIdCliente(new ObjectId().toString());
        repository.insert(cliente);
        return cliente;
    }

    public Optional<Cliente> alterar(String id, Cliente cliente) {
        cliente.setIdCliente(id);
        repository.save(cliente);
        return repository.findById(id);
    }

    public void excluir(String id) {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(id);
        repository.delete(cliente);
    }

    public List<Cliente> listar() {
        return repository.findAll();
    }

}
