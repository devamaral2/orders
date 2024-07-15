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
    @Column(name = "cliente_accepted")
    private Boolean clienteAccepted;
    @Column(name = "produto_accepted")
    private Boolean produtosAccepted;
    @Column(name = "entrega_accepted")
    private Boolean entregaAccepted;

    @OneToMany(mappedBy = "listaPedidos")
    public List<Pedido> pedidos;
}
