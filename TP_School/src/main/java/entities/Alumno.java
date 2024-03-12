package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Alumno extends Persona implements Comparable<Alumno>{
	
	final private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	private int legajo;
	private String dni;
	private String tel;	
	private LocalDate fechaNac;
	private boolean regular;
	private Inscripcion ultInscripcion;
	
	
	public Alumno() {
	}	
	
	public Alumno(int id,int legajo, String nombre, String apellido, String dni, LocalDate fechaNac, 
			String mail, String tel, boolean reg) {
		super(id, nombre, apellido, mail);
		this.legajo = legajo;
		this.dni = dni;
		this.tel = tel;
		this.fechaNac = fechaNac;
		this.regular = reg;
		
	}
	
	public int getIdAlumno() {
		return id;
	}

	public void setIdAlumno(int idAlumno) {
		this.id = idAlumno;
	}
	
	public int getLegajo() {
		return legajo;
	}
	
	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getDni() {
		return dni;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
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
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public boolean isRegular() {
		return regular;
	}
	
	public void setRegular(boolean regular) {
		this.regular = regular;
	}

	public Inscripcion getUltInscripcion() {
		return ultInscripcion;
	}

	public void setUltInscripcion(Inscripcion ultInscripcion) {
		this.ultInscripcion = ultInscripcion;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Alumno [id=" + id + ", nombre="+ nombre + ", apellido=" + apellido + ", mail=" + mail 
				+ ", legajo=" + legajo + ", dni=" + dni + ", tel=" + tel + ", fechaNac="
				+ fechaNac + ", regular=" + regular + "]";
	}

	@Override
	public int compareTo(Alumno o) {
		String compared = o.getApellido();
		return this.getApellido().compareTo(compared);
	}

	
	
	
	
	
}
