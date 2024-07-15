package br.com.fiap.msclientes.events;
import br.com.fiap.msclientes.models.ClienteAccepted;

public interface ClienteAcceptedEventGatway {
    void sendClienteAcceptedEvent(ClienteAccepted clienteAccepted);
}
