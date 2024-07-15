package com.example.msprodutos.events;
import com.example.msprodutos.models.PedidoCreated;
import com.example.msprodutos.models.ProdutoAccepted;
import com.example.msprodutos.services.ProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class PedidoToProdutoEventListener implements Consumer<PedidoCreated> {
    private final ProdutoService produtoService;
    private final ProdutoAcceptedEventGatway produtoAcceptedEventGatway;
    @Override
    public void accept(PedidoCreated pedidoCreated) {
        log.info("Novo pedido recebido pelo serviço de cliente ", pedidoCreated.getPedidoId());
        try {
          log.info("AQUI" + pedidoCreated.getListaPedidos().getFirst().getProdutoId());
          produtoService.removeProduct(pedidoCreated.getListaPedidos());
          produtoAcceptedEventGatway.sendProdutoAcceptedEvent(new ProdutoAccepted(pedidoCreated.getPedidoId(), true));
        } catch(RuntimeException  e) {
            log.error(e.getMessage());
            produtoAcceptedEventGatway.sendProdutoAcceptedEvent(new ProdutoAccepted(pedidoCreated.getPedidoId(), false));
        }
    }
}
