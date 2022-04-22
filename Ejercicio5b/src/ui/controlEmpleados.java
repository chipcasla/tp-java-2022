package ui;

import entidades.*;
import java.util.ArrayList;
import java.util.Scanner;

public class controlEmpleados {

	public static void main(String[] args) {
		ArrayList<Empleado> empleados = new ArrayList<>();
		Scanner lector = new Scanner(System.in);
		boolean agregarEmpleado = true;
		
		while(agregarEmpleado) {
			
			Empleado person;
			
			person = cargaDatos(lector);
			
			empleados.add(person);
			
			System.out.println("Desea agregar un nuevo empleado?[S/N] ");
			if(((lector.nextLine()).equalsIgnoreCase("N")) || (empleados.size() == 20)) {
				agregarEmpleado = false;
			}
		}
		
		for (Empleado p : empleados) {
			System.out.println(p);
		}
		
		lector.close();
		
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
