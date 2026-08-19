package dev.java10x.CadastroDeNinjasProjeto.Ninjas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    @Autowired
    private NinjaService ninjaService;

    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "Essa é a minha primeira mensagem nessa rota!";
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDto ninjaDto) {
        NinjaDto ninja = ninjaService.criarNinja(ninjaDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso!\n" +
                        "ID: " + ninja.getId() + ",\n" +
                        "Nome: " + ninja.getNome());
    }

    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDto>> listarNinjas() {
        List<NinjaDto> listarNinjas = ninjaService.listarNinjas();
        if (!listarNinjas.isEmpty()) {
            return ResponseEntity.ok(listarNinjas);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<NinjaDto> listarNinjasPorId(@PathVariable Long id) {
        NinjaDto ninja = ninjaService.listarNinjasPorId(id);
        if (ninjaService.listarNinjasPorId(id) != null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/alterar/{id}")
    public ResponseEntity<?> alterarNinjaPorId(@PathVariable Long id, @RequestBody NinjaDto ninja) {
        if (ninjaService.atualizarNinja(id, ninja) != null) {
            return ResponseEntity.ok("Ninja com ID: " + id + " atualizado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com ID: " + id + " não encontrado.");
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinjaPorId(@PathVariable Long id) {
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