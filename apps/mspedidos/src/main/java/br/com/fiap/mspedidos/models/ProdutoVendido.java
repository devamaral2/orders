package br.com.fiap.mspedidos.models;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoVendido {
    private UUID productId;
    private Long quantidade;
}
