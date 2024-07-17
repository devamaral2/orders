package br.com.fiap.msentregas.services;
import br.com.fiap.msentregas.controllers.exceptions.ControllerNotFoundException;
import br.com.fiap.msentregas.models.Entrega;
import br.com.fiap.msentregas.models.Entregador;
import br.com.fiap.msentregas.repositories.EntregaRepository;
import br.com.fiap.msentregas.repositories.EntregadorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class EntregaService {
    @Autowired
    private EntregaRepository entregaRepository;
    @Autowired
    private EntregadorRepository entregadorRepository;
    @Autowired
    private RestTemplate restTemplate;
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

    public ResponseEntity<String> googleMapsRequisition(String origem, String destino) {
        String token = System.getenv("MY_TOKEN");
        String url = "https://maps.googleapis.com/maps/api/directions/json";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromHttpUrl(url)
                .queryParam("origin", origem)
                .queryParam("destination", destino)
                .queryParam("key", token)
                .queryParam("language", "pt_BR");
        ResponseEntity<String> response = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                null,
                String.class
        );
        log.info(response.getStatusCode().toString());
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            System.out.println("Resposta: " + responseBody);
        } else {
            System.out.println("Erro: " + response.getStatusCode());
        }
        return response;
    }

    public Entrega create(UUID pedidoId, String destino) {
        List<Entregador> listaEntregador = entregadorRepository.findByOcupadoIsFalse();
        if (listaEntregador.isEmpty()) {
            throw new ControllerNotFoundException("Não temos um entregador disponível no momento");
        }
        String origem = System.getenv("STOCK_HOUSE_ADDRESS");
        Entrega entrega = Entrega
                .builder()
                .entregador(listaEntregador.getFirst())
                .pedidoId(pedidoId)
                .status("preparado para entrega")
                .destino(destino)
                .origem(origem)
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
        String novoLocal = optionalEntrega.get().getDestino();
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
                .destino(novoLocal)
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
