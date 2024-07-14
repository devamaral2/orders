package br.com.fiap.msclientes.services;

import br.com.fiap.msclientes.controllers.exceptions.ControllerNotFoundException;
import br.com.fiap.msclientes.models.Cliente;
import br.com.fiap.msclientes.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(UUID id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isEmpty()) {
            throw new ControllerNotFoundException("Não existe este cliente na nossa base de dados");
        }
        return  optionalCliente.get();
    }

    public Cliente create(String nome) {
        Cliente cliente = Cliente.builder().nome(nome).build();
        return clienteRepository.save(cliente);
    }

    public Cliente update(UUID id, String nome) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isEmpty()) {
            throw new ControllerNotFoundException("Não existe este cliente na nossa base de dados");
        }
        Cliente cliente = Cliente
                .builder()
                .nome(nome)
                .id(optionalCliente.get().getId())
                .build();
        return clienteRepository.save(cliente);
    }

    public void delete(UUID id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isEmpty()) {
            throw new ControllerNotFoundException("Não existe este cliente na nossa base de dados");
        }
        clienteRepository.delete(optionalCliente.get());
    }


}
