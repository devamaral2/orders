package br.com.fiap.msentregas.repositories;
import br.com.fiap.msentregas.models.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, UUID> {
    Optional<Entrega> findById(UUID id);
    List<Entrega> findByStatus(String status);
}
