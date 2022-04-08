package entidades;

public class Vendedor extends Empleado {
	
	private int porcenComision;
	private int totalVentas;
	public int getPorcenComision() {
		return porcenComision;
	}
	public void setPorcenComision(int porcenComision) {
		this.porcenComision = porcenComision;
	}
	public int getTotalVentas() {
		return totalVentas;
	}
	public void setTotalVentas(int totalVentas) {
		this.totalVentas = totalVentas;
	}
	
	public double getSueldo() {
		return getSueldoBase() + (this.getPorcenComision()*this.getTotalVentas() / 100);
	}
	
}
