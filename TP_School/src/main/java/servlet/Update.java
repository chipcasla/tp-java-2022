package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Alumno;
import entities.Persona;
import logic.AlumnoABMC;

/**
 * Servlet implementation class Update
 */
@WebServlet("/update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Tu fecha de nacimiento es: ").append(request.getParameter("date-birth"));
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opc = request.getParameter("opc")!=null?request.getParameter("opc"):"";
		AlumnoABMC mAlumno = new AlumnoABMC();
		Alumno a = null;
		Persona p = null;
		String urlBack = null;
		
		if(opc.equals("")) {
			a = (Alumno) request.getSession().getAttribute("user");
			urlBack = "WEB-INF/editar.jsp";
		}
		else if(opc.equals("agregar")){
			p = (Persona) request.getSession().getAttribute("user");
			a = new Alumno();
			urlBack = "WEB-INF/agregar-alumno.jsp";
		} else if(opc.equals("editar")) {
			p = (Persona) request.getSession().getAttribute("user");
			a = new Alumno();
			a.setIdAlumno(Integer.parseInt(request.getParameter("id")));
			a = mAlumno.getById(a);
			urlBack = "WEB-INF/editar.jsp";
		}
		
		String nombre = request.getParameter("first-name");
		request.setAttribute("nom", nombre);
		String apellido = request.getParameter("last-name");
		String email = request.getParameter("email");
		String dni = request.getParameter("dni");
		String tel = request.getParameter("tel");
		String esRegular = request.getParameter("isregular")!=null?request.getParameter("isregular"):"";
		LocalDate fechaNac;
		try {
			fechaNac = LocalDate.parse(request.getParameter("date-birth")); //devuelve formato: yyyy-mm-dd
		} catch (DateTimeParseException e) {
			fechaNac = null;
		}
		
		if(!nombre.isEmpty() && !apellido.isEmpty() && !email.isEmpty() && !dni.isEmpty() && !tel.isEmpty() && !fechaNac.equals(null)) {
			Pattern patEmail = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
			Matcher matcherEmail = patEmail.matcher(email);
			Pattern patDni = Pattern.compile("[0-9]{8}");
			Matcher matcherDni = patDni.matcher(dni);
			Pattern patTel = Pattern.compile("[0-9]{10}");
			Matcher matcherTel = patTel.matcher(tel);
				if(matcherEmail.matches() && matcherDni.matches() && matcherTel.matches()) {
					a.setNombre(nombre);
					a.setApellido(apellido);
					a.setMail(email);
					a.setDni(dni);
					a.setTel(tel);
					a.setFechaNac(fechaNac);
					if(opc.equals("") || opc.equals("editar")) {
						if(opc.equals("editar")) {
							a.setRegular(!(esRegular.equals("")));
						}
						mAlumno.updAlumno(a);
						String msgSuccess = "Actualización exitosa!";
						request.setAttribute("msg", msgSuccess);
						if(opc.equals("")) {
							request.getSession().setAttribute("user", a);
						} else if(opc.equals("editar")) {
							request.setAttribute("mi-alumno", a);
						}
						request.getRequestDispatcher(urlBack).forward(request, response);
					} else if(opc.equals("agregar")) {					
						String password = request.getParameter("password");
						Pattern patPw = Pattern.compile("^(?=\\w*\\d)(?=\\w*[a-z])\\S{3,16}$");
						Matcher matcherPw = patPw.matcher(password);
						if (matcherPw.matches() && !(p.equals(null))) {
							a.setPassword(password);							
							mAlumno.addAlumno(a);
							a = mAlumno.getByDocumento(a);
							String msgSuccess = "Alumno añadido! ID: "+a.getIdAlumno()+". Legajo: "+a.getLegajo();
							request.setAttribute("msg", msgSuccess);
							request.setAttribute("alumnos", mAlumno.getAll());
							request.getRequestDispatcher("WEB-INF/listado-alumnos.jsp").forward(request, response);		
						} else {
							String val = "Contraseña inválida.";
							request.setAttribute("validaciones", val);
							request.getRequestDispatcher(urlBack).forward(request, response);
						}
					} else {
						String val = "Acceso indebido";
						if(opc.equals("editar")) {request.setAttribute("mi-alumno", a);}
						request.setAttribute("validaciones", val);
						request.getRequestDispatcher(urlBack).forward(request, response);
					}
				} else {
					String val = "";
					if(!matcherEmail.matches()) {val = val + "Correo inválido. ";};
					if(!matcherDni.matches()) {val = val + "DNI inválido. ";};
					if(!matcherTel.matches()) {val = val + "Teléfono inválido.";};
					request.setAttribute("validaciones", val);
					if(opc.equals("editar")) {request.setAttribute("mi-alumno", a);}
					request.getRequestDispatcher(urlBack).forward(request, response);		
				}
		} else {
			String val = "Complete todos los campos";
			if(opc.equals("editar")) {request.setAttribute("mi-alumno", a);}
			request.setAttribute("validaciones", val);
			request.getRequestDispatcher(urlBack).forward(request, response);
		}
	}

}
