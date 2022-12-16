package logic;

import java.util.LinkedList;
import data.*;
import entities.*;

public class AlumnoABMC {
	
	private DataPersona da;
	
	public AlumnoABMC() {
		da = new DataPersona();
	}
	
	public LinkedList<Alumno> getAll(){
		return da.getAll();
	}
	
	public Alumno getByDocumento(Alumno a) {
		return da.getByDni(a);
	}
	
	public Alumno getById(Alumno a) {
		return da.getById(a);
	}
	
	public void addAlumno(Alumno a) {
		da.add(a);
	}
		
	public void updAlumno(Alumno a) {
		da.updatePerson(a);
	}
	
	public void dltAlumno(Alumno a) {
		da.delete(a);
	}
	
	

}
