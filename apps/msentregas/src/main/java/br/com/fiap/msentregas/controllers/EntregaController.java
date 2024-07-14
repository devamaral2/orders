package br.com.fiap.msentregas.controllers;
import br.com.fiap.msentregas.controllers.exceptions.ValidationTrigger;
import br.com.fiap.msentregas.dto.CreateEntregaDto;
import br.com.fiap.msentregas.dto.UpdateEntregaDto;
import br.com.fiap.msentregas.models.Entrega;
import br.com.fiap.msentregas.services.EntregaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("entrega")
public class EntregaController {

    @Autowired
    private EntregaService entregaService;

    @GetMapping()
    public ResponseEntity<List<Entrega>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.entregaService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Entrega> create(
            @RequestBody @Valid CreateEntregaDto dto,
            BindingResult bindingResult) {
        new ValidationTrigger(bindingResult).verify();
        UUID uuid = UUID.fromString(dto.pedidoId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.entregaService.create(uuid));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Entrega> update(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateEntregaDto dto,
            BindingResult bindingResult) {
        new ValidationTrigger(bindingResult).verify();
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.entregaService.update(id, dto.status(), dto.local()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrega> update(
            @PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.entregaService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable UUID id) {
        this.entregaService.delete(id);
        ResponseEntity.status(HttpStatus.NO_CONTENT);
    }
}
