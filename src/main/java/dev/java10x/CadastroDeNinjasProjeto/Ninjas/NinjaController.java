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
    public String criarNinja() {
        return "Criar ninjas";
    }

    @GetMapping("/listar")
    public List<NinjaModel> listarNinjas() {
        return ninjaService.listarNinjas();
    }

    @GetMapping("/listarId")
    public String mostrarTodosNinjasPorId() {
        return "Mostrar todos ninjas por ID";
    }

    @PutMapping("/alterarId")
    public String alterarNinjaPorId() {
        return "Alterar Ninja Por ID";
    }

    @DeleteMapping("/deletar")
    public String deletarNinjaPorId() {
        return "Deletar Ninja Por ID";
    }

}