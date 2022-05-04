/*
import Entidades.Auto;
import Entidades.Vehiculo;

public class Test {

	public static void main(String[] args) {

		
		//PA USAR EL CONSTRUCTOR QUE CREE EN LA CLASE AUTO:
		Auto tutu1 = new Auto("Audi", 2022, "Verde", "AC 001 BD", 2000);
		
		
		Auto tutu = new Auto("", 0, "", "", 0);
		
		tutu.setMarca("Audi");
		tutu.setColor("Rojo");
		tutu.setModelo(2020);
		tutu.setPatente("AC 001 BD");
		
		
		
		Auto tutu2 = new Auto("", 0, "", "", 0);
		
		tutu2.setMarca("Audi");
		tutu2.setColor("Rojo");
		tutu2.setModelo(2020);
		tutu2.setPatente("AC 001 BD");
		
		
		
//		System.out.println(tutu.getMarca());
//		System.out.println(tutu.getPatente());
//		System.out.println(tutu.getColor());
//		System.out.println(tutu.getModelo());
//		
//		
//		System.out.println(tutu);
//		
//		System.out.println(tutu.mostrarAuto());
		
		
		
		
	
//		tutu2 = tutu;
//		tutu2.setColor("Verde");
		if (tutu.compare(tutu2)) {
			System.out.println("SI");
		}
		else {
			System.out.println("NO");
		}
		
		System.out.println(tutu);
		System.out.println(tutu2);
		System.out.println(tutu2.getColor());
		
		
		
		
		
		
		Vehiculo x;
		x = new Auto("Toyota", 1997, "Rojo", "JDM 097", 2000);
		
		
		
		
	}

}
*/
// CLASE 3/5 


import datos.*;
import entidades.Auto;
 
 public class Test {

	public static void main(String[] args) {
		
		AutoRepositorio datoAutos = new AutoRepositorio();
		
		datoAutos.inicializa();
		
		datoAutos.setAuto (new Auto("Ford", 2020, "verde", "AWE 234 WW", 20));
		System.out.println(datoAutos.getAuto("AWE 234 WW"));
		
	}	
}		


