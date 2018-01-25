package java8.testeBásicoDeNovosRecursos;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class OrdenaCursoComOptionalECollectors {

	private String nome;
    private int alunos;
    
    public OrdenaCursoComOptionalECollectors(String nome, int alunos) {
        this.nome = nome;
        this.alunos = alunos;
    }
    
    public static void main(String[] args) {
		List<OrdenaCursoComOptionalECollectors> cursos = new ArrayList<OrdenaCursoComOptionalECollectors>();
		cursos.add(new OrdenaCursoComOptionalECollectors("Python", 45));
		cursos.add(new OrdenaCursoComOptionalECollectors("JavaScript", 150));
		cursos.add(new OrdenaCursoComOptionalECollectors("Java 8", 113));
		cursos.add(new OrdenaCursoComOptionalECollectors("C", 55));
		
		cursos.sort(Comparator.comparingInt(c -> c.getAlunos()));
		cursos.stream()
			.filter(c -> c.getAlunos() > 50)
			.map(OrdenaCursoComOptionalECollectors::getNome)
			.forEach(System.out::println);
		
		Optional<OrdenaCursoComOptionalECollectors> findFirst = cursos.stream()
			.filter(c -> c.getAlunos() > 50)
			.findFirst();
		
		System.out.println(findFirst.get().getNome());
		
		OptionalDouble average = cursos.stream()
			.mapToInt(c -> c.getAlunos())
			.average();
		
		System.out.println("A média dos cursos é de : "+ average.getAsDouble() + " alunos por curso.");
		
		
		List<OrdenaCursoComOptionalECollectors> collect = cursos.stream()
			.filter(c -> c.getAlunos() > 50)
			.collect(Collectors.toList());
		
		collect.forEach(c -> System.out.println(c.getNome() + " tem " + c.getAlunos() + " alunos."));
		//cursos.sort(Comparator.comparingInt(OrdenaCursoComOptionalECollectors::getAlunos));
	}

    public String getNome() {
        return nome;
    }

    public int getAlunos() {
        return alunos;
    }
	
}
