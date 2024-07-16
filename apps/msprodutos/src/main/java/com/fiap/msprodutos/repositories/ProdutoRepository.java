package com.fiap.msprodutos.repositories;
import com.fiap.msprodutos.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
    Optional<Produto> findById(UUID id);
}
