package br.com.fiap.msentregas.events;
import br.com.fiap.msentregas.config.MessageProperties;
import br.com.fiap.msentregas.models.PedidoCreated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EntregaToPedidoWithStreamBridge implements EntregaToPedidoEventGatway {
    private final StreamBridge streamBridge;
    private final MessageProperties messageProperties;
    @Override
    public void sendEntregaToPedidoEvent(PedidoCreated pedidoCreated) {
        log.info("Mensagem enviada para o serviço de pedidos");
        streamBridge.send(messageProperties.getEntregaToPedidoChannel(), pedidoCreated);
    }
}
