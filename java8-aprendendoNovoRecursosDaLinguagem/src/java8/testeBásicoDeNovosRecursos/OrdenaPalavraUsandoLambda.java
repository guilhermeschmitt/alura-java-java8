package java8.testeBásicoDeNovosRecursos;
import java.util.Arrays;
import java.util.List;

public class OrdenaPalavraUsandoLambda {

	public static void main(String[] args) {
		List<String> palavras = Arrays.asList("banana", "maçã", "laranja", "melão");

		/*
		 * palavras.sort((s1, s2) -> { if (s1.length() < s2.length()) return -1; if
		 * (s1.length() > s2.length()) return 1; return 0; });
		 */

		palavras.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));

		palavras.forEach(s -> System.out.println(s));

		/*
		 * new Thread(new Runnable() {
		 * 
		 * @Override public void run() { System.out.println("Executando um Runnable"); }
		 * 
		 * }).start();
		 */
		
		new Thread(() -> System.out.println("Executando um Runnable")).start();
	}

}
