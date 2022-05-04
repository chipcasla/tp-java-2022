package entidades;

public class Auto extends Vehiculo {
	
	private int tamanioBaul;
	
	//CONSTRUCTOR (ESTE REEMPLAZARIA AL CONSTRUCTOR DEFAULT)
	public Auto(String mar, int mod, String col, String pat, int tamBaul) {
		setMarca(mar);
		setModelo(mod);
		setColor(col);
		setPatente(pat);
		setTamanioBaul(tamBaul);
	}
	
	/*	 
	//CONSTRUCTOR GENERADO CON LA OPCION SOURCE
	public Auto(String marca, int modelo) {
		super();
		this.marca = marca;
		this.modelo = modelo;
		this.color = "blanco";  // este valor se lo asigne yo al atributo color
		this.patente = ""; // este valor se lo asigne yo al atributo patente
	}
	*/
		
	//EL METODO TO STRING Q LO GENERE DESDE SOURCE	
	@Override
	public String toString() {
		return "Auto "+ super.toString() +", tamanioBaul=" + tamanioBaul + "]";
	}

	public int getTamanioBaul() {
		return tamanioBaul;
	}


	public void setTamanioBaul(int tamanioBaul) {
		this.tamanioBaul = tamanioBaul;
	}

	public boolean compare(Auto otro) {
		
		return this.getPatente() == otro.getPatente();
		
	}
	
	
}




