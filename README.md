# Cadastro de Ninjas

Uma API RESTful para gerenciamento de cadastro de Ninjas desenvolvida com Spring Boot 4.1, JPA/Hibernate e banco de dados H2.

---

## Descrição do Projeto

Este projeto é uma aplicação backend que fornece endpoints para criar, listar, atualizar e deletar registros de Ninjas. É um projeto educacional que demonstra boas práticas de desenvolvimento com Spring Boot, incluindo persistência de dados com JPA/Hibernate.

---

## Stack Tecnológico

- **Java 25** - Linguagem de programação
- **Spring Boot 4.1.0-M1** - Framework web
- **Spring Data JPA** - Abstração de persistência
- **Hibernate** - Implementação ORM
- **H2 Database** - Banco de dados em memória
- **Flyway** - Versionamento de migrations
- **Lombok** - Redução de boilerplate
- **Maven** - Gerenciador de dependências

Para saber mais sobre essas tecnologias, veja [estudo/tecnologiasREADME.md](./estudo/tecnologiasREADME.md)

---

## Como Executar

### Pré-requisitos
- Java 25 ou superior instalado
- Maven 3.6+ instalado

### Passos

1. **Clone o repositório**
```bash
git clone <repository-url>
cd ProjetoCadastro
```

2. **Execute a aplicação**
```bash
./mvnw spring-boot:run
```

A aplicação será iniciada em `http://localhost:8080`

---

## Endpoints da API

### Ninjas

| Método | Endpoint             | Descrição |
|--------|----------------------|-----------|
| GET | `/ninjas/boasvindas` | Mensagem de boas-vindas |
| GET | `/ninjas/listar`     | Listar todos os Ninjas |
| POST | `/ninjas/criar`      | Criar novo Ninja |
| GET | `/ninjas/listar/id`  | Listar Ninja por ID |
| PUT | `/ninjas/alterar/id` | Atualizar um Ninja |
| DELETE | `/ninjas/deletar/id` | Deletar um Ninja |

### Exemplo de Requisição

**GET** - Listar todos os Ninjas:
```bash
curl http://localhost:8080/ninjas/listar
```

**POST** - Criar novo Ninja:
```bash
curl -X POST http://localhost:8080/ninjas/criar
```

---

## Estrutura do Projeto

```
src/
├── main/
│   ├── java/dev/java10x/CadastroDeNinjasProjeto/
│   │   ├── Ninjas/
│   │   │   ├── NinjaController.java    # Endpoints da API
│   │   │   ├── NinjaService.java       # Lógica de negócio
│   │   │   ├── NinjaRepository.java    # Acesso aos dados
│   │   │   └── NinjaModel.java         # Entidade/Modelo
│   │   └── Application.java            # Classe principal
│   └── resources/
│       ├── db/migration/               # Scripts de migration (Flyway)
│       └── application.properties      # Configurações
└── test/                               # Testes unitários
```

### Padrão de Arquitetura

O projeto segue o padrão de **3 camadas**:

```
Controller (Apresentação)
    ↓
Service (Negócio)
    ↓
Repository (Dados)
    ↓
Banco de Dados
```

1. **Controller** - Recebe requisições HTTP e retorna respostas
2. **Service** - Implementa regras de negócio
3. **Repository** - Acessa e persiste dados (via JPA/Hibernate)
4. **Model** - Representa a entidade no banco

---

## Configurações

### application.properties

As configurações da aplicação estão em `src/main/resources/application.properties`

---

## Documentação Adicional

- **[Guia de Tecnologias](./estudo/tecnologiasREADME.md)** - Explicação detalhada sobre JPA, Hibernate, ORM e Spring Data JPA
- **[Spring Boot Documentation](https://spring.io/projects/spring-boot)**
- **[JPA Specification](https://jakarta.ee/specifications/persistence/)**

---

## Testes

Executar testes:
```bash
./mvnw test
```

---

## Padrões de Código Utilizados

- **REST API** - Arquitetura de serviços web
- **MVC** - Model-View-Controller (adaptado para API)
- **Dependency Injection** - Injeção de dependências com Spring
- **ORM** - Object-Relational Mapping com Hibernate
- **Repository Pattern** - Abstração de acesso a dados

---

## Desenvolvido com

- Spring Boot
- Java
- JPA/Hibernate
- H2 Database
- Maven

---

## Licença

Este projeto é fornecido como educacional.

---

Desenvolvido para aprendizado de Spring Boot e Java
