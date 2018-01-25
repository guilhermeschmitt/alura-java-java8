package java8.modeloDePagamento.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class Assinatura {
	private BigDecimal valorMensal;
	private LocalDateTime inicio;
	private Optional<LocalDateTime> fim;
	private Cliente cliente;

	public Assinatura(BigDecimal valorMensal, LocalDateTime inicio, Cliente cliente) {
		this.cliente = cliente;
		this.valorMensal = valorMensal;
		this.inicio = inicio;
		this.fim = Optional.empty();
	}

	public Assinatura(BigDecimal valorMensal, LocalDateTime inicio,LocalDateTime fim, Cliente cliente) {
		this.cliente = cliente;
		this.valorMensal = valorMensal;
		this.inicio = inicio;
		this.fim = Optional.of(fim);
	}

	public BigDecimal getValorMensal() {
		return valorMensal;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public Optional<LocalDateTime> getFim() {
		return fim;
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	
	
}
