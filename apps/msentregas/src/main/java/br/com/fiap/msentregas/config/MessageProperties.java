package br.com.fiap.msentregas.config;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MessageProperties {
    private String entregaToPedidoChannel = "entregaToPedidoSupplier-out-0";
}
