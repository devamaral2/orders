package br.com.fiap.msentregas.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Este serviço é um fake construido para substituir um serviço que consegue obter a possição
 * de algum gps a partir de um endpoint.
 **/
@Service
@Slf4j
public class FakeInTransitLocationService {
    public String getLocation(UUID id) {
        log.info("Nova localização da entrega: " + id);
        return  "localização atualizada";
    }

}
