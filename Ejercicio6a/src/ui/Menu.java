package ui;

import data.DbHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import entities.Producto;

public class Menu {
	
	private Scanner scan;
	private DbHandler db = new DbHandler();
	private String dateFormat = "d/MM/uuuu - HH:mm";
	final private DateTimeFormatter formato = DateTimeFormatter.ofPattern(dateFormat);

	
	public void start() {
		
		scan = new Scanner(System.in);
		String opc;
		
		do {			
			opc = menu();
			
			switch (opc) {
			case "1":
				listar();
				break;
				
			case "2":
				buscar();				
				break;
				
			case "3":
				nuevo();
				break;
				
			case "4":
				borrar();
				break;
			
			case "5":
				actualizar();
				break;
				
			case "0":
				System.out.println("See you again...");
				break;
				
			default:
				System.out.println("try again...");
				break;
			}
			
		} while (! opc.equals("0"));
		
		scan.close();	
	}
		
	private String menu() {
		
		System.out.print("1-list/2-search/3-new/4-delete/5-update/0-exit\n>>opcion: ");
		return scan.nextLine();
		
	}
	
	private void listar() {
		
		System.out.println(db.listar());
		
	}
	
	private void buscar() {
		
		Producto p = new Producto();
		System.out.print("ID p/buscar: ");
		p.setId(Integer.parseInt(scan.nextLine()));
		Producto prod = db.buscar(p);
		if (prod != null) {
			System.out.println(prod);
		} else {
			System.out.println("Producto ID:"+p.getId()+" no encontrado...");
		}
	}
	
	private void nuevo() {
		
		System.out.println("::Datos del nuevo producto::");
		Producto p = new Producto();
		this.cargaDatos(p);
		db.nuevoProducto(p);
		System.out.println("ID de nuevo producto: "+ p.getId());
		
	}
	
	private void cargaDatos(Producto nP) {
		
		System.out.print("Nombre: ");
		nP.setName(scan.nextLine());
		System.out.print("Descripcion: ");
		nP.setDescription(scan.nextLine());
		System.out.print("Precio: ");
		nP.setPrice(Double.parseDouble(scan.nextLine()));
		System.out.print("Stock: ");
		nP.setStock(Integer.parseInt(scan.nextLine()));
		System.out.print("Envio incluido? (S/N): ");
		nP.setShippingIncluded(scan.nextLine().equalsIgnoreCase("S"));
		System.out.print("Disabled on ("+dateFormat+"): ");
		nP.setDisabledOn(LocalDateTime.parse(scan.nextLine(),formato));;
		
	}
	
	private void borrar() {
		
		Producto p = new Producto();
		System.out.println("--Productos actuales--");
		this.listar();
		System.out.print("ID p/borrar: ");
		p.setId(Integer.parseInt(scan.nextLine()));
		db.borrar(p);
		
	}
	
	private void actualizar() {
		
		Producto p = new Producto();
		System.out.println("--Productos actuales--");
		this.listar();
		System.out.print("ID p/actualizar: ");
		p.setId(Integer.parseInt(scan.nextLine()));
		Producto updProd = db.buscar(p);
		if (updProd!=null) {
			System.out.println("Datos actuales: ");
			System.out.println(updProd);
			System.out.println("Cargar nuevos datos:");
			this.cargaDatos(updProd);
			db.actualizar(updProd);
		} else {
			System.out.println("No se encontró el producto.");		
		}
	}
}















