import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class OrdenaPalavra3UsandoMethodReference {

	public static void main(String[] args) {
		List<String> palavras = Arrays.asList("banana", "maçã", "laranja", "melão");
		
		//palavras.sort(Comparator.comparing(s -> s.length()));
		//Usando o Method Reference \/
		palavras.sort(Comparator.comparing(String::length));
		//palavras.sort(String.CASE_INSENSITIVE_ORDER);
		
		palavras.forEach(System.out::println);
	}
	
}
