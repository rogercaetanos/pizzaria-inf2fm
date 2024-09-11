package com.itb.inf2fm.pizzaria;


import com.itb.inf2fm.pizzaria.exceptions.ErrorMessage;
import com.itb.inf2fm.pizzaria.model.Produto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizzariaApplication {

	public static void main(String[] args) {

		SpringApplication.run(PizzariaApplication.class, args);

		System.out.println("Hello World - Meu primeiro projeto Spring Boot");

		Produto p1 = new Produto();
		// p1.precoVenda = -25;   Acesso ao atributo "precoVenda" não permitido, o modificador de acesso dos atributos agora é private!

		p1.setPrecoVenda(-25);
		p1.validarProduto();
		System.out.println("Valor de venda: " + p1.getPrecoVenda() + " Informação: " + p1.getMensagemErro());


		//  ErrorMessage errorMessage = new ErrorMessage(); // Não tem mais o construtor padrão

	}

}
