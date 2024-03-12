package entities;

import java.util.Objects;

public class Materia {
	
	private int idMateria;
	private String nombre;
		
	public Materia() {	}
	
	public Materia(int idMateria, String nombre) {
		this.idMateria = idMateria;
		this.nombre = nombre;
	}
	
	public int getIdMateria() {
		return idMateria;
	}
	public void setIdMateria(int idMateria) {
		this.idMateria = idMateria;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Materia other = (Materia) obj;
		return idMateria == other.idMateria;
	}
		
    @Override
	public int hashCode() {
		return Objects.hash(idMateria);
	}

}
