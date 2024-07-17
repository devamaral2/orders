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
    public void sendInformation(UUID entregadorId, GoogleMapsResponse response) {
        log.info("A informação sobre a localização da entrega foi passada para o entregador" + entregadorId);
        String distancia = response.getRoutes().getFirst().getLegs().getFirst().getDistance().getText();
        String duracao = response.getRoutes().getFirst().getLegs().getFirst().getDuration().getText();
        List<GoogleMapsResponse.Step> steps = response.getRoutes().getFirst().getLegs().getFirst().getSteps();
        log.info("Distancia até o destino: " + distancia);
        log.info("Duração até o destino " + duracao );
        log.info("Distancia até o primeiro step: " + steps.getFirst().getDistance().getText());
    }
}
