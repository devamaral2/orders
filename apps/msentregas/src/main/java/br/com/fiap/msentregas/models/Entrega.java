package br.com.fiap.msentregas.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "entregas")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Entrega {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID pedidoId;
    private String destino;
    private String origem;
    private String status;

    @OneToOne
    @JoinColumn(name = "entregador_id")
    private Entregador entregador;
}
