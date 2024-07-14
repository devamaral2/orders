package com.example.msprodutos.dto;

public record UpdateProdutoDto(
        String nome,
        String descricao,
        Long quantidade
) {
}
