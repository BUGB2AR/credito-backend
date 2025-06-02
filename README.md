Consulta Crédito API
Sobre o projeto

Este projeto é uma API RESTful para consulta e gerenciamento de informações de crédito.
Ele permite buscar dados de crédito pelo número da NFSe ou pelo número do crédito, com retorno de dados detalhados.
Além disso, eventos de consulta são publicados em um tópico Kafka para processamento assíncrono, garantindo integração com sistemas downstream.
Funcionalidades principais

    Buscar créditos por número da NFSe.

    Buscar crédito por número do crédito.

    Publicar eventos de consulta no Kafka para integração assíncrona.

    Tratamento de erros com exceções customizadas (ex: ResourceNotFoundException).

Tecnologias Utilizadas

    Java 17

    Spring Boot 3.x

    Spring Data JPA (Hibernate)

    Kafka (Spring Kafka)

    Banco de dados relacional (MySQL ou PostgreSQL)

    JUnit 5 + Mockito para testes unitários

    Docker + Docker compose


A arquitetura do projeto segue os princípios de camadas separadas de responsabilidade, utilizando:

    Interfaces para definir contratos dos serviços (CreditoService) e dos repositórios (CreditoRepository);

    Implementações concretas (ex: CreditoServiceImpl) que centralizam a lógica de negócio;

    Generics (quando aplicável) para permitir reutilização de mapeamentos e comportamentos comuns, especialmente no uso de Mapper<Entity, DTO> e possíveis GenericService.
    
    usei essa forma pragmática para manter o projeto simples, limpo e modular.


Como subir o projeto

Siga os passos abaixo para executar o projeto localmente:
1. Clone o repositório

git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio

2. Comandos para subir o banco e o Kafka

docker compose up -d ou se prefirir ver os logs (docker compose up apenas).

3. Usando a ide Intellij basta rodar agora o projeto irá subir na porta 8085.
 
obs: outras alternativas para rodar via maven

   mvn clean install
   mvn spring-boot:run

endpoints + endpoint extra

- http://localhost:8088/api/creditos/{numeroNfse}
- http://localhost:8088/api/creditos/numero/{numeroCredito}
- http://localhost:8088/api/creditos/credito/{id} -> foi criado apenas para fins de testes com id.









