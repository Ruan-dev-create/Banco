package Banco.Modelos;

import Banco.Repository.IRepositorio;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

@Entity
@Table(name = "usuario")
public class Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    protected String tempoCriacao;
    protected String dataCriacao;

    protected String nome;

    protected int idade;

    @Column(unique = true)
    protected String cpf;

    @Column(unique = true)
    protected String email;

    protected String senha;

    @Column(nullable = false)
    private BigDecimal saldo = BigDecimal.ZERO;

    private boolean ativo;

    public Usuario(){}
    public Usuario(String dataCriacao, String tempoCriacao, String nome, int idade
            , String cpf, String email, String senha, BigDecimal saldo,  boolean ativo) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.tempoCriacao = tempoCriacao;
        this.dataCriacao = dataCriacao;
        this.saldo = saldo;
        this.ativo = ativo;
    }

    public String verificarCPF(String cpf) {
        Scanner l = new Scanner(System.in);

        Pattern pattern = Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$");

        while (!pattern.matcher(cpf).matches()) {
            System.out.println("CPF inválido!");
            System.out.print("Digite seu CPF (000.000.000-00): ");
            cpf = l.nextLine();

        }
        return cpf;
    }

    public String verificarEmail(String email) {
        Scanner l = new Scanner(System.in);

        Pattern pattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

        while (!pattern.matcher(email).matches()) {
            System.out.println("Email inválido!");
            System.out.print("Digite seu email novamente: ");
            email = l.nextLine();
        }
        return email;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTempoCriacao() {
        return tempoCriacao;
    }

    public void setTempoCriacao(String tempoCriacao) {
        this.tempoCriacao = tempoCriacao;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return " \n Usuario {" +
                " | id = " + id + '\n' +
                " | nome = " + nome + '\n' +
                " | idade = " + idade + '\n' +
                " | cpf = " + cpf + '\n' +
                " | email = " + email + '\n' +
                " | senha = " + senha + '\n' +
                " | Criada as = " + tempoCriacao + " horas " + '\n' +
                " | No dia = " + dataCriacao + '\n'+
                " | Ativada = " + ativo + '\n' +
                " }";
    }
}