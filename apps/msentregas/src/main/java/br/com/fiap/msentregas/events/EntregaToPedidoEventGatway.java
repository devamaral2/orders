package br.com.fiap.msentregas.events;

import br.com.fiap.msentregas.models.PedidoCreated;

public interface EntregaToPedidoEventGatway {
    void sendEntregaToPedidoEvent(PedidoCreated pedidoCreated);
}
