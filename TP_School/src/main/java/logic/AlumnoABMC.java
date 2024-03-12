package logic;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.*;
import entities.*;

public class AlumnoABMC {
	
	private DataPersona da;
	
	public AlumnoABMC() {
		da = new DataPersona();
	}
	
	public LinkedList<Alumno> getAll(){
		return da.getAllStudents();
	}
	
	public Alumno getByDocumento(Alumno a) {
		return da.getByDni(a);
	}
	
	public Alumno getById(Alumno a) {
		return da.getById(a);
	}
	
	public void addAlumno(Alumno a) {
		a.setRegular(true);
		if(!a.getNombre().isEmpty() && !a.getApellido().isEmpty() && !a.getMail().isEmpty() && 
				!a.getDni().isEmpty() && !a.getTel().isEmpty() && !a.getFechaNac().equals(null)) {
			Pattern patEmail = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
			Matcher matcherEmail = patEmail.matcher(a.getMail());
			Pattern patDni = Pattern.compile("[0-9]{8}");
			Matcher matcherDni = patDni.matcher(a.getDni());
			Pattern patTel = Pattern.compile("[0-9]{10}");
			Matcher matcherTel = patTel.matcher(a.getTel());
			if(matcherEmail.matches() && matcherDni.matches() && matcherTel.matches()) {
				Pattern patPw = Pattern.compile("^(?=\\w*\\d)(?=\\w*[a-z])\\S{3,16}$");
				Matcher matcherPw = patPw.matcher(a.getPassword());
				if (matcherPw.matches()) {
					da.add(a);					
				} else {
					throw new AppException("Contraseña debe tener 3 o mas caracteres, y al menos un número y una letra.");
				}
			} else {
				String val = "";
				if(!matcherEmail.matches()) {val = val + "Correo inválido. ";};
				if(!matcherDni.matches()) {val = val + "DNI inválido. ";};
				if(!matcherTel.matches()) {val = val + "Teléfono inválido.";};
				throw new AppException(val);
			}
		} else {
			throw new AppException("Complete todos los campos");
		}
	}
		
	public void updAlumno(Alumno a, String tipo) {
		Alumno alumnoEditar = da.getById(a);
		if(alumnoEditar != null) {
		if(!a.getNombre().isEmpty() && !a.getApellido().isEmpty() && !a.getMail().isEmpty() && 
				!a.getDni().isEmpty() && !a.getTel().isEmpty() && !a.getFechaNac().equals(null)) {
			Pattern patEmail = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
			Matcher matcherEmail = patEmail.matcher(a.getMail());
			Pattern patDni = Pattern.compile("[0-9]{8}");
			Matcher matcherDni = patDni.matcher(a.getDni());
			Pattern patTel = Pattern.compile("[0-9]{10}");
			Matcher matcherTel = patTel.matcher(a.getTel());
			if(matcherEmail.matches() && matcherDni.matches() && matcherTel.matches()) {
				if(tipo == "alumno") {
					a.setRegular(alumnoEditar.isRegular());
				}
				da.updatePerson(a);
			} else {
				String val = "";
				if(!matcherEmail.matches()) {val = val + "Correo inválido. ";};
				if(!matcherDni.matches()) {val = val + "DNI inválido. ";};
				if(!matcherTel.matches()) {val = val + "Teléfono inválido.";};
				throw new AppException(val);
			}
		} else {
			throw new AppException("Complete todos los campos");
		}
		} else {
			throw new AppException("Alumno no encontrado");
		}
	}
	
	public void dltAlumno(Alumno a) {
		da.delete(a);
	}
	
	

}
