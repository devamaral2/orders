package br.com.fiap.msclientes.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class PedidoToCliente {
    UUID clienteId;
}
