package logic;

import data.*;
import entities.*;

public class Login {
	private DataAlumno da;
	
	public Login() {
		da=new DataAlumno();
	}
	
	public Alumno validate(Alumno a) {
		/* para hacer más seguro el manejo de passwords este sería un lugar 
		 * adecuado para generar un hash de la password utilizando un cifrado
		 * asimétrico como sha256 y utilizar el hash en lugar de la password en plano 
		 */
		return da.getByUser(a);
	}
	
	
	
}