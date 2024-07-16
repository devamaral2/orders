package br.com.fiap.msentregas.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record CreateEntregaDto(
        @NotBlank(message = "O campo 'pedidoId' deve ser preenchido.")
        String pedidoId,
        @NotBlank(message = "O campo 'local' deve ser preenchido.")
        String local
) {
}
