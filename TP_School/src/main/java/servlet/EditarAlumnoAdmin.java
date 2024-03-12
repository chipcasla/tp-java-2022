package servlet;

import java.io.IOException;
import java.time.LocalDate;

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
 * Servlet implementation class EditarAlumnoAdmin
 */
@WebServlet("/Admin/EditarAlumnoAdmin")
public class EditarAlumnoAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ControlCursos controlCurso = new ControlCursos();
	AlumnoABMC controlAlumno = new AlumnoABMC();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarAlumnoAdmin() {
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
			Alumno a = new Alumno();
			a.setIdAlumno(Integer.parseInt(request.getParameter("id")));
			a = controlAlumno.getById(a);
			
			String nombre = request.getParameter("first-name");
			String apellido = request.getParameter("last-name");
			String email = request.getParameter("email");
			String dni = request.getParameter("dni");
			String tel = request.getParameter("tel");
			String esRegular = request.getParameter("isregular")!=null?request.getParameter("isregular"):"";
			LocalDate fechaNac = LocalDate.parse(request.getParameter("date-birth")); //devuelve formato: yyyy-mm-dd
	
			a.setNombre(nombre);
			a.setApellido(apellido);
			a.setMail(email);
			a.setDni(dni);
			a.setTel(tel);
			a.setFechaNac(fechaNac);
			a.setRegular(!(esRegular.equals("")));
			
			controlAlumno.updAlumno(a, "admin");
			
			String msgSuccess = "Actualización exitosa!";
			request.setAttribute("msg", msgSuccess);
			
			request.setAttribute("mi-alumno", a);
			request.getRequestDispatcher("controllerAdmin?option=stlist").forward(request, response);
	
		} catch(AppException e) {
			request.setAttribute("servlet", request.getContextPath()+"/Admin/controllerAdmin?option=stlist");
			request.setAttribute("action", request.getContextPath()+"/Admin/EditarAlumnoAdmin");
			Alumno a = new Alumno();
			a.setIdAlumno(Integer.parseInt(request.getParameter("id")));
			a = controlAlumno.getById(a);
			request.setAttribute("mi-alumno", a);
			request.setAttribute("validaciones", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/editar.jsp").forward(request, response);
		} catch(Exception e) {
			request.setAttribute("error", "Ocurrio un error inesperado. \n"+e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}

}
