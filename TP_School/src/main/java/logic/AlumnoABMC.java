package logic;

import data.*;
import entities.*;

public class AlumnoABMC {
	
	private DataAlumno da;
	
	public AlumnoABMC() {
		da = new DataAlumno();
	}
	
	public void addAlumno(Alumno a) {
		da.add(a);
	}
	
	public void updAlumno(Alumno a) {
		da.updatePerson(a);
	}
	
	

}
