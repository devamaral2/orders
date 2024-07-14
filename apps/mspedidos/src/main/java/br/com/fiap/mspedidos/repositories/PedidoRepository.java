package br.com.fiap.mspedidos.repositories;
import br.com.fiap.mspedidos.models.ListaPedidos;
import br.com.fiap.mspedidos.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
    Optional<Pedido> findById(UUID id);
}
