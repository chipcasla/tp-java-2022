package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Alumno;
import entities.AppException;
import logic.AlumnoABMC;
import logic.ControlCursos;

/**
 * Servlet implementation class EditarAlumno
 */
@WebServlet("/EditarAlumno")
public class EditarAlumno extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ControlCursos controlCurso = new ControlCursos();
	AlumnoABMC mAlumno = new AlumnoABMC();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarAlumno() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Alumno a = null;
			
			a = (Alumno) request.getSession().getAttribute("user");
			
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
			
			a.setNombre(nombre);
			a.setApellido(apellido);
			a.setMail(email);
			a.setDni(dni);
			a.setTel(tel);
			a.setFechaNac(fechaNac);
			
			mAlumno.updAlumno(a, "alumno");
			
			String msgSuccess = "Actualización exitosa!";
			request.setAttribute("msg", msgSuccess);
			
			request.getSession().setAttribute("user", a);
			request.getRequestDispatcher("Alumno?opcion=editar").forward(request, response);
		
		} catch(AppException e) {
			request.setAttribute("validaciones", e.getMessage());
			request.getRequestDispatcher("Alumno?opcion=editar").forward(request, response);
		} catch(Exception e) {
			request.setAttribute("error", "Ocurrio un error inesperado. \n"+e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
}
