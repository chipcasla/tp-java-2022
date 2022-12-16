package logic;

import data.*;
import entities.*;

public class Login {
	private DataPersona dp;
	
	public Login() {
		dp=new DataPersona();
	}
	
	public Persona validate(Persona p) {
		/*  
		 * generar un hash de la password utilizando un cifrado 
		 */
		return dp.getByUser(p);
	}
	
	
	
}