package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.AppException;
import entities.Curso;
import logic.ControlCursos;

/**
 * Servlet implementation class EliminarModalidad
 */
@WebServlet("/Admin/EliminarModalidad")
public class EliminarModalidad extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ControlCursos controlCursos = new ControlCursos();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarModalidad() {
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
			Curso curso = new Curso();
			int idMod = Integer.parseInt(request.getParameter("modalidad"));
			curso.setIdModalidad(idMod);
			
			controlCursos.eliminarModalidad(curso);
			
			request.setAttribute("msg", "Modalidad eliminada");
			request.getRequestDispatcher("/Admin/controllerAdmin?option=modalidades").forward(request, response);		
			
		} catch (AppException e) {
			request.setAttribute("validaciones", e.getMessage());
			request.getRequestDispatcher("/Admin/controllerAdmin?option=modalidades").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

}
