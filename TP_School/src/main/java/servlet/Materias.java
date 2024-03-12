package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.AppException;
import entities.Asignatura;
import entities.Curso;
import entities.Materia;
import logic.ControlAsignatura;
import logic.ControlCursos;

/**
 * Servlet implementation class Materias
 */
@WebServlet("/Admin/Materias")
public class Materias extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ControlAsignatura controlAsignatura = new ControlAsignatura();
	ControlCursos controlCursos = new ControlCursos();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Materias() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Curso curso = new Curso();
			int idCurso = Integer.parseInt(request.getParameter("idCurso"));
			int idMod = Integer.parseInt(request.getParameter("idMod"));
			curso.setIdCurso(idCurso);
			curso.setIdModalidad(idMod);
			
			Asignatura asignatura = new Asignatura();
			asignatura.setCurso(curso);
			
			LinkedList<Asignatura> asignaturas = controlAsignatura.getAsignaturas(asignatura);
			curso = controlCursos.cursoPorId(curso);
			
			request.setAttribute("materias", asignaturas);
			request.setAttribute("curso", curso);
			request.getRequestDispatcher("/WEB-INF/listado-materias.jsp").forward(request, response);
		} catch(Exception e) {
			request.setAttribute("error", "Error inesperado. \n" + e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nombreMateria = request.getParameter("materia");
		String idCurso = request.getParameter("curso");
		String idMod = request.getParameter("mod");
		try {
			 Materia materia = new Materia();
			 materia.setNombre(nombreMateria);
			 materia = controlAsignatura.addMateria(materia);
			 request.setAttribute("msg", "Materia agregada");
			 
			 String path = "/Admin/GestionMaterias?idCurso=" + idCurso + "&idMod=" + idMod;
			 request.getRequestDispatcher(path).forward(request, response);
			 
		} catch(AppException e) {
			request.setAttribute("validaciones", e.getMessage());
			String path = "/Admin/GestionMaterias?idCurso=" + idCurso + "&idMod=" + idMod;
			 request.getRequestDispatcher(path).forward(request, response);
		} catch(Exception e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
	}

}
