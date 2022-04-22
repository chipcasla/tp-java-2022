package entidades;

public abstract class Empleado {
	private int dni;
	private String apellido;
	private String nombre;
	private String mail;
	private double sueldoBase;
	
	public Empleado(int dni, String apellido, String nombre, String mail, double sueldoBase) {
		this.dni = dni;
		this.apellido = apellido;
		this.nombre = nombre;
		this.mail = mail;
		this.sueldoBase = sueldoBase;
	}
	
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getApellido() {
		return apellido;
	}
	public double getSueldoBase() {
		return sueldoBase;
	}
	public void setSueldoBase(int sueldoBase) {
		this.sueldoBase = sueldoBase;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		return "dni=" + dni + ", apellido=" + apellido + ", nombre=" + nombre;
	}
	
	
		

}
