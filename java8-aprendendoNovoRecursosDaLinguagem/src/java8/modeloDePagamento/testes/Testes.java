package java8.modeloDePagamento.testes;

import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import java8.modeloDePagamento.controllers.AssinaturaController;
import java8.modeloDePagamento.controllers.ClienteController;
import java8.modeloDePagamento.controllers.PagamentoController;
import java8.modeloDePagamento.models.Assinatura;
import java8.modeloDePagamento.models.Cliente;
import java8.modeloDePagamento.models.Pagamento;
import java8.modeloDePagamento.models.Produto;

public class Testes {

	public static void main(String[] args) {
		Cliente joao = new Cliente("João");
		Cliente maria = new Cliente("Maria");
		Cliente gabriel = new Cliente("Gabriel");
		Cliente rafaela = new Cliente("Rafela");

		Produto bach = new Produto("Bach Completo", Paths.get("/music/bach.mp3"), new BigDecimal(100));
		Produto poderosas = new Produto("Poderosas Anita", Paths.get("/music/poderosas.mp3"), new BigDecimal(90));
		Produto bandeira = new Produto("Bandeira Brasil", Paths.get("/images/brasil.jpg"), new BigDecimal(50));
		Produto beauty = new Produto("Beleza Americana", Paths.get("beauty.mov"), new BigDecimal(150));
		Produto vingadores = new Produto("Os Vingadores", Paths.get("/movies/vingadores.mov"), new BigDecimal(200));
		Produto amelie = new Produto("Amelie Poulain", Paths.get("/movies/amelie.mov"), new BigDecimal(100));

		LocalDateTime today = LocalDateTime.now();
		LocalDateTime yesterday = today.minusDays(1);
		LocalDateTime lastMonth = today.minusMonths(1);

		Pagamento pagamento1 = new Pagamento(Arrays.asList(bach, poderosas), today, joao);
		Pagamento pagamento2 = new Pagamento(Arrays.asList(bandeira, beauty, amelie), yesterday, maria);
		Pagamento pagamento3 = new Pagamento(Arrays.asList(vingadores, poderosas), lastMonth, gabriel);
		Pagamento pagamento4 = new Pagamento(Arrays.asList(beauty, vingadores), today, rafaela);
		Pagamento pagamento5 = new Pagamento(Arrays.asList(vingadores), lastMonth, joao);

		List<Pagamento> pagamentos = Arrays.asList(pagamento1, pagamento2, pagamento3, pagamento4, pagamento5);
		
		BigDecimal valorMensal = new BigDecimal("99.90");
		Assinatura assinatura1 = new Assinatura(valorMensal, yesterday.minusMonths(5), gabriel);
		Assinatura assinatura2 = new Assinatura(valorMensal, yesterday.minusMonths(8), today.minusMonths(1), joao);
		Assinatura assinatura3 = new Assinatura(valorMensal, yesterday.minusMonths(5), today.minusMonths(2), rafaela);
		List<Assinatura> assinaturas = Arrays.asList(assinatura1, assinatura2, assinatura3);
		
		PagamentoController pagamentoController = new PagamentoController();
		ClienteController clienteController = new ClienteController();
		AssinaturaController assinaturaController = new AssinaturaController();

		pagamentos.stream().sorted(Comparator.comparing(Pagamento::getData)).forEach(System.out::println);

		System.out.println("--------------- VALOR DO PAGAMENTO DA PRIMEIRA COMPRA ---------------");
		System.out.println(pagamentoController.somaDaCompra(pagamento1));

		System.out.println("\n\n --------------- VALOR TOTAL DAS COMPRAS ---------------");
		System.out.println(pagamentoController.somaTotal(pagamentos));

		System.out.println("\n\n--------------- NÚMERO DE ITENS VENDIDOS POR PRODUTO ---------------");
		pagamentoController.pegaVendasDosProdutos(pagamentos).entrySet().stream().forEach(System.out::println);

		System.out.println("\n\n--------------- ITEM QUE MAIS VENDEU ---------------");
		pagamentoController.pegaMaisVendido(pagamentos).ifPresent(System.out::println);

		System.out.println("\n\n--------------- QUANTO CADA UM VENDEU ---------------");
		pagamentoController.pegaVendaDeCada(pagamentos).entrySet().stream()
			.sorted(Comparator.comparing(Map.Entry::getValue))
			.forEach(System.out::println);
		
		System.out.println("\n\n--------------- PROTUDOS DE CADA CLIENTE ---------------");
		clienteController.listaProduto(pagamentos);
		
		System.out.println("\n\n--------------- PRODUTOS DE CADA CLIENTE ---------------");
		clienteController.clienteMaisEspecial(pagamentos);
		
		System.out.println("\n\n--------------- PRODUTOS DE CADA CLIENTE ---------------");
		pagamentoController.pagamentoPorMes(pagamentos);
		
		System.out.println("\n\n--------------- QUANTIDADE DE MESES EM QUE CADA CLIENTE TEVE UMA ASSINATURA ---------------");
		assinaturas.forEach(a -> System.out.println(assinaturaController.calcMesesPagosAssinatura(a)));
		
		System.out.println("\n\n--------------- SOMA DE TODO O TOTAL PAGO EM ASSINATURAS ---------------");
		assinaturas.forEach(a -> System.out.println(assinaturaController.pagoTotalPorAssinatura(a)));
		
		System.out.println("\n\n--------------- SOMA DO QUE FOI GASTADO EM ASSINATURA ---------------");
		System.out.println(assinaturaController.valorGastoEmAssinaturasNoGeral(assinaturas));
	}

}
