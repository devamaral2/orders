package br.com.fiap.msentregas.services;
import br.com.fiap.msentregas.models.Entrega;
import br.com.fiap.msentregas.models.GoogleMapsResponse;
import br.com.fiap.msentregas.repositories.EntregaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.DataInput;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleFindLocationService {

    private final EntregaService entregaService;
    private final EntregaRepository entregaRepository;
    private final FakeInTransitLocationService inTransitLocation;
    private final FakeSendGoogleInformation sendGoogleInformation;
    @Scheduled(cron = "0 */1 * * * *")
    public void doExecuteEachOneMinuteSchedule() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Entrega> entregas = entregaRepository.findByStatus("em andamento");
        entregas.stream().forEach(e -> {
            String newLocation = inTransitLocation.getLocation(e.getId());
            entregaService.update(e.getId(), null, newLocation);
            ResponseEntity<String> response = entregaService.googleMapsRequisition(newLocation, e.getDestino());
            GoogleMapsResponse googleMapsResponse = null;
            try {
                googleMapsResponse = objectMapper.readValue(response.getBody(), GoogleMapsResponse.class);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            sendGoogleInformation.sendInformation(e.getEntregador().getId(), googleMapsResponse);
        });
        log.info("Localização das entregas em andamento atualizado");
    }
}
