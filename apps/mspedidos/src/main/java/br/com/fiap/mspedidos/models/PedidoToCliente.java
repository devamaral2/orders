package br.com.fiap.mspedidos.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class PedidoToCliente {
    UUID clienteId;
}
