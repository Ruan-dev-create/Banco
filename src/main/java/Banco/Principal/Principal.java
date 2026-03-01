package Banco.Principal;

import Banco.Modelos.Usuario;
import Banco.Repository.IRepositorio;
import Banco.Services.ContaService;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Principal{
    private IRepositorio repositorio ;

    private Usuario usuarioLogado; //Salvar informações do usuario

    private ContaService service; // Metodos do saldo.


    public Principal(IRepositorio repositorio, ContaService service) {
        this.repositorio = repositorio;
        this.service = service;
    }

    private void pause() {
        System.out.println("\nPressione Enter para continuar...");
        l.nextLine();
    }

    public void LimpaTela(){
        int parar = 0;
        for (parar = 0; parar < 20; parar++){
            System.out.println("");
        }
    }


    //-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-===-=-=-=-=-=-=-==-=-==-=-=-=-=-=-
    private Scanner l = new Scanner(System.in);

    public void exibirMenu() {
// ===================== MENU PRINCIPAL =====================

        boolean rodandoInicio = true;

        LimpaTela();

        mostraMenuInicial();

        String op;
        while (rodandoInicio) {

            System.out.print("\nDigite sua opção: ");
            op = l.nextLine();

            switch (op) {
                case "1": // Login
                    try {
                        FazendoLogin();
                        rodandoInicio = false;
                    }
                    catch (RuntimeException e){
                        System.out.println("Erro: " + e.getMessage());
                        System.out.println("Voltando ao menu principal.");
                        pause();
                    }

                    break;
                case "2":  // Cadastro
                    FazendoCadastro();
                    rodandoInicio = false;
                    break;

                case "3":
                    System.out.println("<<< modo administrador ativado >>> ");
                    System.out.println("Digite a senha de administrador: ");
                    String senhaADM = l.nextLine();
                    if (senhaADM.equals("RuanAdmin")) {
                        ListarUsuarios();

                        System.out.println("Deseja desativar algum? [s/n]: ");
                        String op3 = l.nextLine();

                        if(op3.equals("s")){
                            System.out.println("Escolha o usuario pelo Id: ");
                            Long Id = l.nextLong();
                            boolean ativo = false;
                            repositorio.deleteById(Id);

                            break;
                        }

                        System.out.println("deseja imprimir o menu novamente? [s/n]: ");
                        String op2 = l.nextLine();
                        if (op2.equals("s")) {
                            mostraMenuInicial();
                            break;
                        }else if(op2.equals("n")){
                            System.out.println("ok.");
                            break;
                        }
                    } else {
                        System.out.println("senha incorreta! ");
                        break;
                    }

                case "0":
                    System.out.println("Saindo...");
                    rodandoInicio = false;
                    break;

                default:
                    System.out.println("Opção inválida.");
                    pause();
            }

        }

        // ===================== MENU CONTA =====================
        boolean rodandoConta = true;
        mostraMenuConta();

        while(rodandoConta) {
            System.out.println("\nDigite sua opção: ");
            String opConta = l.nextLine();

            switch (opConta) {
                case "1":
                    try {
                        System.out.println("Digite o valor de deposito: ");
                        BigDecimal valorDepositado = new BigDecimal(l.nextLine());
                        service.depositar(usuarioLogado, valorDepositado);
                        repositorio.save(usuarioLogado);
                        System.out.println("Depósito realizado!");
                        pause();

                    } catch (NumberFormatException e) {
                        System.out.println("Valor inválido!");
                        pause();
                    }
                    System.out.println("Deseja imprimir o menu novamente? [s/n]: ");
                    String op2 = l.nextLine();
                    if (op2.equals("s")) {
                        mostraMenuConta();
                        break;
                    }
                    break;

                case "2":
                    try {
                        System.out.println("Digite o valor de Transfência: ");
                        BigDecimal valorTransferido = new BigDecimal(l.nextLine());
                        service.sacar(usuarioLogado, valorTransferido);
                        repositorio.save(usuarioLogado);
                        System.out.println("transferência realizada!");
                        pause();
                    } catch (NumberFormatException e) {
                        System.out.println("Valor inválido!");
                        pause();
                    } catch (RuntimeException e){
                        System.out.println("Saldo insuficiente para transferência.");
                        pause();
                    }
                    System.out.println("Deseja imprimir o menu novamente? [s/n]: ");
                    op2 = l.nextLine();
                    if (op2.equals("s")) {
                        mostraMenuConta();
                        break;
                    }

                    break;
                case "0":
                    System.out.println("saindo...");
                    rodandoConta = false;
                    pause();
                    break;

                default:
                    System.out.println("Opção inválida!");
                    pause();
                    break;

            }
        }

    }

    // ===================== LOGIN =====================
    public void FazendoLogin(){
        System.out.println("Informe seu CPF: ");
        String cpfDigitado = l.nextLine();

        System.out.println("Informe seu email: ");
        String emailDigitado = l.nextLine();

        System.out.println("Digite sua senha: ");
        String senhaDigitada = l.nextLine();

        List<Usuario> usuarios = repositorio.findAll();

        Optional<Usuario> usuario = usuarios.stream()
                .filter(u -> u.getCpf().equals(cpfDigitado))
                .filter(u -> u.getEmail().equals(emailDigitado))
                .filter(u -> senhaDigitada.trim().equals(u.getSenha()))
                .findFirst();

        if(usuario.isPresent()){
            usuarioLogado = usuario.get();
            System.out.printf("Bem-Vindo de volta %s \n", usuario.get().getNome());
        }else{
            throw new RuntimeException("Dados incoretos");
        }

    }

    //================ CADASTRO =====================
    public void FazendoCadastro(){
        Usuario usuarioMetodos = new Usuario();

        System.out.println("\nDigite seu nome: ");
        String nome = l.nextLine();

        System.out.println("\nDigite sua idade: ");
        int idade = l.nextInt();
        l.nextLine();

        System.out.println("\nInforme seu CPF: ");
        String cpf = l.nextLine();
        cpf = usuarioMetodos.verificarCPF(cpf);

        System.out.println("\nInforme seu Email: ");
        String email = l.nextLine();
        email = usuarioMetodos.verificarEmail(email);

        System.out.println("\nDigite a senha da conta: \n");
        String senha = l.nextLine();
        LocalDate data = LocalDate.now();
        LocalTime tempo = LocalTime.now();

        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");

        String dataFormatada = data.format(formatoData);
        String tempoFormatado = tempo.format(formatoHora);

        BigDecimal saldo = new BigDecimal("0");

        var ativo = true;
        usuarioLogado = new Usuario(
                dataFormatada,
                tempoFormatado,
                nome,
                idade,
                cpf,
                email,
                senha,
                saldo,
                ativo
        );


        repositorio.save(usuarioLogado);

        System.out.println("Salvo com sucesso! ");

    }

    //======================= LISTAGEM ==========================

    public void ListarUsuarios(){
        List<Usuario> usuarios = repositorio.findAll();
        System.out.println(usuarios);
    }

    //======================= MENUS =============================

    public void mostraMenuInicial(){
        System.out.println("""
                          Inicio:  
                
                |    🏦 <<<< MENU >>>> 🏦    |
                |                            |
                | [1] - Fazer Login          |
                | [2] - Cadastro             |
                | [3] - Listar Usuarios      |
                |                            |
                | [0] - sair                 |
                |                            |
                |============================|
                
                
                """);
    }

    public void mostraMenuConta(){

        if(usuarioLogado == null){
            System.out.println("Nenhum usuario Logado/Cadastrado...");
            return;
        }

        LimpaTela();

        System.out.printf("""           
                | ==== 🏦 MENU DA CONTA 🏦 ==== |
                | Bem-Vindo %s
                | Saldo Atual: R$%s
                |
                |-------------------------------|
                |                               |
                | [1] - Deposito                |
                | [2] - Transferir              |
                |                               |
                | [0] - sair                    |
                |===============================|
                """,    usuarioLogado.getNome(),
                usuarioLogado.getSaldo()

        );

    }


}