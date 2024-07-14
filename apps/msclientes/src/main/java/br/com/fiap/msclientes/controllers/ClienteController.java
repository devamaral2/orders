package br.com.fiap.msclientes.controllers;
import br.com.fiap.msclientes.controllers.exceptions.ValidationTrigger;
import br.com.fiap.msclientes.dto.CreateClienteDto;
import br.com.fiap.msclientes.models.Cliente;
import br.com.fiap.msclientes.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping()
    public ResponseEntity<List<Cliente>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.clienteService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Cliente> create(
            @RequestBody @Valid CreateClienteDto dto,
            BindingResult bindingResult) {
        new ValidationTrigger(bindingResult).verify();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.clienteService.create(dto.nome()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Cliente> update(
            @PathVariable UUID id,
            @RequestBody @Valid CreateClienteDto dto,
            BindingResult bindingResult) {
        new ValidationTrigger(bindingResult).verify();
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.clienteService.update(id, dto.nome()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> update(
            @PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.clienteService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable UUID id) {
        this.clienteService.delete(id);
        ResponseEntity.status(HttpStatus.NO_CONTENT);
    }
}
