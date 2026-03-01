package Banco;

import Banco.Principal.Principal;
import Banco.Repository.IRepositorio;
import Banco.Services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ProjetoBancoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoBancoSpringApplication.class, args);
	}


}
