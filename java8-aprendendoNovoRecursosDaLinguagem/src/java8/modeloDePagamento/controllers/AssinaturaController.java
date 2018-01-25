package java8.modeloDePagamento.controllers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import java8.modeloDePagamento.models.Assinatura;

public class AssinaturaController {

	public AssinaturaController() {

	}

	public long calcMesesPagosAssinatura(Assinatura assinatura) {
		return ChronoUnit.MONTHS.between(assinatura.getInicio(), assinatura.getFim().orElse(LocalDateTime.now()));
	}

	public BigDecimal pagoTotalPorAssinatura(Assinatura assinatura) {
		return assinatura.getValorMensal()
				.multiply(new BigDecimal(ChronoUnit.MONTHS.between(assinatura.getInicio(), assinatura.getFim().orElse(LocalDateTime.now()))));
	}
	
	public BigDecimal valorGastoEmAssinaturasNoGeral(List<Assinatura> assinaturas) {
		return assinaturas.stream()
					.collect(Collectors.reducing(BigDecimal.ZERO, a -> pagoTotalPorAssinatura(a), BigDecimal::add));
	}

}
