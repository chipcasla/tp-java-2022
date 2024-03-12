package entities;

public class Curso {
	
	private int idCurso;
	private String nombre;
	private int idModalidad;
	private String modalidad;
	private int activo;
	
	public Curso() {
	
	}
		
	public Curso(int idCurso, String nombre, int idModalidad, String modalidad) {
		this.idCurso = idCurso;
		this.nombre = nombre;
		this.idModalidad = idModalidad;
		this.modalidad = modalidad;
	}

	public int getIdCurso() {
		return idCurso;
	}
	
	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getIdModalidad() {
		return idModalidad;
	}

	public void setIdModalidad(int idModalidad) {
		this.idModalidad = idModalidad;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}
	
	public int esActivo() {
		return activo;
	}

	public void setActivo(int act) {
		this.activo = act;
	}
		
}
