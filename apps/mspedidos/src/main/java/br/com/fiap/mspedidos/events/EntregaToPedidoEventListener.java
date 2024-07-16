package br.com.fiap.mspedidos.events;
import br.com.fiap.mspedidos.controllers.exceptions.ControllerNotFoundException;
import br.com.fiap.mspedidos.models.ClienteAccepted;
import br.com.fiap.mspedidos.models.ListaPedidos;
import br.com.fiap.mspedidos.models.PedidoCreated;
import br.com.fiap.mspedidos.repositories.ListaPedidosRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class EntregaToPedidoEventListener implements Consumer<PedidoCreated> {
    private final ListaPedidosRepository listaPedidosRepository;
    @Override
    public void accept(PedidoCreated pedidoCreated) {
        log.info("A menssagem do serviço de entrega chegou " + pedidoCreated.getClienteAccepted());
        log.info("A menssagem do serviço de entrega chegou " + pedidoCreated.getProdutosAccepted());
        log.info("A menssagem do serviço de entrega chegou " + pedidoCreated.getEntregaAccepted());
        Optional<ListaPedidos> optionalPedido = listaPedidosRepository.findById(pedidoCreated.getPedidoId());
        if (optionalPedido.isEmpty()) {
            throw new ControllerNotFoundException("Este pedido não existe na base de dados");
        }
        ListaPedidos list = optionalPedido.get();
        if (!pedidoCreated.getClienteAccepted()) {
        list.setStatus("cliente_invalido");
        } else if (!pedidoCreated.getProdutosAccepted()) {
            list.setStatus("pedido_invalido");
        } else if (!pedidoCreated.getEntregaAccepted()) {
            list.setStatus("pendencia_com_a_entrega");
        } else {
            list.setStatus("pedido_aceito");
        }
        listaPedidosRepository.save(list);
    }
}
