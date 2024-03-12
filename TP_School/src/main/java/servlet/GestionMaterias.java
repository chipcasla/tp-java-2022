package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Asignatura;
import entities.Curso;
import entities.Materia;
import logic.ControlAsignatura;
import logic.ControlCursos;

/**
 * Servlet implementation class Asignaturas
 */
@WebServlet("/Admin/GestionMaterias")
public class GestionMaterias extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ControlAsignatura controlAsignatura = new ControlAsignatura();
	ControlCursos controlCurso = new ControlCursos();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionMaterias() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int idCurso = Integer.parseInt(request.getParameter("idCurso"));
			int idMod = Integer.parseInt(request.getParameter("idMod"));
			Curso curso = new Curso();
			curso.setIdCurso(idCurso);
			curso.setIdModalidad(idMod);
			
			Asignatura asig = new Asignatura();
			asig.setCurso(curso);
			
			LinkedList<Materia> materias = controlAsignatura.getMaterias();
			request.setAttribute("materiasDisponibles", materias);
			
			LinkedList<Materia> misMaterias = controlAsignatura.misMaterias(asig);
			request.setAttribute("materiasCurso", misMaterias);
			
			request.setAttribute("curso", controlCurso.cursoPorId(asig.getCurso()));
			
			request.getRequestDispatcher("/WEB-INF/agregar-materia.jsp").forward(request, response);
			
		} catch (Exception e) {
			
			request.setAttribute("error", "Error inesperado. \n"+e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
