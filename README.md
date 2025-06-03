Consulta Crédito API

Este projeto é uma API RESTful desenvolvida para consulta e gerenciamento de informações de crédito. Ele permite a busca de dados de crédito através do número da NFSe ou do número do crédito, retornando informações detalhadas. Além disso, toda consulta realizada gera um evento Kafka, possibilitando a integração assíncrona com sistemas downstream.
⚙️ Funcionalidades Principais

    🔍 Buscar créditos por número da NFSe

    🔍 Buscar créditos por número do crédito

    🔄 Publicar eventos de consulta no Kafka para integração assíncrona

    ❗ Tratamento de erros com exceções customizadas, como ResourceNotFoundException

🛠️ Tecnologias Utilizadas

    Java 17

    Spring Boot 3.x

    Spring Data JPA (Hibernate)

    Apache Kafka (com Spring Kafka)

    MySQL ou PostgreSQL

    JUnit 5 + Mockito (para testes unitários)

    Docker + Docker Compose

🧱 Arquitetura do Projeto

A arquitetura segue o princípio de separação de responsabilidades em camadas bem definidas:

    Interfaces para definição de contratos (CreditoService, CreditoRepository)

    Implementações concretas, como CreditoServiceImpl, concentrando a lógica de negócio

    Uso opcional de Generics para reaproveitamento de lógica comum, especialmente em mapeadores Mapper<Entity, DTO> e serviços genéricos

Essa abordagem pragmática visa manter o projeto simples, limpo e modular.
🚀 Como Executar o Projeto Localmente
1. Clone o repositório

git clone https://github.com/BUGB2AR/credito-backend.git
cd credito-backend

2. Suba os serviços com Docker Compose

docker compose up -d

    Caso deseje acompanhar os logs:

docker compose up

3. Execute a aplicação

Se estiver utilizando o IntelliJ IDEA, basta rodar o projeto como uma aplicação Spring Boot.
A API estará disponível na porta 8085.
Alternativa com Maven:

mvn clean install
mvn spring-boot:run

📌 Endpoints Disponíveis

    Buscar por NFSe
    GET http://localhost:8088/api/creditos/{numeroNfse}

    Buscar por número do crédito
    GET http://localhost:8088/api/creditos/numero/{numeroCredito}

    Buscar por ID (para testes)
    GET http://localhost:8088/api/creditos/credito/{id}

⚠️ Observações Importantes

Na primeira execução do projeto, é necessário configurar o comportamento do Hibernate e a inicialização do SQL da seguinte forma no application.properties:

spring.jpa.hibernate.ddl-auto=none
spring.sql.init.mode=always

Após a primeira execução (que irá criar as tabelas e popular os dados), altere:

spring.sql.init.mode=never

Isso evitará que os scripts de inicialização sejam reexecutados nas próximas execuções.
🧪 Testes

Os testes unitários utilizam JUnit 5 e Mockito para validação da lógica de negócios.
