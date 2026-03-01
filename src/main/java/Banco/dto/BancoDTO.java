package Banco.dto;

import java.math.BigDecimal;

public record BancoDTO(int Id, String nome, int idade, String cpf, String email, String tempoCriacao,
                       String dataCriacao, BigDecimal saldo, boolean ativo) {
}
