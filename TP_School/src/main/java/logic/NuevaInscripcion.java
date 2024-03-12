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
	
	public void setUltimaInscripcion(Alumno alu) {
		di.setUltimaInscripcion(alu);
	}
	
	public LinkedList<Curso> cursosDisponibles(Alumno a) {
		if(a.getUltInscripcion() == null) {
			di.setUltimaInscripcion(a);
		}
		if(a.getUltInscripcion() == null) {
			ControlCursos controlCursos = new ControlCursos();
			return controlCursos.getAllCursos();
		} else {
			return dc.getCursosDisp(a);
		}
	}
	
	public Inscripcion updInscripcion(Alumno alumno, Curso curso) {
		Inscripcion i = null;
		if(alumno.getUltInscripcion() == null) {
			di.setUltimaInscripcion(alumno);
		}
		if(alumno.getUltInscripcion() != null) {
			curso = dc.getById(curso);
		if(curso != null) {
			if((Integer)curso.getIdCurso() != null && (Integer) curso.getIdModalidad() != null) {
			if(alumno.getUltInscripcion().getCurso().getIdCurso() == curso.getIdCurso() 
			   && alumno.getUltInscripcion().getFechaInscripcion().getYear() == LocalDate.now().getYear()) {
				if(alumno.getUltInscripcion().getFechaInscripcion().plusMonths(2).isAfter(LocalDate.now())) {
					i = new Inscripcion(alumno,curso, LocalDate.now());
					di.updateInscripcion(i);					
				} else {
					throw new AppException("No puede editar esta inscripción");
				}
			} else {
				throw new AppException("No puede editar esta inscripcion");
			}
		} else {
			throw new AppException("Complete todos los campos");
		}
		} else {
			throw new AppException("Curso no encontrado");
		} 
		} else {
			throw new AppException("No tiene inscripciones para editar");
		}
			
		return i;
	}
	
	public Alumno dltInscripcion(Alumno alumno, Inscripcion insToDlt) {
		if(alumno.getUltInscripcion().getCurso().getIdCurso() == insToDlt.getCurso().getIdCurso() 
				   && alumno.getUltInscripcion().getFechaInscripcion().getYear() == LocalDate.now().getYear()) {
			if(alumno.getUltInscripcion().getFechaInscripcion().plusMonths(2).isAfter(LocalDate.now())) {
				di.delete(insToDlt);
				di.setUltimaInscripcion(alumno);
				return alumno;
			} else {
				throw new AppException("No puede editar esta inscripción");
			}
		} else {
			throw new AppException("No puede editar esta inscripcion");
		}		
	}
	
	public LinkedList<Curso> inscripcionesDisponibles(Alumno a) {
		
		if(a.getUltInscripcion() == null) {
			setUltimaInscripcion(a);
		}
		
		if(a.getUltInscripcion() != null) {
			if(a.getUltInscripcion().getCurso().getIdCurso()!=5) {
			  if(a.getUltInscripcion().getFechaInscripcion().getYear()!=LocalDate.now().getYear()) {				
				if(estaHabilitado(a)) {
					LinkedList<Curso> insDisp = cursosDisponibles(a);
					return insDisp;
				} else {
					throw new AppException("No aprobó suficientes materias en "+
							a.getUltInscripcion().getCurso().getNombre()+
							" año para inscribirse.");
				}
			} else {
				throw new AppException("Ya se ha inscripto a un curso este año.");
			}
			} else {
				throw new AppException("Ya se ha inscripto a todos los cursos.");
			}
			} else {
				throw new AppException("La primera inscripción debe solicitarla en secretaría.");
			}
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
		if((Integer) curso.getIdCurso() != null && (Integer) curso.getIdModalidad() != null) {
			Curso c = dc.getById(curso);
			if(c != null) {
			if(alumno.getUltInscripcion() == null) {
				if(misInscripciones(alumno).isEmpty()) {
					i = new Inscripcion(alumno, c, LocalDate.now());
					di.add(i);
					return i;
				} else {
					throw new AppException("Ocurrio un error inesperado en la inscripción");
				}
			} else
			if(alumno.getUltInscripcion().getCurso().getIdCurso()!=5) {
				if(alumno.getUltInscripcion().getFechaInscripcion().getYear() != LocalDate.now().getYear()) {
					if(curso.getIdCurso() == alumno.getUltInscripcion().getCurso().getIdCurso() 
							|| curso.getIdCurso() == (alumno.getUltInscripcion().getCurso().getIdCurso() + 1)) {
						if(estaHabilitado(alumno)) {
							i = new Inscripcion(alumno, c, LocalDate.now());
							di.add(i);
							return i;
						} else {
							throw new AppException("No ha aprobado la cantidad de materias requeridas "
									+ "en "+ c.getNombre() +" año.");
						}
					} else {
						throw new AppException("No puede inscribirse a "+ c.getNombre() +" año.");
					}
				} else {
					throw new AppException("Ya se ha inscripto a un curso este año.");
				}
			} else {
				throw new AppException("Ya se ha inscripto a todos los cursos.");
			}
			} else {
				throw new AppException("Curso no encontrado");
			}
		} else {
			throw new AppException("Complete todos los campos.");
		}
			
	}

}
