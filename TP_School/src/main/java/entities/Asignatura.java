package entities;

public class Asignatura {
	
	private Curso curso;
	private Materia materia;
	
	Asignatura() {	
	}

	public Asignatura(Curso curso, Materia m) {
		this.curso = curso;
		this.materia = m;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia m) {
		this.materia = m;
	}
}
