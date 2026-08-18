package dev.java10x.CadastroDeNinjasProjeto.Ninjas;

import org.springframework.beans.factory.annotation.Autowired;
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
    public NinjaDto criarNinja(@RequestBody NinjaDto ninjaDto) {
        return ninjaService.criarNinja(ninjaDto);
    }

    @GetMapping("/listar")
    public List<NinjaDto> listarNinjas() {
        return ninjaService.listarNinjas();
    }

    @GetMapping("/listar/{id}")
    public NinjaDto listarNinjasPorId(@PathVariable Long id) {
        return ninjaService.listarNinjasPorId(id);
    }

    @PutMapping("/alterar/{id}")
    public NinjaDto alterarNinjaPorId(@PathVariable Long id, @RequestBody NinjaDto ninja) {
        return ninjaService.atualizarNinja(id, ninja);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarNinjaPorId(@PathVariable Long id) {
        ninjaService.deletarNinjaPorId(id);
    }

}