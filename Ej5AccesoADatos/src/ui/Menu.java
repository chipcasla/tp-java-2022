package ui;

import java.util.LinkedList;
import java.util.Scanner;

import entities.*;
import logic.Login;

public class Menu {
	Scanner s=null;
	Login ctrlLogin = new Login();

	public void start() {
		s = new Scanner(System.in);
		Persona p=login();
		System.out.println("Bienvenido "+p.getNombre()+" "+p.getApellido());
		System.out.println();
		
		String command;
		do {
			command=getCommand();
			executeCommand(command);
			System.out.println();
			
		}while(!command.equalsIgnoreCase("exit"));
		
		s.close();
	}

	private void executeCommand(String command) {
		switch (command) {
		case "list":
			System.out.println(ctrlLogin.getAll());
			break;
		case "find":
			System.out.println(find());
			break;
		case "search":
			System.out.println(search());	
			break;
		case "new":
			System.out.println(newPerson());
			break;
		case "edit":
			editPerson();
			break;
		case "delete":
			delete();
			break;
		default:
			break;
		}
	}

	private String getCommand() {
		System.out.println("Ingrese el comando segun la opcion que desee realizar");
		System.out.println("list\t\tlistar todos");
		System.out.println("find\t\tbuscar por tipo y nro de documento"); //solo debe devolver 1
		System.out.println("search\t\tlistar por apellido"); //puede devolver varios
		System.out.println("new\t\tcrea una nueva persona y asigna un rol existente");
		System.out.println("edit\t\tbusca por tipo y nro de documento y actualiza todos los datos");
		System.out.println("delete\t\tborra por tipo y nro de documento");
		System.out.println();
		System.out.print("comando: ");
		return s.nextLine();
	}
	
	public Persona login() {
		Persona p=new Persona();
		
		System.out.print("Email: ");
		p.setEmail(s.nextLine());

		System.out.print("password: ");
		p.setPassword(s.nextLine());
		
		p=ctrlLogin.validate(p);
		
		return p;
		
	}
	
	private Persona find() {
		System.out.println();
		Persona p=new Persona();
		Documento d=new Documento();
		p.setDocumento(d);
		System.out.print("Tipo doc: ");
		d.setTipo(s.nextLine());

		System.out.print("Nro doc: ");
		d.setNro(s.nextLine());
		
		return ctrlLogin.getByDocumento(p);
	}
	
	private LinkedList<Persona> search() {
		System.out.println();
		Persona p = new Persona();
		System.out.print("Apellido: ");
		p.setApellido(s.nextLine());
		
		return ctrlLogin.getByLastName(p);
	}
	
	private String newPerson() {
		Persona p = new Persona();
		p.setDocumento(new Documento());
		loadDataPerson(p);
		ctrlLogin.setPersona(p);
		loadRoles(p);
		return "Nueva persona agregada ID: "+p.getId();				
	}
	
	private void loadDataPerson(Persona p) {
		System.out.print("Tipo Documento: ");
		p.getDocumento().setTipo(s.nextLine());;
		System.out.print("Nro. Documento: ");
		p.getDocumento().setNro(s.nextLine());
		System.out.print("Nombre: ");
		p.setNombre(s.nextLine());
		System.out.print("Apellido: ");
		p.setApellido(s.nextLine());
		System.out.print("Email: ");
		p.setEmail(s.nextLine());
		System.out.print("Contraseña: ");
		p.setPassword(s.nextLine());
		System.out.print("Tel: ");
		p.setTel(s.nextLine());
		System.out.print("Está habilitado? [S/N]: ");
		p.setHabilitado(s.nextLine().trim().equalsIgnoreCase("S"));	
				
	}
	
	private void loadRoles(Persona p) {
		Rol rol = new Rol();
		System.out.println(ctrlLogin.listarRoles());
		System.out.print("Selecciona rol: ");
		rol.setId(Integer.parseInt(s.nextLine()));
		p.addRol(ctrlLogin.getRolById(rol));
		ctrlLogin.setRolPersona(p,rol);
	}
	
	private void editPerson() {
		Persona perToUpd = null;
		System.out.println(ctrlLogin.getAll());
		System.out.println();
		perToUpd = find();
		System.out.println(perToUpd);
		System.out.println();
		System.out.println("::::Nuevos Datos::::");
		loadDataPerson(perToUpd);
		ctrlLogin.updatePerson(perToUpd);
		
	}
	
	private void delete() {
		Persona pToDlt = null;
		System.out.println(ctrlLogin.getAll());
		System.out.println();
		pToDlt = find();
		System.out.println("Desea eliminar a "+pToDlt.getApellido()+", "+pToDlt.getNombre()+"? [S|N]: ");
		if(s.nextLine().equalsIgnoreCase("s")) {
			ctrlLogin.deleteByDocumento(pToDlt);
			System.out.println("Persona ID: "+pToDlt.getId()+", fue eliminada.");
		} else {
			System.out.println("Eliminacion CANCELADA.");
		}
	}

}
