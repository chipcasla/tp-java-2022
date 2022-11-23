package entities;

public class Materia {
	
	private int idMateria;
	private Curso miCurso;
	private String nombre;
		
	public Materia(int idMateria, Curso miCurso, String nombre) {
		this.idMateria = idMateria;
		this.miCurso = miCurso;
		this.nombre = nombre;
	}
	
	public int getIdMateria() {
		return idMateria;
	}
	public void setIdMateria(int idMateria) {
		this.idMateria = idMateria;
	}
	public Curso getMiCurso() {
		return miCurso;
	}
	public void setMiCurso(Curso miCurso) {
		this.miCurso = miCurso;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
