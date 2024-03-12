package logic;

import java.util.LinkedList;

import data.*;
import entities.*;

public class ConsultaNotas {
	
	private DataNota dn;
	private DataCurso dc;
	private DataInscripcion di;
	
	public ConsultaNotas() {
		dn = new DataNota();
		dc = new DataCurso();
		di = new DataInscripcion();
	}
	
	public LinkedList<Nota> miBoletinVirtual(Alumno a,String idC,String idM) {
		if(idC != null && idM != null && idC != "" && idM != ""  && idM != "") {
			Integer idCurso = Integer.parseInt(idC);
			Integer idMod = Integer.parseInt(idM);
			Curso curso = new Curso();
			curso.setIdCurso(idCurso);
			curso.setIdModalidad(idMod);
			if(dc.getById(curso) != null) {
				LinkedList<Inscripcion> inscripciones = di.getInscripciones(a);
				boolean inscripcionExiste = false;
				for(Inscripcion i : inscripciones) {
					if(i.getCurso().getIdCurso() == idCurso && i.getCurso().getIdModalidad() == idMod) {
						inscripcionExiste = true;
						break;
					}
				}
				if(inscripcionExiste) {
					return dn.getMisNotas(a,idCurso,idMod);												
				} else {
					throw new AppException("Inscripcion no encontrada");
				}
			} else {
				throw new AppException("Curso no encontrado");
			}
		} else {
			throw new AppException("Complete todos los campos");
		}
	}

}
