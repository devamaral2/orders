package com.example.msprodutos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProdutoDto(
        @NotBlank(message = "O campo 'nome' deve ser preenchido.")
        String nome,
        @NotBlank(message = "O campo 'descricao' deve ser preenchido.")
        String descricao,
        @NotNull(message = "O campo 'quantidade' deve ser preenchido.")
        Long quantidade
) {
}
