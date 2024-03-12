package logic;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.DataPersona;
import entities.AppException;
import entities.Profesor;

public class ProfesorABMC {
	
private DataPersona da;
	
	public ProfesorABMC() {
		da = new DataPersona();
	}
	
	public LinkedList<Profesor> getAll(){
		return da.getAllTeachers();
	}
	
	public Profesor getOne(Profesor p) {
		return da.getProfesorById(p);
	}
	
	public void add(Profesor p) {
		if(p.getNombre() != "" && p.getApellido() != "" && p.getDni() != "" 
				&& p.getDni() != "" && p.getMail() != "" && p.getTel() != "" 
				&& !(p.getFechaNacFormat().equals(null)) && p.getPassword() != "") {
			Pattern patEmail = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
			Matcher matcherEmail = patEmail.matcher(p.getMail());
			Pattern patDni = Pattern.compile("[0-9]{8}");
			Matcher matcherDni = patDni.matcher(p.getDni());
			Pattern patPass = Pattern.compile("^(?=\\w*[0-9])(?=\\w*[a-z])\\S{3,16}$");
			Matcher matcherPass = patPass.matcher(p.getPassword());
			if(matcherEmail.matches() && matcherDni.matches() && matcherPass.matches()) {				
				da.addProfesor(p);			
			} else {
				String error = "";
				if(!matcherEmail.matches()) {error = error + "Correo inválido. ";};
				if(!matcherDni.matches()) {error = error + "DNI inválido. ";};
				if(!matcherPass.matches()) {error = error + "Contraseña inválida.";};
				
				throw new AppException(error);
			}
		} else {
			throw new AppException("Complete todos los campos");			
		}
	}
		
	public void updProfesor(Profesor p) {
		if(getOne(p)!=null) {
		if(p.getNombre() != "" && p.getApellido() != "" && p.getDni() != "" 
				&& p.getDni() != "" && p.getMail() != "" && p.getTel() != "" 
				&& !(p.getFechaNacFormat().equals(null)) && p.getPassword() != "") {
			Pattern patEmail = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
			Matcher matcherEmail = patEmail.matcher(p.getMail());
			Pattern patDni = Pattern.compile("[0-9]{8}");
			Matcher matcherDni = patDni.matcher(p.getDni());
			if(matcherEmail.matches() && matcherDni.matches()) {
				da.updateProfesor(p);
			} else {
				String error = "";
				if(!matcherEmail.matches()) {error = error + "Correo inválido. ";};
				if(!matcherDni.matches()) {error = error + "DNI inválido. ";};
				
				throw new AppException(error);
			}
		} else {
			throw new AppException("Complete todos los campos");			
		}
		} else {
			throw new AppException("Profesor no encontrado");
		}
	}
	
	public void dltProfesor(Profesor p) {
		da.deleteProfesor(p);
	}

}
