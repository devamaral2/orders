package br.com.fiap.mspedidos.config;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MessageProperties {
    private String produtoToClienteChannel = "create-produto-to-cliente-out-0";
    private String pedidoToprodutoChannel = "create-pedido-to-produto-out-1";
}
