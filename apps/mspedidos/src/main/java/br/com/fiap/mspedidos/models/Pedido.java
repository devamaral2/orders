package br.com.fiap.mspedidos.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID productId;
    private Long quantidade;
    private Long valor;

    @ManyToOne
    @JoinColumn(name = "lista_pedidos_id")
    private ListaPedidos listaPedidos;
}
