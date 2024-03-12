package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Alumno;
import entities.AppException;
import entities.Curso;
import logic.AlumnoABMC;
import logic.ControlCursos;

/**
 * Servlet implementation class AgregarAlumno
 */
@WebServlet("/Admin/AgregarAlumno")
public class AgregarAlumno extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AlumnoABMC controlAlumno = new AlumnoABMC();
	ControlCursos controlCurso = new ControlCursos();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarAlumno() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Alumno a = new Alumno();
			
			String nombre = request.getParameter("first-name");
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
			String password = request.getParameter("password");
			
			a.setNombre(nombre);
			a.setApellido(apellido);
			a.setMail(email);
			a.setDni(dni);
			a.setTel(tel);
			a.setFechaNac(fechaNac);
			a.setRegular(true);
			a.setPassword(password);
			
			//agregamos al alumno
			controlAlumno.addAlumno(a);
			a = controlAlumno.getByDocumento(a);
			
			//llevamos al usuario a la pagina para inscribirlo
			request.setAttribute("mi-alumno", a);
			LinkedList<Curso> insDisp = controlCurso.getAllCursos();
			request.setAttribute("disponibles", insDisp);
			request.setAttribute("opc", "inscribirfromadmin");
			request.getRequestDispatcher("/WEB-INF/agregar-inscripcion.jsp").forward(request, response);
		} 
		catch(AppException e) {
			request.setAttribute("validaciones", e.getMessage());
			request.setAttribute("servlet", "Admin/controllerAdmin?option=stlist");
			request.getRequestDispatcher("/WEB-INF/agregar-alumno.jsp").forward(request, response);
		} catch(Exception e) {
			request.setAttribute("error", "Error inesperado"+ e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
