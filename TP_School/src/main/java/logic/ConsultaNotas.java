package logic;

import java.util.LinkedList;

import data.*;
import entities.*;

public class ConsultaNotas {
	
	private DataNota dn;
	
	public ConsultaNotas() {
		dn = new DataNota();		
	}
	
	public LinkedList<Nota> miBoletinVirtual(Alumno a,String idC,String idM) {
		Integer idCurso = Integer.parseInt(idC);
		Integer idMod = Integer.parseInt(idM);
		return dn.getMisNotas(a,idCurso,idMod);
	}

}
