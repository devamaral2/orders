package br.com.fiap.msclientes.events;
import br.com.fiap.msclientes.controllers.exceptions.ControllerNotFoundException;
import br.com.fiap.msclientes.models.Cliente;
import br.com.fiap.msclientes.models.PedidoCreated;
import br.com.fiap.msclientes.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class PedidoToClienteEventListener implements Consumer<PedidoCreated> {

    private final ClienteRepository clienteRepository;
    private final ClienteToProdutoEventGatway clienteToProdutoEventGatway;
   @Override
    public void accept(PedidoCreated pedidoCreated) {
       log.info("Novo pedido recebido pelo serviço de cliente " + pedidoCreated.getClienteId());
       Optional<Cliente> optionalCliente = clienteRepository.findById(pedidoCreated.getClienteId());
       if (optionalCliente.isEmpty()) {
           pedidoCreated.setClienteAccepted(false);
           clienteToProdutoEventGatway.sendClienteAcceptedEvent(pedidoCreated);
           throw new ControllerNotFoundException("Não existe este cliente na nossa base de dados");
       }
       pedidoCreated.setClienteAccepted(true);
       clienteToProdutoEventGatway.sendClienteAcceptedEvent(pedidoCreated);
   }
}
