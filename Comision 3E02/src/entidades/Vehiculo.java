package entidades;


//UNA CLASE ES ABSTRACTA CUANDO SE PUEDE INSTANCIAR, es decir no se puede hacer new de vhiculo (no existe un objeto de vehiculo)
public abstract class Vehiculo {   
	
	private String marca;
	private int modelo;
	private String color;
	private String patente;
	
	
	public String getColor() {
		return color;
	}
	public String getMarca() {
		return marca;
	}
	public int getModelo() {
		return modelo;
	}
	public String getPatente() {
		return patente;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public void setModelo(int modelo) {
		this.modelo = modelo;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	
	@Override
	public String toString() {
		return "[marca=" + marca + ", modelo=" + modelo + ", color=" + color + ", patente=" + patente;
	}
	
	
	
	
}
