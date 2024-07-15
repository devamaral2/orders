package com.example.msprodutos.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ProdutoAccepted {
    private UUID pedidoId;
    private Boolean accepted;
}
