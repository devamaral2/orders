package br.com.fiap.msclientes.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateClienteDto(
        @NotBlank(message = "O campo nome deve ser preenchido.")
        String nome
) {
}
