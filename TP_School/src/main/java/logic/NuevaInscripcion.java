package logic;

import data.*;
import entities.*;
import java.time.*;
import java.util.LinkedList;

public class NuevaInscripcion {
	
	private DataInscripcion di;
	private DataCurso dc;
	private DataNota dn;
	
	public NuevaInscripcion() {
		di=new DataInscripcion();
		dc=new DataCurso();
		dn=new DataNota();
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
	
	public Inscripcion updInscripcion(Alumno alumno, Curso curso) {
		Inscripcion i = null;
		if(alumno.getUltInscripcion() == null) {
			di.setUltimaInscripcion(alumno);
		}
		if(alumno.getUltInscripcion().getCurso().getIdCurso() == curso.getIdCurso()) {
				i = new Inscripcion(alumno,curso, LocalDate.now());
				di.updateInscripcion(i);
			}
			
		return i;
	}
	
	public Alumno dltInscripcion(Alumno alumno, Inscripcion insToDlt) {
		
		di.delete(insToDlt);
		di.setUltimaInscripcion(alumno);
		return alumno;
		
	}
	
	public boolean estaHabilitado(Alumno a) {
		LinkedList<Nota> notas = dn.getMisNotas(a,a.getUltInscripcion().getCurso().getIdCurso(),a.getUltInscripcion().getCurso().getIdModalidad());
		int aprobadas = 0;
		for (Nota n : notas) {
			if(n.getNotaFinal() >= 6) {
				aprobadas = aprobadas + 1;
			}
		}
		return aprobadas >= (notas.size()-2);
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
