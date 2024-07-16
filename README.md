# Sistema de Gerenciamento de Pedidos e Entregas

## Descrição do Projeto

Segue o projeto desenvolvido para a pós-graduação FIAP em Arquitetura e desenvolvimento Java .Desafio proposto: criar um sistema de gerencimaneto de pedidos Integrado comSpring e Microserviços utilizando conceitos abordados durante as aulas .

A arquitetura proposta para o sistema de gerenciamento de pedidos é modular e eficiente, com cada micro serviço desempenhando um papel específico. A comunicação entre eles é feita de forma assíncrona, utilizando mensagens baseadas em eventos, o que garante coordenação eficiente e escalabilidade robusta, essencial para sistemas de alta demanda.

O serviço de gerenciamento de clientes cuida de todas as operações relacionadas aos clientes, como criação, leitura, atualização e exclusão de registros. A escolha do Spring Boot facilita a criação e configuração desse serviço, enquanto o Spring Data JPA simplifica a integração com o banco de dados relacional, tornando as operações CRUD mais fáceis.

Por outro lado, o micro serviço de catálogo de produtos gerencia as informações detalhadas dos produtos e o controle de estoque. Ele permite a importação em massa de dados de produtos, como descrições, preços e quantidades em estoque. Para isso, utiliza o Spring Batch, que automatiza a importação de dados de fontes externas, garantindo que o catálogo esteja sempre atualizado e que o processo seja eficiente e seguro.

A funcionalidade de carga de produtos merece destaque, pois permite importar grandes volumes de dados de forma eficiente e segura. O Spring Batch é essencial nesse processo, automatizando tarefas repetitivas e garantindo a integridade dos dados importados.

O serviço de gestão de pedidos centraliza o processamento de todos os pedidos, desde a criação até a conclusão. Ele recebe pedidos dos clientes, processa pagamentos e coordena com o micro serviço de logística de entrega. A comunicação baseada em eventos garante que cada etapa do processo de pedidos seja desencadeada automaticamente, reduzindo o acoplamento entre os serviços e permitindo que operem de maneira mais independente.

Por fim, o micro serviço de logística de entrega gerencia toda a logística relacionada às entregas. Ele cuida da atribuição de entregadores, rastreamento das entregas em tempo real, cálculo de rotas eficientes, estimativas de tempos de entrega e atualizações de status para os clientes. Utilizando o Spring Boot e o Spring Data JPA, o serviço gerencia as informações de entrega no banco de dados de forma eficaz.

Em resumo, este projeto mostra como implementar um sistema de gerenciamento de pedidos eficiente usando uma arquitetura de micro serviços com o ecossistema Spring. Cada serviço é responsável por uma parte específica do sistema, garantindo autonomia, escalabilidade e fácil manutenção. A comunicação assíncrona baseada em eventos assegura uma coordenação eficaz entre os serviços, permitindo um processamento eficiente e robusto dos pedidos. O resultado é um sistema modular e flexível, pronto para atender à grandes demandas.

## Arquitetura

### VPN Privada e Gateway

Todos os microsserviços foram desenvolvidos dentro de uma VPN privada, comunicando-se com um gateway que coordena a entrada de requisições externas. Essa abordagem minimiza riscos e garante maior segurança para a aplicação.

### Microsserviços

Os principais microsserviços incluídos no projeto são:

- **Gerenciamento de Pedidos:** Lida com a criação, atualização e consulta de pedidos.
- **Gerenciamento de Produtos:** Administra o inventário de produtos, incluindo entrada e saída de estoque.
- **Gerenciamento de Clientes:** Gerencia informações e dados dos clientes.
- **Gerenciamento de Entregas:** Cuida da logística de entregas, incluindo rastreamento e atualização de status.

### Gateway

O gateway redireciona as requisições para os respectivos microsserviços, garantindo uma interface unificada e simplificada para o usuário.

### Spring Batch

Para facilitar a inserção de dados sobre os produtos em lote, utilizamos o Spring Batch. Esta ferramenta permite organizar grandes volumes de entrada de produtos de forma eficiente.

### RabbitMQ

A comunicação entre os serviços é realizada por meio do RabbitMQ, um serviço de mensageria que recebe e distribui mensagens acionando os processos necessários. Isso garante maior segurança na execução dos processos, pois as mensagens são enfileiradas e executadas pela aplicação consumidora conforme a disponibilidade.

### Google Maps API

Para a funcionalidade de localização, utilizamos a API do Google Maps, que fornece informações sobre distância, tempo e melhores rotas para a entrega. Além disso, uma tarefa cron foi implementada para atualizar periodicamente a posição das entregas para os usuários.

---

### Tecnologias Utilizadas
  - Microserviços
  - RabbitMq 
  - Spring Batch
  - Spring JPA
  - Spring Cloud Stream


### Integrantes 
 * Andre S Ferreira
 * Eduardo Vilhena
 * Giulliana Munhoz
 * Rafael Amaral
 * Tiago Santana

Obrigado por conferir nosso projeto! Se tiver alguma dúvida ou sugestão, não hesite em entrar em contato. 🚀✨
