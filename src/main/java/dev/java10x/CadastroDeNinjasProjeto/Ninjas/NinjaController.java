package dev.java10x.CadastroDeNinjasProjeto.Ninjas;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class NinjaController {

    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "Essa é a minha primeira mensagem nessa rota!";
    }

    @PostMapping("/criar")
    public String criarNinja() {
        return "Criar ninjas";
    }

    @GetMapping("/listar")
    public String mostrarTodosNinjas() {
        return "Mostrar todos os ninjas";
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