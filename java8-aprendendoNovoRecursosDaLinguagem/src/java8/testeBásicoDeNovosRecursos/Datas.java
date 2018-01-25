package java8.testeBásicoDeNovosRecursos;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Datas {

	public static void main(String[] args) {
		LocalDate dataAtual = LocalDate.now();
		
		System.out.println(dataAtual);
		
		LocalDate dataFutura = LocalDate.of(2099, Month.JANUARY, 25);
		
		System.out.println(dataFutura);
		
		Period periodo = Period.between(dataAtual, dataFutura);
		
		System.out.println(periodo);
		
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println(dataAtual.format(pattern));
	}
	
}
