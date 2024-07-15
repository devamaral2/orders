package br.com.fiap.mspedidos.events;
import br.com.fiap.mspedidos.models.ClienteAccepted;
import br.com.fiap.mspedidos.repositories.ListaPedidosRepository;
import br.com.fiap.mspedidos.repositories.PedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClienteAcceptedEventListener implements Consumer<ClienteAccepted> {
    private final ListaPedidosRepository listaPedidosRepository;
    @Override
    public void accept(ClienteAccepted clienteAccepted) {
        log.info("O pedido que confirma que existe alguém no banco de dados chegou " + clienteAccepted.getAccepted());
        Optional<Cliente> optionalCliente = clienteRepository.findById(pedidoToCliente.getClienteId());
        if (optionalCliente.isEmpty()) {
            clienteAcceptedEventGatway.sendClienteAcceptedEvent(new ClienteAccepted(false));
            throw new ControllerNotFoundException("Não existe este cliente na nossa base de dados");
        }
        clienteAcceptedEventGatway.sendClienteAcceptedEvent(new ClienteAccepted(true));
    }
}
