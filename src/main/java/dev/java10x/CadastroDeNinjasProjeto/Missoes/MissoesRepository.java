package dev.java10x.CadastroDeNinjasProjeto.Missoes;

import org.springframework.data.jpa.repository.JpaRepository;

//JPA escaneia o MissoesModel e passa o tipo de ID contido nele, basicamente, para
//conectar classes da aplicação a tabelas do banco de dados relacional
public interface MissoesRepository extends JpaRepository<MissoesModel, Long> {
}
