package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Profesor extends Persona {
	
	final private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	private String dni;
	private String cuil;
	private String tel;	
	private LocalDate fechaNac;
	
	public Profesor() {
	}
	
	public Profesor(int id, String nombre, String apellido, String mail, String dni, String cuil, String tel, LocalDate fechaNac) {
		super(id, nombre, apellido, mail);
		this.dni = dni;
		this.cuil = cuil;
		this.tel = tel;
		this.fechaNac = fechaNac;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}
	
	public String getFechaNacFormat() {
		return fechaNac.format(formato);
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}
	
}
