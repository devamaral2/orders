package br.com.fiap.mspedidos.dto;

import br.com.fiap.mspedidos.models.ProdutoVendido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.UUID;

public record CreateListaPedidosDto(
        UUID clienteId,
        List<ProdutoVendido> produtos,
        @NotBlank(message = "Você deve enviar um 'local' que contenha produtos.")
        String local
) {
}
