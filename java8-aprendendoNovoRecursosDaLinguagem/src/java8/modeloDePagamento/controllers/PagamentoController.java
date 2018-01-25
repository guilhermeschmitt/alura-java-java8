package java8.modeloDePagamento.controllers;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import java8.modeloDePagamento.models.Pagamento;
import java8.modeloDePagamento.models.Produto;

public class PagamentoController {

	public PagamentoController() {
	}
	
	public void pagamentoPorMes(List<Pagamento> pagamentos) {
		Map<YearMonth, BigDecimal> pagamentosDoMes = pagamentos.stream()
			.collect(Collectors.groupingBy(p -> YearMonth.from(p.getData()),
					Collectors.reducing(BigDecimal.ZERO,
							p -> p.getProdutos().stream()
								.map(Produto::getPreco)
								.reduce(BigDecimal.ZERO,
										BigDecimal::add),
							BigDecimal::add)));
		
		pagamentosDoMes.entrySet().stream()
			.forEach(System.out::println);
	}
	
	public Map<Object, BigDecimal> pegaVendaDeCada(List<Pagamento> pagamentos) {
		return pagamentos.stream()
			.flatMap(p -> p.getProdutos().stream())
			.collect(Collectors.groupingBy(p -> p.getNome(),
				Collectors.reducing(BigDecimal.ZERO, Produto::getPreco, BigDecimal::add)));
	}

	public Optional<Entry<Object, Long>> pegaMaisVendido(List<Pagamento> pagamentos) {

		return pegaVendasDosProdutos(pagamentos).entrySet().stream().max(Comparator.comparing(Map.Entry::getValue));
	}

	public Map<Object, Long> pegaVendasDosProdutos(List<Pagamento> pagamentos) {
		/*
		 * Precisamos gerar um Map de Product para Long. Esse Long indica quantas vezes
		 * o produto foi vendido. Usaremos o groupingBy, agrupando todos esses produtos
		 * pelo próprio produto, mapeando-o pela sua contagem
		 */
		return pagamentos.stream().flatMap(p -> p.getProdutos().stream())
				.collect(Collectors.groupingBy(p -> p.getNome(), Collectors.counting()));
	}

	public BigDecimal somaTotal(List<Pagamento> pagamentos) {
		return pagamentos.stream().map(p -> somaDaCompra(p)).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal somaDaCompra(Pagamento pagamento) {
		return pagamento.getProdutos().stream().map(Produto::getPreco).reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
}
