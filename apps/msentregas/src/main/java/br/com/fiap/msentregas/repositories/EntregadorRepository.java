package br.com.fiap.msentregas.repositories;
import br.com.fiap.msentregas.models.Entregador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EntregadorRepository extends JpaRepository<Entregador, UUID> {
    Optional<Entregador> findById(UUID id);
    List<Entregador> findByOcupadoIsFalse();
}
