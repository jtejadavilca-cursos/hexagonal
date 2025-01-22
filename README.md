# Hexagonal Architecture Project

Este projeto faz parte de um curso de **Arquitetura Hexagonal** e utiliza diversas tecnologias e ferramentas para criar uma aplicação Java robusta e escalável.

## Diagrama do Projeto
![Texto Alternativo](Diagrama%20do%20projeto.png)


## Tecnologias Utilizadas

- **Java 17**: Linguagem de programação principal.
- **Spring Boot**: Framework para simplificar a criação de aplicações Java.
- **MongoDB**: Banco de dados NoSQL utilizado como armazenamento de dados.
- **MapStruct**: Framework para mapeamento de objetos (DTOs e entidades).
- **Lombok**: Reduz a verbosidade do código, gerando automaticamente getters, setters, construtores, etc.
- **Apache Kafka**: Plataforma de streaming de eventos distribuídos.
- **OpenFeign**: Cliente HTTP para consumir APIs de forma simples.
- **Bean Validation**: Validação de objetos usando anotações.
- **Docker**: Criação e manipulação de containers.
- **WireMock**: Ferramenta para simular APIs HTTP, utilizada para simular a API de endereços.

## Arquitetura

O projeto segue os princípios da **Arquitetura Hexagonal**, que visa:
- **Isolamento da lógica de negócios** do restante do sistema.
- **Portas e Adaptadores** para comunicação entre camadas, permitindo flexibilidade na escolha de frameworks e tecnologias.

### Camadas do Projeto

1. **Application**: Contém a lógica de negócios, entidades, interfaces para os casos de uso, 
implementa os casos de uso e orquestra o fluxo de dados entre o domínio e os adaptadores.
2. **Adapters**: Inclui:
    - **Entrada**: APIs REST, eventos Kafka, etc.
    - **Saída**: Comunicação com MongoDB, serviços externos via OpenFeign, etc.
3. **Config**: Contém as classes de configuração do projeto.

## Endpoints API

| Método | Endpoint                         | Descrição            |
|--------|----------------------------------|----------------------|
| POST   | `/api/v1/customers`              | Cria um novo cliente |
| GET    | `/api/v1/customers/{customerId}` | Busca um cliente por id |
| PUT    | `/api/v1/customers/{customerId}` | Atualiza um cliente por id |
| DELETE | `/api/v1/customers/{customerId}` | Deleta um cliente por id |


## Configuração

### Pré-requisitos

- **Java 17** ou superior instalado.
- **MongoDB** rodando na porta padrão (`27017`).
- **Kafka** configurado e rodando.
- Acesso à internet para consumo de APIs externas com OpenFeign.
- **WireMock** para simular a API de endereços.

### Executando o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/hexagonal.git
   cd hexagonal
   ```
2. Inicie os serviços Docker (MongoDB, Kafka, etc.):
   ```bash
   docker-compose -f docker-local/docker-compose.yml up
   ```
3. Execute o WireMock para simular a API de endereços:
   ```bash
   java -jar wiremock-standalone-<version>.jar --port 8082
   ```
4. Inicie a aplicação Spring Boot:
   ```bash
   ./gradlew bootRun
   ```

### Testes

Para executar os testes, utilize o comando:
```bash
./gradlew test
```

### Observações

Certifique-se de que todas as dependências estão corretamente configuradas e que os serviços necessários estão em execução antes de iniciar a aplicação.

Projeto em desenvolvimento...