package dev.java10x.CadastroDeNinjasProjeto.Ninjas;

import dev.java10x.CadastroDeNinjasProjeto.Missoes.MissoesModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NinjaDto {

    private Long id;
    private String nome;
    private String email;
    private String img_url;
    private String rank;
    private int idade;
    private MissoesModel missoes;
}
