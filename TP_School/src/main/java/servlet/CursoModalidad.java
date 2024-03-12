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
 * Servlet implementation class CursoModalidad
 */
@WebServlet("/Admin/CursoModalidad")
public class CursoModalidad extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ControlCursos controlCursos = new ControlCursos();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CursoModalidad() {
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
		
		String idCurso = request.getParameter("curso");
		try {
			 String idModalidad = request.getParameter("modalidad");
			 Curso curso = new Curso();
			 curso.setIdCurso(Integer.parseInt(idCurso));
			 curso.setIdModalidad(Integer.parseInt(idModalidad));
			 controlCursos.addCursoModalidad(curso);
			 response.sendRedirect("Modalidades?curso="+idCurso);
			 //request.getRequestDispatcher("Modalidades?curso="+idCurso).forward(request, response);
		} catch(AppException e) {
			request.setAttribute("validaciones2", e.getMessage());
			request.getRequestDispatcher("/Admin/controllerAdmin?option=agregar-modalidad&curso="+idCurso).forward(request, response);		
		} catch(Exception e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		
	}

}
