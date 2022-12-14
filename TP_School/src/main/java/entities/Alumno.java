package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Alumno {
	
	final private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	private int idAlumno;
	private int legajo;
	private String nombre;
	private String apellido;
	private String dni;
	private LocalDate fechaNac;
	private String mail;
	private String password;
	private String tel;
	private boolean regular;
	private Inscripcion ultInscripcion;
	
	
	public Alumno() {
	}	
	
	public Alumno(int id,int legajo, String nombre, String apellido, String dni, LocalDate fechaNac, 
			String mail, String tel, Inscripcion inscripcionUlt) {
		this.idAlumno = id;
		this.legajo = legajo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fechaNac = fechaNac;
		this.mail = mail;
		this.tel = tel;
		this.regular = true;
		this.ultInscripcion = inscripcionUlt;
	}
	
	public int getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
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
	
	
	
	
}
