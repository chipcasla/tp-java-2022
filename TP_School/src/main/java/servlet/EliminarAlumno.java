package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Alumno;
import entities.AppException;
import logic.AlumnoABMC;

/**
 * Servlet implementation class EliminarAlumno
 */
@WebServlet("/Admin/EliminarAlumno")
public class EliminarAlumno extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AlumnoABMC controlAlumno = new AlumnoABMC();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarAlumno() {
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
			a.setId(Integer.parseInt(request.getParameter("id")));
			a = controlAlumno.getById(a);
			if(a == null) {
				throw new AppException("Alumno no encontrado");
			} else {
			controlAlumno.dltAlumno(a);
			
			String mensaje = "El alumno "+a.getApellido()+", "+a.getNombre()+" ha sido eliminado";
			request.setAttribute("msg", mensaje);
			request.getRequestDispatcher("/Admin/controllerAdmin?option=stlist").forward(request, response);
			}
		} catch(AppException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/Admin/controllerAdmin?option=stlist").forward(request, response);			

		}
		catch(Exception e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);			

		}
	}

}
