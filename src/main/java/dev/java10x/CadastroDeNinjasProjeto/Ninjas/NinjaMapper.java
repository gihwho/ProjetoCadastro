package dev.java10x.CadastroDeNinjasProjeto.Ninjas;

import org.springframework.stereotype.Component;

@Component
public class NinjaMapper {

    public NinjaModel map(NinjaDto ninjaDto) {
        NinjaModel ninja = new NinjaModel();
        ninja.setId(ninjaDto.getId());
        ninja.setNome(ninjaDto.getNome());
        ninja.setEmail(ninjaDto.getEmail());
        ninja.setImg_url(ninjaDto.getImg_url());
        ninja.setRank(ninjaDto.getRank());
        ninja.setIdade(ninjaDto.getIdade());
        ninja.setMissoes(ninjaDto.getMissoes());

        return ninja;
    }

    public NinjaDto map(NinjaModel ninjaModel) {
        NinjaDto ninjaDto = new NinjaDto();
        ninjaDto.setId(ninjaModel.getId());
        ninjaDto.setNome(ninjaModel.getNome());
        ninjaDto.setEmail(ninjaModel.getEmail());
        ninjaDto.setImg_url(ninjaModel.getImg_url());
        ninjaDto.setRank(ninjaModel.getRank());
        ninjaDto.setIdade(ninjaModel.getIdade());
        ninjaDto.setMissoes(ninjaModel.getMissoes());

        return ninjaDto;
    }
}
