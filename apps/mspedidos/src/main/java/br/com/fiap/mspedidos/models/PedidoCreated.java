package br.com.fiap.mspedidos.models;

import br.com.fiap.mspedidos.models.ProdutoVendido;
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
