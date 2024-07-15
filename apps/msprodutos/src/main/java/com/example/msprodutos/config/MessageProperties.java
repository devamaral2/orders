package com.example.msprodutos.config;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MessageProperties {
    private String productAcceptedChannel = "produtoAcceptedSupplier-out-0";
}
