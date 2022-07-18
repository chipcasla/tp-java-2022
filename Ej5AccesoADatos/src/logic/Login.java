package logic;

import java.util.LinkedList;

import data.*;
import entities.*;

public class Login {
	private DataPersona dp;
	
	public Login() {
		dp=new DataPersona();
	}
	
	public Persona validate(Persona p) {
		/* para hacer más seguro el manejo de passwords este sería un lugar 
		 * adecuado para generar un hash de la password utilizando un cifrado
		 * asimétrico como sha256 y utilizar el hash en lugar de la password en plano 
		 */
		return dp.getByUser(p);
	}

	public LinkedList<Persona> getAll(){
		return dp.getAll();
	}

	public Persona getByDocumento(Persona per) {
		return dp.getByDocumento(per);
		
	}
	
	public LinkedList<Persona> getByLastName(Persona per) {
		return dp.getByLastName(per);
	}
	
	public void setPersona(Persona p) {
		dp.add(p);
	}
	
	public LinkedList<Rol> listarRoles() {
		DataRol dr;
		dr = new DataRol();
		return(dr.getAll());
	}
	
	public Rol getRolById(Rol r) {
		DataRol dr = new DataRol();
		return(dr.getById(r));
	}
	
	public void setRolPersona(Persona p,Rol r) {
		DataRol dr = new DataRol();
		dr.addRolPersona(p, r);
	}
	
	public void updatePerson(Persona p) {
		dp.updatePerson(p);
	}
	
	public void deleteByDocumento(Persona p) {
		dp.delete(p);
	}
}
