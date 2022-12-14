package entities;

public class Nota {
	
	private Alumno alumno;
	private Asignatura asignatura;
	private Integer notaFinal;
	private Integer nota1C;
	private Integer nota2C;
	private Integer nota3C;
	
	public Nota() {
	}

	public Nota(Alumno alumno, Asignatura asignatura) {
		this.alumno = alumno;
		this.asignatura = asignatura;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public Integer getNotaFinal() {
		return notaFinal;
	}

	public void setNotaFinal(Integer notaFinal) {
		this.notaFinal = notaFinal;
	}

	public Integer getNota1C() {
		return nota1C;
	}

	public void setNota1C(Integer nota1c) {
		nota1C = nota1c;
	}

	public Integer getNota2C() {
		return nota2C;
	}

	public void setNota2C(Integer nota2c) {
		nota2C = nota2c;
	}

	public Integer getNota3C() {
		return nota3C;
	}

	public void setNota3C(Integer nota3c) {
		nota3C = nota3c;
	}
}
