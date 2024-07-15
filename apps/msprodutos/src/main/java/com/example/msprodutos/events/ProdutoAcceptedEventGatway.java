package com.example.msprodutos.events;
import com.example.msprodutos.models.ProdutoAccepted;

public interface ProdutoAcceptedEventGatway {
    void sendProdutoAcceptedEvent(ProdutoAccepted produtoAccepted);
}
