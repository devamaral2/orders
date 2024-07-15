package com.example.msprodutos.events;

import com.example.msprodutos.config.MessageProperties;
import com.example.msprodutos.models.ProdutoAccepted;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProdutoAcceptedWithStreamBridge implements ProdutoAcceptedEventGatway {
    private final StreamBridge streamBridge;
    private final MessageProperties messageProperties;
    @Override
    public void sendProdutoAcceptedEvent(ProdutoAccepted produtoAccepted) {
        log.info("Mensagem enviada para o serviço de produtos");
        streamBridge.send(messageProperties.getProductAcceptedChannel(), produtoAccepted);
    }
}
