# Cadastro de Ninjas

Projeto em Java + Spring Boot para cadastro, listagem, edição, exclusão e visualização de ninjas, com relacionamento com missões e persistência em banco H2.

## Visão geral

Este projeto foi desenvolvido como estudo prático com foco em:

- CRUD de ninjas
- relacionamento com missões
- uso de JPA/Hibernate
- persistência em H2
- migrações com Flyway
- consumo/visualização via Spring MVC + Thymeleaf
- documentação de API com Swagger/OpenAPI

## Stack utilizada

- Java 21
- Spring Boot 3.x
- Spring Web
- Spring MVC
- Spring Data JPA
- Hibernate ORM
- H2 Database
- Flyway
- Thymeleaf
- Lombok
- Maven
- Swagger / springdoc-openapi
- dotenv / arquivo .env

## Pré-requisitos

- Java 21+
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

4. Acesse os pontos principais:

- API REST: `http://localhost:8080`
- UI de gerenciamento: `http://localhost:8080/ninjas/ui/listar`
- Console H2: `http://localhost:8080/h2-console`
- Swagger UI: `http://localhost:8080/swagger-ui/index.html#`

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
│   │   │   │   ├── NinjaControllerUi.java
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
│   │       ├── templates/
│   │       │   ├── listarNinjas.html
│   │       │   ├── adicionarNinja.html
│   │       │   ├── editarNinja.html
│   │       │   └── detalhesNinja.html
│   │       └── db/
│   │           └── migrations/
│   │               └── V2__Add_rank_tb_cadastro.sql
│   └── test/
│       └── java/dev/java10x/CadastroDeNinjasProjeto/
│           └── CadastroDeNinjasProjetoApplicationTests.java
├── estudo/
│   └── tecnologiasREADME.md
└── target/
```

## Modelo de dados

### Ninja

- `id`
- `nome`
- `email`
- `img_url`
- `rank`
- `idade`
- `missoes`

### Missão

- `id`
- `nomeMissao`
- `dificuldadeMissao`

## Endpoints da API REST

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

## UI do projeto

Além da API REST, o projeto também possui páginas de interface com Thymeleaf:

- `/ninjas/ui/listar` → listagem de ninjas
- `/ninjas/ui/adicionar` → formulário de cadastro
- `/ninjas/ui/listar/{id}` → detalhes do ninja
- `/ninjas/ui/alterar/{id}` → formulário de edição
- `/ninjas/ui/deletar/{id}` → exclusão

## Banco de dados

A aplicação usa H2 em modo arquivo para persistir os dados localmente na pasta `data/`.

A configuração principal fica em `src/main/resources/application.properties` e usa variáveis do arquivo `.env` para a conexão.

## Swagger/OpenAPI

A documentação da API foi habilitada com o Springdoc OpenAPI.

Acesse:

```text
http://localhost:8080/swagger-ui/index.html#
```

## Testes

```bash
./mvnw test
```

## Ferramentas e tecnologias usadas ao longo do projeto

- Java
- Spring Boot
- Maven
- JPA / Hibernate
- Flyway
- H2 Database
- Thymeleaf
- HTML/CSS
- Spring MVC
- Swagger / springdoc-openapi
- Git
- VS Code / IntelliJ IDEA
- arquivo `.env` para variáveis de ambiente

## Documentação adicional

- [Guia de tecnologias](./estudo/tecnologiasREADME.md)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Thymeleaf](https://www.thymeleaf.org/)
- [Swagger UI](https://swagger.io/tools/swagger-ui/)
