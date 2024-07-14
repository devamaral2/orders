package br.com.fiap.mspedidos.controllers;
import br.com.fiap.mspedidos.controllers.exceptions.ValidationTrigger;
import br.com.fiap.mspedidos.dto.CreateListaPedidosDto;
import br.com.fiap.mspedidos.models.ListaPedidos;
import br.com.fiap.mspedidos.services.ListaPedidosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("pedido")
public class ListaPedidosController {

    @Autowired
    private ListaPedidosService listaPedidos;

    @GetMapping()
    public ResponseEntity<List<ListaPedidos>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.listaPedidos.findAll());
    }

    @PostMapping()
    public ResponseEntity<ListaPedidos> create(
            @RequestBody @Valid CreateListaPedidosDto dto,
            BindingResult bindingResult) {
        new ValidationTrigger(bindingResult).verify();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.listaPedidos.create(dto.clienteId(), dto.produtos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaPedidos> update(
            @PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.listaPedidos.findById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable UUID id) {
        this.listaPedidos.delete(id);
        ResponseEntity.status(HttpStatus.NO_CONTENT);
    }
}
