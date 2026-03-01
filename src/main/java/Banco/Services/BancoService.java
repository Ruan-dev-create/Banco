package Banco.Services;

import Banco.Repository.IRepositorio;
import Banco.dto.BancoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BancoService {

    @Autowired
    private IRepositorio repositorio;

    public List<BancoDTO> obterTodosOsUsuarios(){
        return repositorio.findAll().stream()
                .map(s -> new BancoDTO(s.getId(),
                        s.getNome(), s.getIdade(),
                        s.getCpf(), s.getEmail(),
                        s.getTempoCriacao(), s.getDataCriacao(),
                        s.getSaldo(), s.getAtivo()))
                .collect(Collectors.toList());

    }

}
