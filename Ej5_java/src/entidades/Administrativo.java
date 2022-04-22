package entidades;

public class Administrativo extends Empleado {
	
	private int horasExtras;
	private int horasMes;
	
	public Administrativo(int dni, String apellido, String nombre, String mail, double sueldoBase, int horasExtras,
			int horasMes) {
		super(dni, apellido, nombre, mail, sueldoBase);
		this.horasExtras = horasExtras;
		this.horasMes = horasMes;
	}
	public int getHorasExtras() {
		return horasExtras;
	}
	public void setHorasExtras(int horasExtras) {
		this.horasExtras = horasExtras;
	}
	public int getHorasMes() {
		return horasMes;
	}
	public void setHorasMes(int horasMes) {
		this.horasMes = horasMes;
	}
	
	public double getSueldo() {
		return this.getSueldoBase() * ((this.getHorasExtras() * 1.5)+this.getHorasMes()) / this.getHorasMes();
	}
	
	@Override
	public String toString() {
		return "Administrativo [" + super.toString() + ", sueldo=$" + this.getSueldo()+"]";
	}
	
	
	
}
