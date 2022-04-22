package entidades;

public class Vendedor extends Empleado{
	
	private int porcenComision;
	private int totalVentas;
	
	public Vendedor(int dni, String apellido, String nombre, String mail, double sueldoBase, int porcenComision,
			int totalVentas) {
		super(dni, apellido, nombre, mail, sueldoBase);
		this.porcenComision = porcenComision;
		this.totalVentas = totalVentas;
	}
	
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
		return this.getSueldoBase() + (this.getPorcenComision()*this.getTotalVentas() / 100.0);
	}

	@Override
	public String toString() {
		return "Vendedor [" + super.toString() + ", sueldo=$" + this.getSueldo()+"]";
	}
	
}
