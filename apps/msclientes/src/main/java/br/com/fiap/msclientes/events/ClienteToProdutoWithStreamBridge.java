package br.com.fiap.msclientes.events;

import br.com.fiap.msclientes.config.MessageProperties;
import br.com.fiap.msclientes.models.PedidoCreated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClienteToProdutoWithStreamBridge implements ClienteToProdutoEventGatway {
    private final StreamBridge streamBridge;
    private final MessageProperties messageProperties;
    @Override
    public void sendClienteAcceptedEvent(PedidoCreated pedidoCreated) {
        log.info("Mensagem enviada para o serviço de produtos");
        streamBridge.send(messageProperties.getClienteAcceptedChannel(), pedidoCreated);
    }
}
