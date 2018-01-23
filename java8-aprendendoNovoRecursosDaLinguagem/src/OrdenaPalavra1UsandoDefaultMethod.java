import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class OrdenaPalavra1UsandoDefaultMethod {

	public static void main(String[] args) {
		List<String> palavras = Arrays.asList("banana", "maçã", "laranja", "melão");
		Consumer consumidor = new ImprimeNaLinha();
		Comparator<String> comparador = new ComparadorPorTamanho();
		palavras.sort(comparador);
		
		palavras.forEach(consumidor);
	}
}

class ComparadorPorTamanho implements Comparator<String>{

	@Override
	public int compare(String s1, String s2) {
		if(s1.length() < s2.length()) return -1;
		if(s1.length() > s2.length()) return 1;
		return 0;
	}
	
}

class ImprimeNaLinha implements Consumer<String>{

	@Override
	public void accept(String string) {
		System.out.println(string);
	}
	
}
