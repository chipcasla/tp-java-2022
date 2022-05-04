package datos;

import java.util.ArrayList;

import entidades.Auto;

public class AutoRepositorio {
	
		private ArrayList<Auto> autos;

		public void inicializa() {
			
			autos = new ArrayList<Auto>();
		
		}
		
		public void setAuto(Auto unAuto) {
		
			autos.add(unAuto);
		
		}
		
		public Auto getAuto(String laPatente) {
			
			for (Auto a:autos) {
				if ((a.getPatente()).equals(laPatente)) {
					return a; 
				}
			}
			return null;
		}
		
		public ArrayList<Auto> getAll(){
			return autos;
		}
	}

