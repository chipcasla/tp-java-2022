package logic;

import data.*;
import entities.*;

public class abmcAlumno {
	
	private DataAlumno da;
	
	public abmcAlumno() {
		da = new DataAlumno();
	}
	
	public void addAlumno(Alumno a) {
		da.add(a);
	}
	
	

}
