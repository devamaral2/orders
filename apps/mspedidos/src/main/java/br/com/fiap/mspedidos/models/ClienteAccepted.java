package br.com.fiap.mspedidos.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ClienteAccepted {
    private UUID pedidoId;
    private Boolean accepted;
}
