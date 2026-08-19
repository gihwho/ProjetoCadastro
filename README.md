# Cadastro de Ninjas

API REST para cadastro e gestão de ninjas, com relacionamento com missões e persistência em banco H2 usando Spring Boot, JPA/Hibernate e Flyway.

## Visão geral

Este projeto foi desenvolvido como estudo prático com Java e Spring Boot, com foco em:

- CRUD de ninjas
- relacionamento entre ninja e missão
- persistência com JPA
- migrações com Flyway
- uso do banco H2 em ambiente local

## Stack atual

- Java 25
- Spring Boot 4.1.0-M1
- Spring Web MVC
- Spring Data JPA
- Hibernate ORM
- H2 Database
- Flyway
- Lombok
- Maven

## Requisitos

- Java 25+
- Maven 3.9+
- Git

## Como executar

1. Clone o repositório:

```bash
git clone <url-do-repositorio>
cd ProjetoCadastro
```

2. Inicie a aplicação:

```bash
./mvnw spring-boot:run
```

3. Acesse a API em:

- `http://localhost:8080`
- Console H2: `http://localhost:8080/h2-console`

## Estrutura do projeto

```text
ProjetoCadastro/
├── README.md
├── pom.xml
├── mvnw
├── .env
├── data/
│   └── CadastroDeNinjasDb.mv.db
├── src/
│   ├── main/
│   │   ├── java/dev/java10x/CadastroDeNinjasProjeto/
│   │   │   ├── CadastroDeNinjasProjetoApplication.java
│   │   │   ├── Ninjas/
│   │   │   │   ├── NinjaController.java
│   │   │   │   ├── NinjaService.java
│   │   │   │   ├── NinjaRepository.java
│   │   │   │   ├── NinjaMapper.java
│   │   │   │   ├── NinjaModel.java
│   │   │   │   └── NinjaDto.java
│   │   │   └── Missoes/
│   │   │       ├── MissoesController.java
│   │   │       ├── MissoesModel.java
│   │   │       └── MissoesRepository.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── db/
│   │           └── migrations/
│   │               └── V2__Add_rank_tb_cadastro.sql
│   └── test/
│       └── java/dev/java10x/CadastroDeNinjasProjeto/
│           └── CadastroDeNinjasProjetoApplicationTests.java
└── estudo/
    └── tecnologiasREADME.md
```

## Modelo de dados

A entidade principal é `NinjaModel`:

- `id`
- `nome`
- `email`
- `img_url`
- `rank`
- `idade`
- `missoes` (relacionamento com `MissoesModel`)

A entidade `MissoesModel` representa as missões associadas ao ninja:

- `id`
- `nomeMissao`
- `dificuldadeMissao`

## Endpoints da API

### Ninjas

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/ninjas/boasvindas` | Mensagem de boas-vindas |
| POST | `/ninjas/criar` | Cria um ninja |
| GET | `/ninjas/listar` | Lista todos os ninjas |
| GET | `/ninjas/listar/{id}` | Busca ninja por id |
| PATCH | `/ninjas/alterar/{id}` | Atualiza ninja por id |
| DELETE | `/ninjas/deletar/{id}` | Remove ninja por id |

### Missões

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/missoes/listar` | Lista missões |
| POST | `/missoes/criar` | Cria missão |
| PUT | `/missoes/alterar` | Atualiza missão |
| DELETE | `/missoes/deletar` | Remove missão |

## Banco de dados

A aplicação usa H2 em modo arquivo para persistir os dados localmente na pasta `data/`.

A configuração principal fica em `src/main/resources/application.properties`, e a aplicação também usa variáveis de ambiente para conexão com o banco.

## Testes

```bash
./mvnw test
```

## Documentação adicional

- [Guia de tecnologias](./estudo/tecnologiasREADME.md)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [JPA / Jakarta Persistence](https://jakarta.ee/specifications/persistence/)
