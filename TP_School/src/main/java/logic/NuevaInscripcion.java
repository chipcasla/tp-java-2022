package logic;

import data.*;
import entities.*;
import java.time.*;
import java.util.LinkedList;

public class NuevaInscripcion {
	
	private DataInscripcion di;
	private DataCurso dc;
	
	public NuevaInscripcion() {
		di=new DataInscripcion();
		dc=new DataCurso();
	}
	
	public LinkedList<Inscripcion> misInscripciones(Alumno a) {
		
		return di.getInscripciones(a);
	}
	
	public LinkedList<Curso> cursosDisponibles(Alumno a) {
		
		return dc.getCursosDisp(a);
	}
	
	public Curso cursoPorNombre(Curso c) {
		return dc.getByName(c);
	}
	
	public Inscripcion inscribirse(Alumno alumno, Curso curso) {
		Inscripcion i = null;
		if(alumno.getUltInscripcion() == null) {
			di.setUltimaInscripcion(alumno);
		}
		if(alumno.getUltInscripcion().getFechaInscripcion().getYear() != LocalDate.now().getYear()) {
			if(curso.getIdCurso()-1 <= alumno.getUltInscripcion().getCurso().getIdCurso() 
					&& curso.getIdCurso() >= alumno.getUltInscripcion().getCurso().getIdCurso()) {
				i = new Inscripcion(alumno, curso, LocalDate.now());
				di.add(i);
			}
		}
		return i;
	}

}
