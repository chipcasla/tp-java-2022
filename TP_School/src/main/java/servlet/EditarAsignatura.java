package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.AppException;
import entities.Asignatura;
import entities.Curso;
import entities.Materia;
import entities.Profesor;
import logic.ControlAsignatura;
import logic.ProfesorABMC;

/**
 * Servlet implementation class EditarAsignatura
 */
@WebServlet("/Admin/EditarAsignatura")
public class EditarAsignatura extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ControlAsignatura controlAsignatura = new ControlAsignatura();
	ProfesorABMC controlProfesores = new ProfesorABMC();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarAsignatura() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
			
			asignatura = controlAsignatura.getAsignatura(asignatura);
			
			if(asignatura == null) {
				request.setAttribute("error", "Asignatura no encontrada.");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			} else {
				request.setAttribute("profesores", controlProfesores.getAll());
				request.setAttribute("asignatura", asignatura);
				request.getRequestDispatcher("/WEB-INF/editar-asignatura.jsp").forward(request, response);
			}
			
			
		} catch (Exception e) {
			request.setAttribute("error", "Error inesperado. \n"+e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
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
			
			int idProfesor = Integer.parseInt(request.getParameter("profesor"));
			Profesor profesor = new Profesor();
			profesor.setId(idProfesor);
			
			asignatura.setProfesor(profesor);
			
			this.controlAsignatura.updAsignatura(asignatura);
			
			response.sendRedirect("Materias?idCurso="+idCurso+"&idMod="+idMod);
			
		} catch (AppException e) {
			int idCurso = Integer.parseInt(request.getParameter("idCurso"));
			int idMod = Integer.parseInt(request.getParameter("idMod"));
			int idMat = Integer.parseInt(request.getParameter("idMat"));
			
			Curso curso = new Curso();
			curso.setIdCurso(idCurso);
			curso.setIdModalidad(idMod);
			
			Materia materia = new Materia();
			materia.setIdMateria(idMat);
			
			Asignatura asignatura = new Asignatura(curso,materia);
			
			request.setAttribute("validaciones", e.getMessage());
			request.setAttribute("profesores", controlProfesores.getAll());
			request.setAttribute("asignatura", asignatura);
			request.getRequestDispatcher("/WEB-INF/editar-asignatura.jsp").forward(request, response);			
		} catch (Exception e) {
			request.setAttribute("error", "Ocurrió un error inesperado.\n"+e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
	}

}
