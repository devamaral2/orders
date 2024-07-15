package br.com.fiap.msclientes.config;//package br.com.fiap.msclientes.config;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MessageProperties {
    private String clienteAcceptedChannel = "clienteAcceptedSupplier-out-0";
}
