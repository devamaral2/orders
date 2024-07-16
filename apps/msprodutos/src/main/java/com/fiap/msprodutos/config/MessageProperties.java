package com.fiap.msprodutos.config;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MessageProperties {
    private String produtoToEntregaChannel = "produtoToEntregaSupplier-out-0";
}
