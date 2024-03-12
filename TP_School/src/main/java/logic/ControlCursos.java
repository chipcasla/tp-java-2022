package logic;

import java.util.LinkedList;

import data.DataCurso;
import entities.AppException;
import entities.Curso;

public class ControlCursos {
	
	private DataCurso dc;
	
	public ControlCursos() {
		dc=new DataCurso();
	}
	
	public LinkedList<Curso> getAllMods() {
		return dc.getModalidades();
	}
	
	public LinkedList<Curso> getAllMods(Curso c) {
		
		if(dc.getCursoById(c)==null) {
			throw new AppException("Curso no encontrado");
		}
		else {			
			LinkedList<Curso> misCursos = dc.getMisMods(c);
			return misCursos;
		}
	}
	
	public LinkedList<Curso> getAllCursos() {
		
		return dc.getAll();
	}
	
	public Curso cursoPorNombre(Curso c) {
		return dc.getByName(c);
	}
	
	public Curso soloCursoPorId(Curso c) {
		return dc.getCursoById(c);
	}
	
	public Curso soloModadalidadPorId(Curso c) {
		return dc.getModById(c);
	}
	
	public Curso cursoPorId(Curso c) {
		return dc.getById(c);
	}
	
	public Curso addModalidad(Curso c) {
		
		if(c.getModalidad() == null || c.getModalidad() == "") {
			throw new AppException("Complete nombre de modalidad");
		} else {
			return dc.addModalidad(c);
		}
	}
	
	public void darDeAlta(Curso c) {
		dc.darAltaCursoMod(c);
	}
	
	public void darDeBaja(Curso c) {
		dc.darBajaCursoMod(c);
	}

	public void addCursoModalidad(Curso curso) {
		Curso c = dc.getById(curso);
		if (c == null) {
			if(dc.getModById(curso) != null) {				
				dc.addCursoModalidad(curso);
			} else {
				throw new AppException("Modalidad no encontrada");
			}
		} else {
			if(c.esActivo() == 0) {
				darDeAlta(c);
			} else {
				throw new AppException("La modalidad ya existe en "+ c.getNombre() +" año");
			}
		}
	}

	public void eliminarModalidad(Curso curso) {
		if(!dc.tieneCursosActivos(curso)) {
			dc.deleteModalidad(curso);
		} else {
			throw new AppException("No se puede eliminar ya que tiene cursos activos.\n"
					+ "Pruebe darlos de baja desde el curso.");
		}
	}

	public void updModalidad(Curso mod) {
		if(mod.getModalidad() != "") {			
			dc.updateModalidad(mod);
		} else {
			throw new AppException("Complete el nombre de modalidad.");
		}
		
	}
	
}
