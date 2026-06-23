package dev.java10x.CadastroDeNinjasProjeto.Ninjas;

import dev.java10x.CadastroDeNinjasProjeto.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Entity transforma uma classe em uma entidade no banco de dados
@Entity
@Table(name = "tb_cadastro_ninja")
@Data   //Cria automaticamente os getters e setters dos atributos
@NoArgsConstructor
@AllArgsConstructor
public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(unique = true)
    private String email;

    @Column(name = "img_url")
    private String img_url;

    @Column(name = "idade")
    private int idade;

    // @ManyToOne um ninja tem uma única missão
    @ManyToOne
    @JoinColumn(name = "missoes_id") //Foreign Key ou chave estrangeira
    private MissoesModel missoes;
}
