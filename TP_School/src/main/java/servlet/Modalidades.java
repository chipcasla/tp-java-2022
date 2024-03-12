package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.AppException;
import entities.Curso;
import logic.ControlCursos;

/**
 * Servlet implementation class Modalidades
 */
@WebServlet("/Admin/Modalidades")
public class Modalidades extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ControlCursos controlCursos = new ControlCursos();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modalidades() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {			
			int idCurso = Integer.parseInt(request.getParameter("curso"));
			Curso curso = new Curso();
			curso.setIdCurso(idCurso);
			curso = controlCursos.soloCursoPorId(curso);
			if(curso == null) {
				throw new AppException("Curso no encontrado");
			} else {
				LinkedList<Curso> curso_mods = controlCursos.getAllMods(curso);
				request.setAttribute("curso", curso);
				request.setAttribute("modalidades", curso_mods);
				request.getRequestDispatcher("/WEB-INF/listado-modalidades.jsp").forward(request, response);
			}
		} catch(AppException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		} catch(Exception e) {
			request.setAttribute("error", "Error inesperado");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idCurso = request.getParameter("curso");
		try {
			 String nombreModalidad = request.getParameter("modalidad");
			 Curso curso = new Curso();
			 curso.setModalidad(nombreModalidad);
			 controlCursos.addModalidad(curso);
			 request.setAttribute("msg", "Modalidad agregada");
			 if(idCurso == "") {
				 request.getRequestDispatcher("/Admin/controllerAdmin?option=modalidades").forward(request, response);				 
			 } else {
				 request.getRequestDispatcher("/Admin/controllerAdmin?option=agregar-modalidad&curso="+idCurso).forward(request, response);				 
			 }
		} catch(AppException e) {
			request.setAttribute("validaciones", e.getMessage());
			if(idCurso == "") {
				 request.getRequestDispatcher("/Admin/controllerAdmin?option=modalidades").forward(request, response);				 
			 } else {
				 request.getRequestDispatcher("/Admin/controllerAdmin?option=agregar-modalidad&curso="+idCurso).forward(request, response);				 
			 }
		} catch(Exception e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
	}

}
