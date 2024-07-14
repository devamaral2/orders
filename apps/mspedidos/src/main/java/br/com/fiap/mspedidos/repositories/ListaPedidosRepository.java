package br.com.fiap.mspedidos.repositories;
import br.com.fiap.mspedidos.models.ListaPedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ListaPedidosRepository extends JpaRepository<ListaPedidos, UUID> {
    Optional<ListaPedidos> findById(UUID id);
}
