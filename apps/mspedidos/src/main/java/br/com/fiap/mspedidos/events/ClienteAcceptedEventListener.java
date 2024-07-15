package br.com.fiap.mspedidos.events;
import br.com.fiap.mspedidos.controllers.exceptions.ControllerNotFoundException;
import br.com.fiap.mspedidos.models.ClienteAccepted;
import br.com.fiap.mspedidos.models.ListaPedidos;
import br.com.fiap.mspedidos.repositories.ListaPedidosRepository;
import br.com.fiap.mspedidos.repositories.PedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClienteAcceptedEventListener implements Consumer<ClienteAccepted> {
    private final ListaPedidosRepository listaPedidosRepository;
    @Override
    public void accept(ClienteAccepted clienteAccepted) {
        log.info("O pedido que confirma que existe alguém no banco de dados chegou " + clienteAccepted.getPedidoId());
        Optional<ListaPedidos> optionalPedido = listaPedidosRepository.findById(clienteAccepted.getPedidoId());
        if (optionalPedido.isEmpty()) {
            throw new ControllerNotFoundException("Este pedido não existe na base de dados");
        }
        ListaPedidos list = optionalPedido.get();
        list.setClienteAccepted(clienteAccepted.getAccepted());
        listaPedidosRepository.save(list);
    }
}
