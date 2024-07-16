package br.com.fiap.msentregas.services;
import br.com.fiap.msentregas.controllers.exceptions.ControllerNotFoundException;
import br.com.fiap.msentregas.models.Entrega;
import br.com.fiap.msentregas.models.Entregador;
import br.com.fiap.msentregas.repositories.EntregaRepository;
import br.com.fiap.msentregas.repositories.EntregadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EntregaService {
    @Autowired
    private EntregaRepository entregaRepository;
    @Autowired
    private EntregadorRepository entregadorRepository;
    public List<Entrega> findAll() {
        return entregaRepository.findAll();
    }

    public Entrega findById(UUID id) {
        Optional<Entrega> optionalEntrega = entregaRepository.findById(id);
        if (optionalEntrega.isEmpty()) {
            throw new ControllerNotFoundException("Não existe este entrega na nossa base de dados");
        }
        return  optionalEntrega.get();
    }

    public Entrega create(UUID pedidoId, String local) {
        List<Entregador> listaEntregador = entregadorRepository.findByOcupadoIsFalse();
        if (listaEntregador.isEmpty()) {
            throw new ControllerNotFoundException("Não temos um entregador disponível no momento");
        }
        Entrega entrega = Entrega
                .builder()
                .entregador(listaEntregador.getFirst())
                .pedidoId(pedidoId)
                .status("preparado para entrega")
                .local(local)
                .build();
        Entrega entregaFinal = entregaRepository.save(entrega);
        Entregador entregador = Entregador
                .builder()
                .ocupado(true)
                .nome(listaEntregador.getFirst().getNome())
                .id(listaEntregador.getFirst().getId())
                .build();
        entregadorRepository.save(entregador);
        return entregaFinal;
    }

    public Entrega update(UUID id, String status, String local) {
        Optional<Entrega> optionalEntrega = entregaRepository.findById(id);
        if (optionalEntrega.isEmpty()) {
            throw new ControllerNotFoundException("Não existe este entrega na nossa base de dados");
        }
        String novoLocal = optionalEntrega.get().getLocal();
        String novoStatus = optionalEntrega.get().getStatus();
        if (status != null) {
            novoStatus = status;
        }
        if (local != null) {
            novoLocal = local;
        }
        Entrega entrega = Entrega
                .builder()
                .status(novoStatus)
                .id(optionalEntrega.get().getId())
                .local(novoLocal)
                .pedidoId((optionalEntrega.get().getPedidoId()))
                .entregador(optionalEntrega.get().getEntregador())
                .build();
        return entregaRepository.save(entrega);
    }

    public void delete(UUID id) {
        Optional<Entrega> optionalEntrega = entregaRepository.findById(id);
        if (optionalEntrega.isEmpty()) {
            throw new ControllerNotFoundException("Não existe este entrega na nossa base de dados");
        }
        entregaRepository.delete(optionalEntrega.get());
    }


}
