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
		Alumno a = (Alumno) request.getSession().getAttribute("user");
		
		String nombre = request.getParameter("first-name");
		request.setAttribute("nom", nombre);
		String apellido = request.getParameter("last-name");
		String email = request.getParameter("email");
		String dni = request.getParameter("dni");
		String tel = request.getParameter("tel");
		LocalDate fechaNac;
		try {
			fechaNac = LocalDate.parse(request.getParameter("date-birth")); //devuelve formato: yyyy-mm-dd
		} catch (DateTimeParseException e) {
			fechaNac = null;
		}
		
		if(!nombre.isEmpty() && !apellido.isEmpty() && !email.isEmpty() && !dni.isEmpty() && !tel.isEmpty() && !fechaNac.equals(null)) {
			if(!(nombre.equalsIgnoreCase(a.getNombre()) && apellido.equalsIgnoreCase(a.getApellido())
					&& email.equalsIgnoreCase(a.getMail()) && dni.equalsIgnoreCase(a.getDni()) 
					&& tel.equalsIgnoreCase(a.getTel()) && fechaNac.equals(a.getFechaNac()))) {
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
					AlumnoABMC mAlumno = new AlumnoABMC();
					mAlumno.updAlumno(a);
					String msgSuccess = "Actualización exitosa!";
					request.setAttribute("msg", msgSuccess);
					request.getSession().setAttribute("user", a);
					request.getRequestDispatcher("WEB-INF/editar.jsp").forward(request, response);
				} else {
					String val = "";
					if(!matcherEmail.matches()) {val = val + "Correo inválido. ";};
					if(!matcherDni.matches()) {val = val + "DNI inválido. ";};
					if(!matcherTel.matches()) {val = val + "Teléfono inválido.";};
					request.setAttribute("validaciones", val);
					request.getRequestDispatcher("WEB-INF/editar.jsp").forward(request, response);		
				}
			} else {
				String val = "No hay cambios";
				request.setAttribute("validaciones", val);
				request.getRequestDispatcher("WEB-INF/editar.jsp").forward(request, response);
			}
		} else {
			String val = "Complete todos los campos";
			request.setAttribute("validaciones", val);
			request.getRequestDispatcher("WEB-INF/editar.jsp").forward(request, response);
		}
	}

}
