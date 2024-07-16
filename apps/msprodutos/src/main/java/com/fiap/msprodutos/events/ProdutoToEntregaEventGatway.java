package com.fiap.msprodutos.events;
import com.fiap.msprodutos.models.PedidoCreated;

public interface ProdutoToEntregaEventGatway {
    void sendProdutoToEntregaEvent(PedidoCreated pedidoCreated);
}
