package logic;

import java.util.LinkedList;

import data.DataAsignatura;
import entities.AppException;
import entities.Asignatura;
import entities.Curso;
import entities.Materia;

public class ControlAsignatura {
	
	private DataAsignatura da;
	private ControlCursos controlCursos;
	private ProfesorABMC controlProfesores;

	public ControlAsignatura() {
		da=new DataAsignatura();
		controlCursos = new ControlCursos();
		controlProfesores = new ProfesorABMC();
	}
	
	public Materia getMateria(Materia m) {
		return da.getMateria(m);
	}
	
	public Asignatura getAsignatura(Asignatura a) {
		return da.getAsignatura(a);
	}
	
	public LinkedList<Materia> getMaterias() {
		return da.getAllMaterias();
	}
	
	public LinkedList<Materia> misMaterias(Asignatura a) {
		if(controlCursos.cursoPorId(a.getCurso()) != null) {
			return da.getMateriasCurso(a);			
		} else {
			throw new AppException("Curso no encontrado.");
		}
	}
	
	public LinkedList<Asignatura> getAsignaturas(Asignatura a) {
		if(controlCursos.cursoPorId(a.getCurso()) != null) {
			return da.getAsignaturasPorCurso(a);			
		} else {
			throw new AppException("Curso no encontrado.");
		}
	}

	public Materia addMateria(Materia m) {
		
		if(m.getNombre() == null || m.getNombre() == "") {
			throw new AppException("Complete nombre de materia.");
		} else {
			return da.addMateria(m);
		}
		
	}
	
	public void addAsignatura(Asignatura asig) {
		Asignatura a = getAsignatura(asig);
		if (a == null ) {
			Curso curso = controlCursos.cursoPorId(asig.getCurso());
			Materia materia = getMateria(asig.getMateria());
			if(curso != null && materia != null) {				
				da.addAsignatura(asig);
			} else {
				String noEncontrado = curso==null?
						"Curso no encontrado":"Materia no encontrada";
				throw new AppException(noEncontrado);
			}
		} else {
			if(a.esActivo() == 0) {
				darDeAlta(a);
			} else {
				throw new AppException("La materia ya existe en "+a.getCurso().getNombre()+" año "+
						a.getCurso().getModalidad()+".");
			}
		}
	}

	private void darDeAlta(Asignatura a) {
		da.darDeAlta(a);
	}
	
	public void darDeBaja(Asignatura a) {
		da.darDeBaja(a);
	}

	public void updAsignatura(Asignatura asignatura) {
		Asignatura a = getAsignatura(asignatura);
		if (a != null ) {
			if(controlProfesores.getOne(asignatura.getProfesor()) != null) {
				da.updateProfesor(asignatura);
			} else {
				throw new AppException("Profesor no encontrado");
			}
		} else {
			Curso curso = controlCursos.cursoPorId(asignatura.getCurso());
			if (curso == null) {
				throw new AppException("Curso no encontrado");
			}
			Materia materia = getMateria(asignatura.getMateria());
			if (materia == null) {
				throw new AppException("Materia no encontrada");
			}
			throw new AppException("Asignatura no encontrada");
		}
		
	}

}
