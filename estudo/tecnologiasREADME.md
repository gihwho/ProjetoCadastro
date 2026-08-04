# Guia de Tecnologias - Spring Boot, JPA, Hibernate e ORM

Documentação detalhada sobre as principais tecnologias usadas em projetos Spring Boot com persistência de dados.

---

## JPA (Java Persistence API)

### O que é?
JPA é uma **especificação Java** que define como trabalhar com dados relacionais em aplicações Java. É um padrão/interface que define as operações básicas de persistência de dados (CRUD - Create, Read, Update, Delete).

### Para que serve?
Eliminar a necessidade de escrever SQL manualmente. O desenvolvedor trabalha com objetos Java, e a JPA se encarrega de traduzir para SQL.

### Exemplo de uso:
```java
@Entity
public class NinjaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String email;
}
```

### Vantagens:
- Abstração do banco de dados
- Código mais orientado a objetos
- Portabilidade entre diferentes bancos
- Padrão definido pela comunidade Java

### Desvantagens:
- Menos controle fino sobre SQL
- Queries complexas podem ser verbosas

---

## Hibernate

### O que é?
Hibernate é a **implementação mais popular de JPA**. É um ORM (Object-Relational Mapping) que fornece a implementação prática das interfaces definidas por JPA.

### Por que existe?
JPA é apenas uma **especificação** (um contrato/interface). Hibernate é a **implementação real** que executa as operações no banco de dados.

### Relação JPA vs Hibernate:
```
┌─────────────────────────────┐
│   Sua Aplicação Java        │
└────────────┬────────────────┘
             │
┌────────────▼────────────────┐
│  JPA (Especificação)        │
│  - Define interfaces        │
│  - Define annotations       │
│  - Define padrões           │
└────────────┬────────────────┘
             │
┌────────────▼────────────────┐
│  Hibernate (Implementação)  │
│  - Implementa JPA           │
│  - Gera SQL                 │
│  - Gerencia cache           │
│  - Gerencia transações      │
└────────────┬────────────────┘
             │
┌────────────▼────────────────┐
│  JDBC (Driver)              │
│  - Comunica com BD          │
└────────────┬────────────────┘
             │
┌────────────▼────────────────┐
│  Banco de Dados (H2, MySQL) │
└─────────────────────────────┘
```

### Conceitos principais do Hibernate:

#### 1. **Ciclo de Vida das Entidades**
```
Transient → Managed → Detached → Removed
(Novo)      (BD)      (Fora)     (Deletado)
```

#### 2. **Lazy Loading vs Eager Loading**
```java
// EAGER - Carrega relacionamento imediatamente
@OneToMany(fetch = FetchType.EAGER)
private List<Missao> missoes;

// LAZY - Carrega relacionamento quando acessado (padrão)
@OneToMany(fetch = FetchType.LAZY)
private List<Missao> missoes;
```

#### 3. **Cascade e Orphan Removal**
```java
// CASCADE - Propaga operações para relacionamentos
@OneToMany(cascade = CascadeType.ALL)
private List<Missao> missoes;

// ORPHAN_REMOVAL - Deleta registros órfãos
@OneToMany(orphanRemoval = true)
private List<Missao> missoes;
```

#### 4. **Caching**
Hibernate mantém cache de entidades em memória para melhorar performance.

### Exemplo de Operação no Hibernate:
```java
// 1. Criar entidade
Ninja ninja = new Ninja();
ninja.setNome("Naruto");

// 2. Salvar
ninjaRepository.save(ninja);  // Hibernate executa: INSERT INTO ninja (nome) VALUES ('Naruto')

// 3. Buscar
Ninja encontrado = ninjaRepository.findById(1L);  // SELECT * FROM ninja WHERE id = 1

// 4. Atualizar
encontrado.setNome("Sasuke");
ninjaRepository.save(encontrado);  // UPDATE ninja SET nome = 'Sasuke' WHERE id = 1

// 5. Deletar
ninjaRepository.deleteById(1L);  // DELETE FROM ninja WHERE id = 1
```

---

## ORM (Object-Relational Mapping)

### O que é?
ORM é um **padrão de programação** que permite mapear objetos de uma aplicação para tabelas de um banco de dados relacional, e vice-versa.

### Como funciona?

```
MUNDO JAVA              MUNDO BANCO DE DADOS
─────────────          ──────────────────────
Classe Java      ←→    Tabela
Atributo         ←→    Coluna
Instância/Objeto ←→    Linha
Relacionamento   ←→    Foreign Key
```

#### Exemplo Prático:
```java
@Entity
@Table(name = "ninja")
public class NinjaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;           // ←→ COLUMN: id (PK)
    
    @Column(name = "nome")
    private String nome;       // ←→ COLUMN: nome VARCHAR
    
    @Column(name = "email")
    private String email;      // ←→ COLUMN: email VARCHAR
    
    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;     // ←→ FOREIGN KEY: equipe_id
}
```

Isso cria:
```sql
CREATE TABLE ninja (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255),
    email VARCHAR(255),
    equipe_id BIGINT,
    FOREIGN KEY (equipe_id) REFERENCES equipe(id)
);
```

### Vantagens do ORM:

| Vantagem | Descrição |
|----------|-----------|
| Produtividade | Menos linhas de SQL, mais foco em lógica |
| Segurança | Proteção contra SQL Injection |
| Portabilidade | Trocar banco de dados com mudanças mínimas |
| Legibilidade | Código mais compreensível e orientado a objetos |
| Manutenção | Mudanças de schema refletem automaticamente |
| Type Safety | Verificação de tipos em tempo de compilação |

### Desvantagens do ORM:

| Desvantagem | Descrição |
|-------------|-----------|
| Performance | Pequeno overhead comparado com SQL puro |
| Complexidade | Curva de aprendizado |
| Queries Complexas | Podem ficar verbosas ou difíceis |
| N+1 Problem | Risco de múltiplas queries desnecessárias |
| Menos Controle | Não dá controle fino sobre otimizações SQL |

### N+1 Problem (Exemplo):
```java
// RUIM - Faz N+1 queries (1 para Ninjas + N para cada Equipe)
List<Ninja> ninjas = ninjaRepository.findAll();
for (Ninja n : ninjas) {
    System.out.println(n.getEquipe().getNome());  // Query para cada ninja!
}

// BOM - Faz 1 query com JOIN
List<Ninja> ninjas = ninjaRepository.findAllWithEquipe();
```

---

## Spring Data JPA

### O que é?
Abstração do Spring que simplifica ainda mais o uso de JPA. Fornece classes base e interfaces prontas para CRUD.

### Por que existe?
Reduzir ainda mais boilerplate. Ao invés de implementar Repository manualmente, você herda de interfaces prontas.

### Stack Completo:
```
Spring Data JPA
    |
   JPA (Especificação)
    |
Hibernate (Implementação)
    |
JDBC (Conexão)
    |
Banco de Dados
```

### Exemplo:
```java
// Você só faz isso:
public interface NinjaRepository extends JpaRepository<NinjaModel, Long> {
    List<NinjaModel> findByNome(String nome);
    List<NinjaModel> findByEquipeId(Long equipeId);
}

// Spring Data JPA fornece automaticamente:
// - save()
// - findById()
// - findAll()
// - update()
// - delete()
// - e muito mais!
```

### Query Derivation (Magia do Spring Data):
```java
// Spring Data gera SQL automaticamente pelo nome do método!

findByNome(String nome)
// SELECT * FROM ninja WHERE nome = ?

findByNomeAndEmail(String nome, String email)
// SELECT * FROM ninja WHERE nome = ? AND email = ?

findByEquipeIdOrderByNomeAsc(Long equipeId)
// SELECT * FROM ninja WHERE equipe_id = ? ORDER BY nome ASC

findByNomeContainingIgnoreCase(String nome)
// SELECT * FROM ninja WHERE LOWER(nome) LIKE LOWER(?)
```

### Query Customizada:
```java
public interface NinjaRepository extends JpaRepository<NinjaModel, Long> {
    @Query("SELECT n FROM NinjaModel n WHERE n.ativo = true ORDER BY n.nome")
    List<NinjaModel> findAllAtivos();
    
    @Query("SELECT n FROM NinjaModel n WHERE n.equipe.id = ?1")
    List<NinjaModel> findByEquipe(Long equipeId);
}
```

---

## Relacionamentos em JPA/Hibernate

### 1. One-to-Many (Um para Muitos)
```java
@Entity
public class Equipe {
    @Id
    private Long id;
    private String nome;
    
    @OneToMany(mappedBy = "equipe")
    private List<Ninja> ninjas;
}

@Entity
public class Ninja {
    @Id
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;
}
```

### 2. Many-to-Many (Muitos para Muitos)
```java
@Entity
public class Ninja {
    @ManyToMany
    @JoinTable(
        name = "ninja_missao",
        joinColumns = @JoinColumn(name = "ninja_id"),
        inverseJoinColumns = @JoinColumn(name = "missao_id")
    )
    private List<Missao> missoes;
}

@Entity
public class Missao {
    @ManyToMany(mappedBy = "missoes")
    private List<Ninja> ninjas;
}
```

### 3. One-to-One (Um para Um)
```java
@Entity
public class Ninja {
    @OneToOne
    @JoinColumn(name = "perfil_id")
    private PerfilNinja perfil;
}

@Entity
public class PerfilNinja {
    @OneToOne(mappedBy = "perfil")
    private Ninja ninja;
}
```

---

## Anotações Principais do JPA

| Anotação | Uso |
|----------|-----|
| @Entity | Define uma classe como entidade (tabela) |
| @Table | Customiza nome da tabela |
| @Id | Define chave primária |
| @GeneratedValue | Auto-incremento da chave |
| @Column | Customiza coluna |
| @Transient | Ignora campo (não cria coluna) |
| @ManyToOne | Relacionamento n-para-1 |
| @OneToMany | Relacionamento 1-para-n |
| @ManyToMany | Relacionamento n-para-n |
| @OneToOne | Relacionamento 1-para-1 |
| @JoinColumn | Customiza foreign key |
| @JoinTable | Customiza tabela de junção |
| @Query | Query customizada (HQL/JPQL) |
| @Transactional | Define transação |
| @Enumerated | Mapeia enumeração |
| @Temporal | Customiza formato de data |
| @Lob | Large Object (arquivo, texto grande) |

---

## Transações em JPA/Hibernate

### ACID Properties:
- Atomicity (Atomicidade) - Tudo ou nada
- Consistency (Consistência) - Estado válido do BD
- Isolation (Isolamento) - Independência entre transações
- Durability (Durabilidade) - Dados persistem após commit

### Exemplo:
```java
@Service
public class NinjaService {
    
    @Transactional  // Spring gerencia a transação
    public void criarNinja(Ninja ninja) {
        ninjaRepository.save(ninja);
        // Automaticamente faz COMMIT se sucesso
        // Faz ROLLBACK se exceção
    }
}
```

---

## Performance e Boas Práticas

### 1. Evitar N+1 Problem
```java
// Ruim
List<Ninja> ninjas = findAll();
for (Ninja n : ninjas) System.out.println(n.getEquipe()); // N queries extra

// Bom - Use JOIN FETCH ou Eager Loading
@Query("SELECT DISTINCT n FROM Ninja n JOIN FETCH n.equipe")
List<Ninja> findAllWithEquipe();
```

### 2. Usar Projeções
```java
@Query("SELECT new map(n.id as id, n.nome as nome) FROM Ninja n")
List<Map<String, Object>> findProjecao();
```

### 3. Pagination
```java
// Retorna Page com metadata (total, páginas, etc)
Page<Ninja> ninjas = ninjaRepository.findAll(PageRequest.of(0, 10));
```

### 4. Batch Processing
```java
List<Ninja> ninjas = new ArrayList<>();
for (int i = 0; i < 10000; i++) {
    ninjas.add(new Ninja("Ninja " + i));
    if (i % 1000 == 0) {
        ninjaRepository.saveAll(ninjas);
        ninjas.clear();
    }
}
```

---

## Testando com JPA

### Usando H2 em Testes:
```java
@DataJpaTest
class NinjaRepositoryTest {
    
    @Autowired
    private NinjaRepository repository;
    
    @Test
    void shouldSaveNinja() {
        Ninja ninja = new Ninja();
        ninja.setNome("Test Ninja");
        
        Ninja saved = repository.save(ninja);
        
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getNome()).isEqualTo("Test Ninja");
    }
}
```

---

## Recursos Adicionais

- [JPA Documentation](https://jakarta.ee/specifications/persistence/)
- [Hibernate Documentation](https://hibernate.org/orm/)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [H2 Database](http://www.h2database.com/)

---

Desenvolvido como guia de aprendizado de Spring Boot e Persistência de Dados