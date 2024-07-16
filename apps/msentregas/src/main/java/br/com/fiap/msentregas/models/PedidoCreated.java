package br.com.fiap.msentregas.models;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
public class PedidoCreated {
    private UUID pedidoId;
    private UUID clienteId;
    private String local;
    private List<ProdutoVendido> listaPedidos;
    private Boolean clienteAccepted;
    private Boolean produtosAccepted;
    private Boolean entregaAccepted;

    public PedidoCreated(UUID pedidoId, UUID clienteId, String local, List<ProdutoVendido> listaPedidos) {
        this.pedidoId = pedidoId;
        this.clienteId = clienteId;
        this.local = local;
        this.listaPedidos = listaPedidos;
    }
}