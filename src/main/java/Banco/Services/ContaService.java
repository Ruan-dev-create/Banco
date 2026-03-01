package Banco.Services;

import Banco.Modelos.Usuario;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ContaService {

    public void depositar(Usuario usuario, BigDecimal valor){
        if(valor.compareTo(BigDecimal.ZERO) <= 0){
            throw new RuntimeException("Valor Inválido");
        }

        usuario.setSaldo(usuario.getSaldo().add(valor));  // Adicioando valor ao saldo;

}

    public void sacar(Usuario usuario, BigDecimal valor){
        if(usuario.getSaldo().compareTo(valor) < 0){
            throw new RuntimeException("Saldo insuficiente");
        }
        usuario.setSaldo(usuario.getSaldo().subtract(valor));  //Retirando valor do saldo;
    }

}