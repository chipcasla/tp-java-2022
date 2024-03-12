package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Asignatura;
import entities.Curso;
import entities.Materia;
import logic.ControlAsignatura;

/**
 * Servlet implementation class EliminarAsignatura
 */
@WebServlet("/Admin/EliminarAsignatura")
public class EliminarAsignatura extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ControlAsignatura controlAsignatura = new ControlAsignatura();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarAsignatura() {
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
			
			int idCurso = Integer.parseInt(request.getParameter("idCurso"));
			int idMod = Integer.parseInt(request.getParameter("idMod"));
			int idMat = Integer.parseInt(request.getParameter("idMat"));
			
			Curso curso = new Curso();
			curso.setIdCurso(idCurso);
			curso.setIdModalidad(idMod);
			
			Materia materia = new Materia();
			materia.setIdMateria(idMat);
			
			Asignatura asignatura = new Asignatura(curso,materia);
			
			controlAsignatura.darDeBaja(asignatura);

			response.sendRedirect("Materias?idCurso="+idCurso+"&idMod="+idMod);
			
		} catch (Exception e) {
			request.setAttribute("error", "Error inesperado. \n"+ e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
	}

}
