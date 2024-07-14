package com.example.msprodutos.services;
import com.example.msprodutos.controllers.exceptions.BadRequestException;
import com.example.msprodutos.controllers.exceptions.ControllerNotFoundException;
import com.example.msprodutos.models.Produto;
import com.example.msprodutos.models.ProdutoVendido;
import com.example.msprodutos.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto findById(UUID id) {
        Optional<Produto> optionalProduct = produtoRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ControllerNotFoundException("Não existe este produto na nossa base de dados");
        }
        return  optionalProduct.get();
    }

    public Produto create(String nome, String descricao, Long quantidade) {
        Produto produto = Produto
                .builder()
                .nome(nome)
                .descricao(descricao)
                .quantidade(quantidade)
                .build();
        return produtoRepository.save(produto);
    }

    public Produto update(UUID id, String nome, String descricao, Long quantidade) {
        Optional<Produto> optionalProduct = produtoRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ControllerNotFoundException("Não existe este produto na nossa base de dados");
        }
        String novoNome = optionalProduct.get().getNome();
        String novaDescricao = optionalProduct.get().getDescricao();
        Long novaQualidade = optionalProduct.get().getQuantidade();
        if (nome != null) {
            novoNome = nome;
        }
        if (descricao != null) {
            novaDescricao = descricao;
        }
        if (quantidade != null) {
            novaQualidade = quantidade;
        }
        Produto produto = Produto
                .builder()
                .nome(novoNome)
                .descricao(novaDescricao)
                .quantidade(novaQualidade)
                .id(optionalProduct.get().getId())
                .build();
        return produtoRepository.save(produto);
    }

    public void delete(UUID id) {
        Optional<Produto> optionalProduct = produtoRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ControllerNotFoundException("Não existe este produto na nossa base de dados");
        }
        produtoRepository.delete(optionalProduct.get());
    }

    public void removeProduct(List<ProdutoVendido> produtoVendido) {
        produtoVendido.stream().forEach(p -> {
        Optional<Produto> optionalProduct = produtoRepository.findById(p.getId());
        if (optionalProduct.isEmpty()) {
            throw new ControllerNotFoundException("Não existe este produto na nossa base de dados: " + p.getId());
        }
        if (optionalProduct.get().getQuantidade() < p.getQuantidade()) {
            throw new BadRequestException("O produto " + optionalProduct.get().getNome() + " não tem quantidade o suficiente no estoque");
        }
        Produto produto = Produto
                .builder()
                .id(optionalProduct.get().getId())
                .nome(optionalProduct.get().getNome())
                .descricao(optionalProduct.get().getDescricao())
                .quantidade(optionalProduct.get().getQuantidade() - p.getQuantidade())
                .build();
            produtoRepository.save(produto);
        });
    }


}
