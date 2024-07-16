package br.com.fiap.msentregas.events;
import br.com.fiap.msentregas.models.PedidoCreated;
import br.com.fiap.msentregas.services.EntregaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProdutoToEntregaEventListener implements Consumer<PedidoCreated> {
    private final EntregaService entregaService;
    private final EntregaToPedidoEventGatway produtoAcceptedEventGatway;
    @Override
    public void accept(PedidoCreated pedidoCreated) {
        log.info("Mensagem recebida pelo serviço de produto " + pedidoCreated.getPedidoId());
        log.info("Mensagem recebida pelo serviço de produto " + pedidoCreated.getLocal());
        try {
          entregaService.create(pedidoCreated.getPedidoId(), pedidoCreated.getLocal());
          pedidoCreated.setEntregaAccepted(true);
          produtoAcceptedEventGatway.sendEntregaToPedidoEvent(pedidoCreated);
        } catch(RuntimeException  e) {
            log.error(e.getMessage());
            pedidoCreated.setEntregaAccepted(false);
            produtoAcceptedEventGatway.sendEntregaToPedidoEvent(pedidoCreated);
        }
    }
}
