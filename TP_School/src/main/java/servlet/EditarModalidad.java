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
 * Servlet implementation class EditarModalidad
 */
@WebServlet("/Admin/EditarModalidad")
public class EditarModalidad extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ControlCursos controlCursos = new ControlCursos();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarModalidad() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		Curso c = new Curso();
		c.setIdModalidad(id);
		c = controlCursos.soloModadalidadPorId(c);
		if(c == null) {
			request.setAttribute("error", "Modalidad no encontrada.");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		} else {
			request.setAttribute("modalidad", c);
			request.getRequestDispatcher("/WEB-INF/editar-modalidad.jsp").forward(request, response);
		}
		} catch(Exception e) {
			request.setAttribute("error", "Ocurrió un error inesperado. "+e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Curso mod = new Curso();
			mod.setIdModalidad(Integer.parseInt(request.getParameter("idModalidad")));
			mod.setModalidad(request.getParameter("nombreModalidad"));
			
			this.controlCursos.updModalidad(mod);
			String mensaje = "La modalidad " + mod.getModalidad() 
				+ " ha sido actualizada correctamente!";
			request.setAttribute("msg", mensaje);
			request.getRequestDispatcher("/Admin/controllerAdmin?option=modalidades").forward(request, response);
		} catch (AppException e) {
			request.setAttribute("validaciones", e.getMessage());
			request.getRequestDispatcher("/Admin/controllerAdmin?option=modalidades").forward(request, response);			
		} catch (Exception e) {
			request.setAttribute("error", "Ocurrió un error inesperado.\n "+ e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
	}

}
