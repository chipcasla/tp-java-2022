package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Curso;
import logic.ControlCursos;

/**
 * Servlet implementation class EliminarCursoModalidad
 */
@WebServlet("/Admin/EliminarCursoModalidad")
public class EliminarCursoModalidad extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ControlCursos controlCursos = new ControlCursos();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarCursoModalidad() {
        super();
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
		int idCurso = Integer.parseInt(request.getParameter("idCurso"));
		int idMod = Integer.parseInt(request.getParameter("idMod"));
		curso.setIdCurso(idCurso);
		curso.setIdModalidad(idMod);
		
		controlCursos.darDeBaja(curso);

		response.sendRedirect("Modalidades?curso="+idCurso);
		} catch (Exception e) {
			request.setAttribute("error", "Error inesperado. \n"+ e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

}
