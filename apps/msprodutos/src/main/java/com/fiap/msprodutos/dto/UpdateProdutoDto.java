package com.fiap.msprodutos.dto;

public record UpdateProdutoDto(
        String nome,
        String descricao,
        Long quantidade
) {
}
