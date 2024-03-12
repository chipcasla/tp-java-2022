package logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.*;
import entities.*;

public class Login {
	private DataPersona dp;
	
	public Login() {
		dp=new DataPersona();
	}
	
	public Persona validate(Persona p) {
		if(!p.getMail().isEmpty() && !p.getPassword().isEmpty() && 
				p.getMail() != "" && p.getPassword() != "") {
			Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
			Matcher matcher = pattern.matcher(p.getMail());
			if(matcher.matches()) {
				Persona user = dp.getByUser(p);	
				if(user != null) {
					return user;
				} else {
					throw new AppException("El usuario ingresado no es correcto");
				}
			} else {
				throw new AppException("Correo inválido");
			}
		} else {
			throw new AppException("Complete todos los campos");
		}
	}
	
	
	
}