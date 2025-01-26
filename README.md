# Hexagonal Architecture Project

Este projeto faz parte de um curso de **Arquitetura Hexagonal** e utiliza diversas tecnologias e ferramentas para criar uma aplicação Java robusta e escalável.

---

## **Diagrama do Projeto**
![Texto Alternativo](Diagrama%20do%20projeto.png)

---

## **Tecnologias Utilizadas**

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

---

## **Arquitetura**

O projeto segue os princípios da **Arquitetura Hexagonal**, que visa:
- **Isolamento da lógica de negócios** do restante do sistema.
- **Portas e Adaptadores** para comunicação entre camadas, permitindo flexibilidade na escolha de frameworks e tecnologias.

### **Camadas do Projeto**
1. **Application**:
   - Contém a lógica de negócios, entidades e interfaces para os casos de uso.
   - Implementa os casos de uso e orquestra o fluxo de dados entre o domínio e os adaptadores.
2. **Adapters**:
   - **Entrada**: APIs REST, eventos Kafka, etc.
   - **Saída**: Comunicação com MongoDB, serviços externos via OpenFeign, etc.
3. **Config**: Contém as classes de configuração do projeto.

---

## **Fluxo da Aplicação**

1. **Criação de Cliente**:
   - Recebe requisição com dados do cliente e CEP.
   - Consulta endereço via API externa (simulada com WireMock).
   - Salva cliente no MongoDB.
   - Envia CPF para validação via Kafka (tópico: `tp-cpf-validation`).

2. **Validação de CPF**:
   - Recebe resultado da validação via Kafka (tópico: `tp-cpf-validated`).
   - Atualiza status de validação do cliente.

---

## **Endpoints API**

| Método | Endpoint                         | Descrição               | Payload Exemplo |
|--------|----------------------------------|-------------------------|-----------------|
| POST   | `/api/v1/customers`              | Cria um novo cliente    | [Ver exemplo](#post-customer) |
| GET    | `/api/v1/customers/{customerId}` | Busca um cliente por id | -               |
| PUT    | `/api/v1/customers/{customerId}` | Atualiza um cliente     | [Ver exemplo](#post-customer) |
| DELETE | `/api/v1/customers/{customerId}` | Deleta um cliente por id| -               |

### **Exemplo de Payload (POST/PUT)**
```json
{
  "name": "John Doe",
  "cpf": "12345678900",
  "zipCode": "12345678"
}
```

---

## **Configuração**

### **Pré-requisitos**

- **Java 17** ou superior instalado.
- **Docker** e **Docker Compose** instalados.
- **MongoDB** rodando na porta padrão (`27017`).
- **Kafka** configurado e rodando na porta `9092`.
- **WireMock** para simular a API de endereços (porta `8082`).

### **Configuração do WireMock**
1. Baixe o WireMock standalone:
   ```bash
   wget https://repo1.maven.org/maven2/org/wiremock/wiremock-standalone/3.0.0/wiremock-standalone-3.0.0.jar
   ```
2. Crie o arquivo de mapping para a API de endereços:
   ```json
   {
     "request": {
       "method": "GET",
       "urlPattern": "/addresses/[0-9]{8}"
     },
     "response": {
       "status": 200,
       "headers": {
         "Content-Type": "application/json"
       },
       "jsonBody": {
         "street": "Rua Exemplo",
         "city": "Cidade Exemplo",
         "state": "Estado Exemplo"
       }
     }
   }
   ```
3. Inicie o WireMock:
   ```bash
   java -jar wiremock-standalone-3.0.0.jar --port 8082
   ```

### **Configuração do Kafka**
- Crie os seguintes tópicos:
   - `tp-cpf-validation`: Para envio de CPFs para validação.
   - `tp-cpf-validated`: Para recebimento dos resultados de validação.

---

## **Executando o Projeto**

1. Clone o repositório:
   ```bash
   git clone https://github.com/renanalencardev/hexagonal.git
   cd hexagonal
   ```
2. Inicie os serviços com Docker Compose:
   ```bash
   docker-compose -f docker-local/docker-compose.yml up -d
   ```
3. Execute o WireMock (em outro terminal):
   ```bash
   java -jar wiremock-standalone-3.0.0.jar --port 8082
   ```
4. Inicie a aplicação:
   ```bash
   ./gradlew bootRun
   ```

---

## **Configurações do Ambiente**

As configurações da aplicação estão no arquivo `src/main/resources/application.yml`:
```yaml
renan:
  client:
    address:
      url: http://localhost:8082/addresses
spring:
  data:
    mongodb:
      host: localhost
      port: 27017
      authentication-database: admin
      username: root
      password: example
      database: hexagonal
```

---

## **Testes**

### **Testes de Arquitetura**

O projeto utiliza o ArchUnit para garantir que as convenções arquiteturais sejam seguidas. Os principais testes incluem:

#### **Testes de Convenção de Nomenclatura**
- Verifica se as classes seguem o padrão de nomenclatura correto
- Garante que as classes estejam nos pacotes apropriados
- Exemplos de regras:
   - Classes `*Consumer` devem estar no pacote `adapters.in.consumer`
   - Classes `*Controller` devem estar no pacote `adapters.in.controller`
   - Classes `*Adapter` devem estar no pacote `adapters.out`
   - Classes `*UseCase` devem estar no pacote `application.core.usecase`

#### **Testes de Arquitetura em Camadas**
- Valida as dependências entre as camadas da arquitetura hexagonal
- Regras de acesso entre camadas:
   - `AdaptersIn` só pode ser acessada por `Config`
   - `AdaptersOut` só pode ser acessada por `Config`
   - `UseCase` só pode ser acessada por `Config`
   - `PortsIn` pode ser acessada por `UseCase` e `AdaptersIn`
   - `PortsOut` pode ser acessada por `UseCase` e `AdaptersOut`
   - `Config` não pode ser acessada por nenhuma camada

---

## **Observações**

- Certifique-se de que todas as dependências estão configuradas corretamente.
- O WireMock deve estar rodando antes de iniciar a aplicação.
- Os tópicos Kafka devem ser criados antes de executar a aplicação.
- O MongoDB deve estar acessível com as credenciais configuradas.

---

Projeto em desenvolvimento...
```