package br.com.fiap.mspedidos.events;

import br.com.fiap.mspedidos.models.PedidoCreated;
import br.com.fiap.mspedidos.models.PedidoToCliente;

public interface PedidoEventGatway {
    void sendProdutoToClienteEvent(PedidoCreated pedidoCreated);
    void sendPedidoToProduto(PedidoCreated pedidoCreated);
}
