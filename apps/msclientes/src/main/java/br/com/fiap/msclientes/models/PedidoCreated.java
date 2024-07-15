package br.com.fiap.msclientes.models;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class PedidoCreated {
    UUID pedidoId;
    UUID clienteId;
    List<ProdutoVendido> listaPedidos;
}
