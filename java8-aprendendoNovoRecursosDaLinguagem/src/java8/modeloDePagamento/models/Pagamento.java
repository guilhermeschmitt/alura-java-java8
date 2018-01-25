package java8.modeloDePagamento.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

public class Pagamento {

	private List<Produto> produtos;
	private LocalDateTime data;
	private Cliente cliente;

	public Pagamento(List<Produto> list, LocalDateTime data, Cliente cliente) {
		super();
		this.produtos = Collections.unmodifiableList(list);
		this.data = data;
		this.cliente = cliente;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public LocalDateTime getData() {
		return data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	@Override
	public String toString() { return "[Pagamento: " + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " " + cliente + " " + produtos + "]"; }


}
