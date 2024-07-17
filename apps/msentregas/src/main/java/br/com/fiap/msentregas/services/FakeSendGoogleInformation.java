package br.com.fiap.msentregas.services;


import br.com.fiap.msentregas.models.GoogleMapsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Este serviço é um fake construido para substituir um serviço que transfere informações sobre a rota que um entregador
 * esta realizando
 *
 **/
@Service
@Slf4j
public class FakeSendGoogleInformation {
    public void sendInformation(UUID entregadorId, ResponseEntity response) {
        log.info("A informação sobre a localização da entrega foi passada para o entregador" + entregadorId);
        log.info("Acessando as informações da requisição" + response.getBody());
    }
}
