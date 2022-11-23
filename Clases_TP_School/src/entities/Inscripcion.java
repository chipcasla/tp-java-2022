package entities;

import java.time.LocalDate;

public class Inscripcion {
	
	private Alumno alumno;
	private Curso curso;
	private LocalDate fechaInscripcion;
	
	public Inscripcion(Alumno alumno, Curso curso, LocalDate fechaInscripcion) {
		this.alumno = alumno;
		this.curso = curso;
		this.fechaInscripcion = fechaInscripcion;
	}
	
	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public LocalDate getFechaInscripcion() {
		return fechaInscripcion;
	}
	public void setFechaInscripcion(LocalDate fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	
	

}
