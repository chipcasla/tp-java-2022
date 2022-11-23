package logic;

import data.*;
import entities.*;
import java.time.*;

public class NuevaInscripcion {
	
	private DataInscripcion di;
	
	public NuevaInscripcion() {
		di=new DataInscripcion();
	}
	
	public Inscripcion inscribirse(Alumno alumno, Curso curso) {
		Inscripcion i = null;
		if(alumno.getUltInscripcion() == null) {
			di.setUltimaInscripcion(alumno);
		}
		if(alumno.getUltInscripcion().getFechaInscripcion().getYear() != LocalDate.now().getYear()) {
			i = new Inscripcion(alumno, curso, LocalDate.now());
			di.add(i);
		}
		return i;
	}

}
