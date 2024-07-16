package com.fiap.msprodutos.models;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoVendido {
    private UUID produtoId;
    private Long quantidade;
}
