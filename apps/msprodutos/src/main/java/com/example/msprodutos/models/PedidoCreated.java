package com.example.msprodutos.models;

import lombok.*;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class PedidoCreated {
    UUID pedidoId;
    UUID clienteId;
    String local;
    List<ProdutoVendido> listaPedidos;
}
