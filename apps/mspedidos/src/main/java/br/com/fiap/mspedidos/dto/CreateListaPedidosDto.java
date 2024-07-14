package br.com.fiap.mspedidos.dto;

import br.com.fiap.mspedidos.models.ProdutoVendido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.UUID;

public record CreateListaPedidosDto(
        UUID clienteId,
        @NotEmpty(message = "Você deve enviar uma lista que contenha produtos.")
        List<ProdutoVendido> produtos
) {
}
