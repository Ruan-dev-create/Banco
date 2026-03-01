package Banco.Controller;

import Banco.Modelos.Usuario;
import Banco.Services.BancoService;
import Banco.dto.BancoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BancoController {

    @Autowired
    private BancoService bancoService;

    @GetMapping("/usuarios")
    public List<BancoDTO> usuarios(){
        return bancoService.obterTodosOsUsuarios();
    }
}
