package dev.java10x.CadastroDeNinjasProjeto.Ninjas;

import dev.java10x.CadastroDeNinjasProjeto.Missoes.MissoesModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NinjaDto {

    @Schema(description = "Identificador único do ninja", example = "1")
    private Long id;

    @Schema(description = "Nome do ninja", example = "Naruto Uzumaki")
    private String nome;

    @Schema(description = "Email do ninja", example = "naruto@konoha.com")
    private String email;

    @Schema(description = "URL da imagem do ninja", example = "https://images.unsplash.com/photo-...")
    private String img_url;

    @Schema(description = "Rank do ninja", example = "Genin")
    private String rank;

    @Schema(description = "Idade do ninja", example = "18")
    private Integer idade;

    @Schema(description = "Missão vinculada ao ninja")
    private MissoesModel missoes;
}
