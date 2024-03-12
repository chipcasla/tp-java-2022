package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Alumno;
import entities.AppException;
import logic.NuevaInscripcion;

/**
 * Servlet implementation class EliminarInscripcion
 */
@WebServlet("/EliminarInscripcion")
public class EliminarInscripcion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	NuevaInscripcion li = new NuevaInscripcion();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarInscripcion() {
        super();
        // TODO Auto-generated constructor stub
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
			Alumno a = (Alumno) request.getSession().getAttribute("user");
			
			a = li.dltInscripcion(a,a.getUltInscripcion());
			String msg = "Inscripcion eliminada";
			request.setAttribute("msg", msg);
			request.getSession().setAttribute("user", a);
			request.getRequestDispatcher("Alumno?opcion=inscripciones").forward(request, response);
		} catch(AppException e) {
			request.setAttribute("validaciones", e.getMessage());
			request.getRequestDispatcher("Alumno?opcion=inscripciones").forward(request, response);
		} catch(Exception e) {
			request.setAttribute("error", "Ocurrió un error inesperado. \n"+e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} 
	}

}
