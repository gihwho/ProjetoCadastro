package dev.java10x.CadastroDeNinjasProjeto.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
@Tag(name = "Ninjas", description = "Operações relacionadas ao cadastro, listagem, atualização e remoção de ninjas")
public class NinjaController {

    @Autowired
    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    @Operation(summary = "Mensagem de boas vindas", description = "Essa rota retorna uma mensagem de boas-vindas.")
    public String boasVindas() {
        return "Essa é a minha primeira mensagem nessa rota!";
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar um novo ninja", description = "Essa rota cria um novo ninja com base nos dados fornecidos.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<String> criarNinja(
            @org.springframework.web.bind.annotation.RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do ninja a ser criado", required = true,
                    content = @Content(schema = @Schema(implementation = NinjaDto.class)))
            NinjaDto ninjaDto) {
        NinjaDto ninja = ninjaService.criarNinja(ninjaDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso!\n" +
                        "ID: " + ninja.getId() + ",\n" +
                        "Nome: " + ninja.getNome());
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar todos os ninjas", description = "Essa rota retorna uma lista de todos os ninjas cadastrados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ninjas listados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum ninja encontrado")
    })
    public ResponseEntity<List<NinjaDto>> listarNinjas() {
        List<NinjaDto> listarNinjas = ninjaService.listarNinjas();
        if (!listarNinjas.isEmpty()) {
            return ResponseEntity.ok(listarNinjas);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "Listar ninja por ID", description = "Essa rota retorna um ninja específico com base no ID fornecido.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ninja listado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado")
    })
    public ResponseEntity<NinjaDto> listarNinjasPorId(
            @Parameter(description = "ID do ninja a ser consultado", required = true)
            @PathVariable Long id) {
        NinjaDto ninja = ninjaService.listarNinjasPorId(id);
        if (ninja != null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/alterar/{id}")
    @Operation(summary = "Alterar ninja por ID", description = "Essa rota atualiza os dados de um ninja específico com base no ID fornecido.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ninja alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<?> alterarNinjaPorId(
            @Parameter(description = "ID do ninja a ser atualizado", required = true)
            @PathVariable Long id,
            @org.springframework.web.bind.annotation.RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados atualizados do ninja", required = true,
                    content = @Content(schema = @Schema(implementation = NinjaDto.class)))
            NinjaDto ninja) {
        if (ninjaService.atualizarNinja(id, ninja) != null) {
            return ResponseEntity.ok("Ninja com ID: " + id + " atualizado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com ID: " + id + " não encontrado.");
        }
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar ninja por ID", description = "Essa rota deleta um ninja específico com base no ID fornecido.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ninja deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<String> deletarNinjaPorId(
            @Parameter(description = "ID do ninja a ser removido", required = true)
            @PathVariable Long id) {
        if (ninjaService.listarNinjasPorId(id) != null) {
            ninjaService.deletarNinjaPorId(id);
            return ResponseEntity.ok(
                    "Ninja com ID: " + id + " deletado com sucesso!"
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com ID: " + id + " não encontrado.");
        }
    }
}