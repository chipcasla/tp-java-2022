package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Alumno;
import entities.AppException;
import entities.Curso;
import logic.NuevaInscripcion;

/**
 * Servlet implementation class InscripcionAlumno
 */
@WebServlet({"/Inscripcion", "/inscripcion"})
public class InscripcionAlumno extends HttpServlet {
	private static final long serialVersionUID = 1L;
	NuevaInscripcion li = new NuevaInscripcion();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscripcionAlumno() {
        super();
 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user") != null) {
		try {
			Alumno a = (Alumno) request.getSession().getAttribute("user");
			
			LinkedList<Curso> inscripciones = li.inscripcionesDisponibles(a);
			request.setAttribute("disponibles", inscripciones);
			request.getRequestDispatcher("/WEB-INF/agregar-inscripcion.jsp").forward(request, response);			
		} catch(AppException e) {
			request.setAttribute("validaciones", e.getMessage());
			request.getRequestDispatcher("Alumno?opcion=inscripciones").forward(request, response);			
		}
		} else {
			request.setAttribute("validaciones", "No ha iniciado sesion");
			request.getRequestDispatcher("Logout").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
