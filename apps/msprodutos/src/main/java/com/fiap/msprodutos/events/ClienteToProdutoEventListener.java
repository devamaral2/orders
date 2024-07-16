package com.fiap.msprodutos.events;
import com.fiap.msprodutos.models.PedidoCreated;
import com.fiap.msprodutos.services.ProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClienteToProdutoEventListener implements Consumer<PedidoCreated> {
    private final ProdutoService produtoService;
    private final ProdutoToEntregaEventGatway produtoAcceptedEventGatway;
    @Override
    public void accept(PedidoCreated pedidoCreated) {
        try {
          produtoService.removeProduct(pedidoCreated.getListaPedidos());
          pedidoCreated.setProdutosAccepted(true);
          produtoAcceptedEventGatway.sendProdutoToEntregaEvent(pedidoCreated);
        } catch(RuntimeException  e) {
            log.error(e.getMessage());
            pedidoCreated.setProdutosAccepted(false);
            produtoAcceptedEventGatway.sendProdutoToEntregaEvent(pedidoCreated);
        }
    }
}
