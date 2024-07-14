package br.com.fiap.msentregas.models;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "entregadores")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Entregador {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nome;
    private Boolean ocupado;
}
