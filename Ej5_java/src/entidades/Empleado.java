package entidades;

public abstract class Empleado {
	private int dni;
	private String apellido;
	private String nombre;
	private String mail;
	private static double sueldoBase = 10000;
	
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getApellido() {
		return apellido;
	}
	public static double getSueldoBase() {
		return sueldoBase;
	}
	public static void setSueldoBase(int sueldoBase) {
		Empleado.sueldoBase = sueldoBase;
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
	
	

}
