package br.com.fiap.mspedidos.services;
import br.com.fiap.mspedidos.controllers.exceptions.ControllerNotFoundException;
import br.com.fiap.mspedidos.events.PedidoEventGatway;
import br.com.fiap.mspedidos.models.*;
import br.com.fiap.mspedidos.repositories.ListaPedidosRepository;
import br.com.fiap.mspedidos.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ListaPedidosService {
    @Autowired
    private ListaPedidosRepository listaPedidosRepository;

    @Autowired
    private PedidoEventGatway pedidoEventGatway;
    @Autowired
    private PedidoRepository pedidoRepository;
    public List<ListaPedidos> findAll() {
        return listaPedidosRepository.findAll();
    }

    public ListaPedidos findById(UUID id) {
        Optional<ListaPedidos> optionalCliente = listaPedidosRepository.findById(id);
        if (optionalCliente.isEmpty()) {
            throw new ControllerNotFoundException("Não existe este lista de pedidos na nossa base de dados");
        }
        return  optionalCliente.get();
    }

    public ListaPedidos create(UUID clienteId, List<ProdutoVendido> produtos) {
        List<Pedido> pedidos = produtos.stream().map(p -> Pedido
                    .builder()
                    .quantidade(p.getQuantidade())
                    .produtoId(p.getProductId())
                    .build()
        ).collect(Collectors.toList());
        List<Pedido> pedidosCriados = pedidoRepository.saveAll(pedidos);
        ListaPedidos listaDePedidos = ListaPedidos
                .builder()
                .clienteId(clienteId)
                .entregaId(UUID.randomUUID())
                .valor(100000L)
                .pedidos(pedidosCriados)
                .build();
        ListaPedidos lista = listaPedidosRepository.save(listaDePedidos);
        pedidoEventGatway.sendProdutoToClienteEvent(new PedidoToCliente(clienteId));
        return lista;

    }

    public void delete(UUID id) {
        Optional<ListaPedidos> optionalCliente = listaPedidosRepository.findById(id);
        if (optionalCliente.isEmpty()) {
            throw new ControllerNotFoundException("Não existe este lista de pedidos na nossa base de dados");
        }
        listaPedidosRepository.delete(optionalCliente.get());
    }


}
