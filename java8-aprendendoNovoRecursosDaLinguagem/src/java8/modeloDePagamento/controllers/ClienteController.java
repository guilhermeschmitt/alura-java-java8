package java8.modeloDePagamento.controllers;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import java8.modeloDePagamento.models.Cliente;
import java8.modeloDePagamento.models.Pagamento;
import java8.modeloDePagamento.models.Produto;

public class ClienteController {

	PagamentoController pagamentoController = new PagamentoController();
	
	public ClienteController() {
	}

	public void clienteMaisEspecial(List<Pagamento> pagamentos) {
		Function<Pagamento, BigDecimal> somaTotal = 
				p -> pagamentoController.somaDaCompra(p);
		
		Map<Cliente, BigDecimal> colecao = pagamentos.stream()
			.collect(Collectors.groupingBy(Pagamento::getCliente, 
					Collectors.reducing(BigDecimal.ZERO,
							somaTotal,
							BigDecimal::add)));
		
		colecao.entrySet().stream()
			.sorted(Comparator.comparing(Map.Entry::getValue))
			.forEach(c -> System.out.println("Cliente: "+ c.getKey().getNome() + " = "+ c.getValue()));

	}
	
	public void listaProduto(List<Pagamento> pagamentos) {
		Map<Cliente, List<Produto>> colecao = varreColecao(pagamentos).entrySet().stream().collect(Collectors.toMap(
				Map.Entry::getKey, e -> e.getValue().stream().flatMap(List::stream).collect(Collectors.toList())));

		colecao.entrySet().stream()
			.sorted(Comparator.comparing(e -> e.getKey().getNome())).forEach(p -> {
				System.out.print("Cliente: " + p.getKey().getNome() + " = Produtos: ");
				p.getValue().stream().forEach(n -> System.out.print(n.getNome()+ "; "));
				System.out.println();
			}
		);
	}

	private Map<Cliente, List<List<Produto>>> varreColecao(List<Pagamento> pagamentos) {
		return pagamentos.stream().collect(Collectors.groupingBy(Pagamento::getCliente,
				Collectors.mapping(Pagamento::getProdutos, Collectors.toList())));
	}

}
