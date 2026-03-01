package Banco;

import Banco.Principal.Principal;
import Banco.Repository.IRepositorio;
import Banco.Services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@SpringBootApplication
//public class ProjetoBancoSpringApplicationSemWeb implements CommandLineRunner {
//
//	@Autowired
//	private IRepositorio repositorio;
//
//	@Autowired
//	private ContaService contaService;
//
//
//	public static void main(String[] args) {
//		SpringApplication.run(ProjetoBancoSpringApplicationSemWeb.class, args);
//	}
//
//
//
//	@Override
//	public void run(String... args) throws Exception {
//		Principal principal = new Principal(repositorio, contaService
//		);
//		principal.exibirMenu();
//
//
//
//	}
//
//
//}
