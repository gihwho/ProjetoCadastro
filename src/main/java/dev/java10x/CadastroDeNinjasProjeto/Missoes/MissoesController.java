package dev.java10x.CadastroDeNinjasProjeto.Missoes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missoes")
@Tag(name = "Missões", description = "Operações relacionadas às missões de ninjas")
public class MissoesController {

    @GetMapping("/listar")
    @Operation(summary = "Listar todas as missões", description = "Essa rota retorna uma lista de todas as missões cadastradas.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Missões listadas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhuma missão encontrada")
    })
    public String listar() {
        return "Missao listada com sucesso";
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar uma nova missão", description = "Essa rota cria uma nova missão com base nos dados fornecidos.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Missão criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public String criarMissao() {
        return "Missao criada com sucesso";
    }

    @PutMapping("/alterar")
    @Operation(summary = "Alterar uma missão existente", description = "Essa rota altera uma missão existente com base nos dados fornecidos.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Missão alterada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada")
    })
    public String alterarMissao() {
        return "Missao alterada com sucesso";
    }

    @DeleteMapping("/deletar")
    @Operation(summary = "Deletar uma missão existente", description = "Essa rota deleta uma missão existente com base nos dados fornecidos.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Missão deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada")
    })
    public String deletarMissao() {
        return "Missao deletada com sucesso";
    }
}
