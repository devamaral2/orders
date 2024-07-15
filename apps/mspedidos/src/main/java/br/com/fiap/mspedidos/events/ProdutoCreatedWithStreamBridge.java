package br.com.fiap.mspedidos.events;
import br.com.fiap.mspedidos.config.MessageProperties;
import br.com.fiap.mspedidos.models.PedidoCreated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProdutoCreatedWithStreamBridge implements PedidoEventGatway {

    private final StreamBridge streamBridge;
    private final MessageProperties messageProperties;

    @Override
    public void sendProdutoToClienteEvent(PedidoCreated pedidoCreated) {
        log.info("Mensagem enviada para o cliente " + pedidoCreated.getClienteId());
         streamBridge.send(messageProperties.getProdutoToClienteChannel(), pedidoCreated);
    }

    @Override
    public void sendPedidoToProduto(PedidoCreated pedidoCreated) {
        log.info("Mensagem enviada para o microsserviço de produto " + pedidoCreated.getPedidoId());
        streamBridge.send(messageProperties.getPedidoToprodutoChannel(), pedidoCreated);
    }
}
