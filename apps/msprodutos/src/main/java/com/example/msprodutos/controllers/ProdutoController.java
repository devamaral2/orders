package com.example.msprodutos.controllers;
import com.example.msprodutos.controllers.exceptions.ValidationTrigger;
import com.example.msprodutos.dto.CreateProdutoDto;
import com.example.msprodutos.dto.UpdateProdutoDto;
import com.example.msprodutos.models.Produto;
import com.example.msprodutos.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping()
    public ResponseEntity<List<Produto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.produtoService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Produto> create(
            @RequestBody @Valid CreateProdutoDto dto,
            BindingResult bindingResult) {
        new ValidationTrigger(bindingResult).verify();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.produtoService.create(dto.nome(), dto.descricao(), dto.quantidade()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Produto> update(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateProdutoDto dto,
            BindingResult bindingResult) {
        new ValidationTrigger(bindingResult).verify();
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.produtoService.update(id, dto.nome(), dto.descricao(), dto.quantidade()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> update(
            @PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.produtoService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable UUID id) {
        this.produtoService.delete(id);
        ResponseEntity.status(HttpStatus.NO_CONTENT);
    }
}
