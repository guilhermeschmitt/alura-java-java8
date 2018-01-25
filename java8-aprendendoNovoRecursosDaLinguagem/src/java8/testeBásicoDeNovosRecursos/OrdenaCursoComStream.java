package java8.testeBásicoDeNovosRecursos;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class OrdenaCursoComStream {

	private String nome;
    private int alunos;

    public OrdenaCursoComStream(String nome, int alunos) {
        this.nome = nome;
        this.alunos = alunos;
    }
    
    public static void main(String[] args) {
		List<OrdenaCursoComStream> cursos = new ArrayList<OrdenaCursoComStream>();
		cursos.add(new OrdenaCursoComStream("Python", 45));
		cursos.add(new OrdenaCursoComStream("JavaScript", 150));
		cursos.add(new OrdenaCursoComStream("Java 8", 113));
		cursos.add(new OrdenaCursoComStream("C", 55));
		
		cursos.sort(Comparator.comparingInt(c -> c.getAlunos()));
		cursos.stream()
			.filter(c -> c.getAlunos() > 50)
			.map(OrdenaCursoComStream::getNome)
			.forEach(System.out::println);
		//cursos.sort(Comparator.comparingInt(OrdenaCursoComStream::getAlunos));
	}

    public String getNome() {
        return nome;
    }

    public int getAlunos() {
        return alunos;
    }
	
}
