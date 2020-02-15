package market.service;

import market.model.Endereco;
import market.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EnderecoService {
    
    @Autowired
    EnderecoRepository repository;

    public Endereco inserir(Endereco endereco, String idCliente) {
        endereco.setIdCliente(idCliente);
        endereco.setIdEndereco(UUID.randomUUID().toString());
        repository.save(endereco);
        return endereco;
    }

    public Optional<Endereco> alterar(String id, String idCliente, Endereco endereco) {
        endereco.setIdCliente(idCliente);
        endereco.setIdEndereco(id);
        repository.save(endereco);
        return repository.findById(id);
    }

    public void excluir(String id, String idCliente) {
        Endereco endereco = new Endereco();
        endereco.setIdCliente(idCliente);
        endereco.setIdEndereco(id);
        repository.delete(endereco);
    }

    public List<Endereco> listar(String idCliente) {
        return repository.findByClient(idCliente);
    }
    
}
