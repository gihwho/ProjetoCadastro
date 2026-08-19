package dev.java10x.CadastroDeNinjasProjeto.Ninjas;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {

    private NinjaMapper ninjaMapper;
    private NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    public List<NinjaDto> listarNinjas() {
        List<NinjaModel> ninjas = ninjaRepository.findAll();
        return ninjas.stream()
                .map(ninjaMapper::map)
                .toList();
    }

    public NinjaDto listarNinjasPorId(Long id) {
        Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id);
        return ninjaPorId.map(ninjaMapper::map).orElse(null);
    }

    public NinjaDto criarNinja(NinjaDto ninjaDto) {
        NinjaModel ninjaModel = ninjaMapper.map(ninjaDto);
        ninjaModel = ninjaRepository.save(ninjaModel);
        return ninjaMapper.map(ninjaModel);
    }

    public void deletarNinjaPorId(Long id) {
        ninjaRepository.deleteById(id);
    }

    public NinjaDto atualizarNinja(Long id, NinjaDto ninjaDto) {
        return ninjaRepository.findById(id)
                .map(ninja -> {
                    if (ninjaDto.getNome() != null)
                        ninja.setNome(ninjaDto.getNome());
                    if (ninjaDto.getEmail() != null)
                        ninja.setEmail(ninjaDto.getEmail());
                    if (ninjaDto.getIdade() != null)
                        ninja.setIdade(ninjaDto.getIdade());
                    if (ninjaDto.getRank() != null)
                        ninja.setRank(ninjaDto.getRank());
                    if (ninjaDto.getImg_url() != null)
                        ninja.setImg_url(ninjaDto.getImg_url());

                    NinjaModel ninjaSalvo = ninjaRepository.save(ninja);
                    return ninjaMapper.map(ninjaSalvo);
                })
                .orElse(null);
    }
}
