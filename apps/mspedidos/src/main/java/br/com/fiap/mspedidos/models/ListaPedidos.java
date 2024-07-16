package br.com.fiap.mspedidos.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "lista_pedidos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListaPedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "cliente_id")
    private UUID clienteId;
    @Column(name = "entrega_id")
    private UUID entregaId;
    private Long valor;
    private String status;

    @OneToMany(mappedBy = "listaPedidos")
    public List<Pedido> pedidos;
}
