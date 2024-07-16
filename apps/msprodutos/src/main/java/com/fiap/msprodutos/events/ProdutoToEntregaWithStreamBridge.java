package com.fiap.msprodutos.events;
import com.fiap.msprodutos.config.MessageProperties;
import com.fiap.msprodutos.models.PedidoCreated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProdutoToEntregaWithStreamBridge implements ProdutoToEntregaEventGatway {
    private final StreamBridge streamBridge;
    private final MessageProperties messageProperties;
    @Override
    public void sendProdutoToEntregaEvent(PedidoCreated pedidoCreated) {
        log.info("Mensagem enviada para o serviço de entregas");
        streamBridge.send(messageProperties.getProdutoToEntregaChannel(), pedidoCreated);
    }
}
