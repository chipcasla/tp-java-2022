package servlet;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Alumno;
import entities.AppException;
import entities.Curso;
import entities.Inscripcion;
import logic.NuevaInscripcion;
import utils.Correo;

/**
 * Servlet implementation class EditarInscripcion
 */
@WebServlet("/EditarInscripcion")
public class EditarInscripcion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarInscripcion() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user") != null) {
		Alumno a = (Alumno) request.getSession().getAttribute("user");
		Curso c = a.getUltInscripcion().getCurso();
		request.setAttribute("disponibles", c);
		request.getRequestDispatcher("/WEB-INF/edit-inscripcion.jsp").forward(request, response);
		} else {
			request.setAttribute("validaciones", "No ha iniciado sesion");
			request.getRequestDispatcher("Logout").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer idCurso = request.getParameter("cur")!=null?Integer.parseInt(request.getParameter("cur")):null;
			Integer idMod = request.getParameter("mod")!=null?Integer.parseInt(request.getParameter("mod")):null;
			NuevaInscripcion ni = new NuevaInscripcion();
			
			Alumno a = (Alumno)request.getSession().getAttribute("user");
			
			Curso c = new Curso();
			c.setIdCurso(idCurso);
			c.setIdModalidad(idMod);
			
			Inscripcion i = ni.updInscripcion(a, c);
			
			a.setUltInscripcion(i);
			
			String msg = "Inscripcion actualizada";
			request.setAttribute("msg", msg);
			
			Correo correo = new Correo();
			try {
				correo.confirmarInscripción(a, i);
			} catch (MessagingException e) {
				request.setAttribute("validaciones", "No se pudo enviar correo de confirmación.");
			}
			
			request.getRequestDispatcher("Alumno?opcion=inscripciones").forward(request, response);
		} catch(AppException e) {
			request.setAttribute("validaciones", e.getMessage());
			Alumno a = (Alumno) request.getSession().getAttribute("user");
			Curso c = a.getUltInscripcion().getCurso();
			request.setAttribute("disponibles", c);
			request.getRequestDispatcher("/WEB-INF/edit-inscripcion.jsp").forward(request, response);
		} catch(Exception e) {
			request.setAttribute("error", "Ocurrió un error inesperado. \n"+ e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}

}
