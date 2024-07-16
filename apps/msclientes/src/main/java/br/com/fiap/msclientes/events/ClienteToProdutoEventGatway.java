package br.com.fiap.msclientes.events;
import br.com.fiap.msclientes.models.PedidoCreated;

public interface ClienteToProdutoEventGatway {
    void sendClienteAcceptedEvent(PedidoCreated pedidoCreated);
}
