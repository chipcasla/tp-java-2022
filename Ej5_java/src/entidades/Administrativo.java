package entidades;

public class Administrativo extends Empleado {
	
	private int horasExtras;
	private int horasMes;
	
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
		return getSueldoBase() * ((this.getHorasExtras() * 1.5)+this.getHorasMes()) / this.getHorasMes();
	}
	
	
}
