package interfaz;

import entidades.*;

import java.util.Scanner;

public class ManejoEmpleados {

	public static void main(String[] args) {
		
		Empleado[] empleados = new Empleado[3];
		Scanner lector = new Scanner(System.in);
		
		for (int i = 0; i < empleados.length; i++) {
			
			Empleado person;
			
			person = cargaDatos(lector);
			
			empleados[i] = person;
			
		}
		
		for (int i = 0; i < empleados.length; i++) {
			System.out.println(empleados[i]);
		}
	

	}
	
	public static Empleado cargaDatos(Scanner lector) {
		Empleado person;
		
		int dni = Integer.parseInt(lector.nextLine());
		String nombre = lector.nextLine();
		String apellido = lector.nextLine();
		String email = lector.nextLine();
		double sBase = Double.parseDouble(lector.nextLine());
		
		System.out.println("Ingrese A (Administrativo) o V (Vendedor): ");
		if(lector.nextLine().equalsIgnoreCase("A")) {
			int hExtras = Integer.parseInt(lector.nextLine());
			int hMes = Integer.parseInt(lector.nextLine());
			person = new Administrativo(dni,nombre,apellido,email,sBase,hExtras,hMes);
		} else {
			int porcentaje = Integer.parseInt(lector.nextLine());
			int totalVentas = Integer.parseInt(lector.nextLine());
			person = new Vendedor(dni,nombre,apellido,email,sBase,porcentaje,totalVentas);
		}
		
		return person;
	}

}
