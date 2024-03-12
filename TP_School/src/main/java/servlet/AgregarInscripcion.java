package servlet;

import java.io.IOException;
import java.util.LinkedList;

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
import entities.Persona;
import logic.AlumnoABMC;
import logic.NuevaInscripcion;
import utils.Correo;

/**
 * Servlet implementation class AgregarInscripcion
 */
@WebServlet({"/AgregarInscripcion", "/Admin/AgregarInscripcion"})
public class AgregarInscripcion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	NuevaInscripcion ni = new NuevaInscripcion();
	AlumnoABMC ac = new AlumnoABMC();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarInscripcion() {
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
		if(request.getSession().getAttribute("user") != null) {
		try {
			
			Integer idCurso = Integer.parseInt(request.getParameter("cur"));
			Integer idMod = Integer.parseInt(request.getParameter("mod"));
			
			// creo el alumno
			Alumno a = null;
			Persona p = (Persona) request.getSession().getAttribute("user");
			if(p.getTipo().equals("alumno")) {
				a = (Alumno)request.getSession().getAttribute("user");				
			} else {
				a = new Alumno();
				a.setId(Integer.parseInt(request.getParameter("id-alumno")));
				a = ac.getById(a);
			}
			
			// creo curso con datos del formulario
			Curso c = new Curso();
			c.setIdCurso(idCurso);
			c.setIdModalidad(idMod);
			// distingo la accion a realizar y obtengo la inscripcion
			Inscripcion i = ni.inscribirse(a, c);
			
			//actualizo la última inscripcion del alumno
			a.setUltInscripcion(i);
			Correo correo = new Correo();
			
			if(p.getTipo().equals("alumno")) {
				String msg = "Inscripcion exitosa";
				request.setAttribute("msg", msg);
				request.getSession().setAttribute("user", a);
				NuevaInscripcion li = new NuevaInscripcion();
				LinkedList<Inscripcion> inscripciones = li.misInscripciones((Alumno) request.getSession().getAttribute("user"));
				request.setAttribute("inscripciones", inscripciones);
				// enviar correo de confirmacion
				try {
				correo.confirmarInscripción(a, i);
				} catch (MessagingException e) {
					request.setAttribute("validaciones", "No se pudo enviar correo de confirmación.");
				}
				request.getRequestDispatcher("Alumno?opcion=inscripciones").forward(request, response);
			} else {
				String msgSuccess = "Alumno añadido! ID: "+a.getIdAlumno()+". Legajo: "+a.getLegajo()
				+"\n Inscripto en "+ a.getUltInscripcion().getCurso().getNombre()+" año "+ a.getUltInscripcion().getCurso().getModalidad();
				request.setAttribute("msg", msgSuccess);
				request.setAttribute("alumnos", ac.getAll());
				try {
					correo.confirmarInscripción(a, i);
				} catch (MessagingException e) {
					request.setAttribute("validaciones", "No se pudo enviar correo de confirmación.");
				}
				request.getRequestDispatcher("/WEB-INF/listado-alumnos.jsp").forward(request, response);	
			}	
			
		} catch (AppException e) {
			Persona p = (Persona) request.getSession().getAttribute("user");
			if(p.getTipo().equals("alumno")) {
				request.setAttribute("validaciones", e.getMessage());
				request.getRequestDispatcher("Inscripcion").forward(request, response);
			} else {
				request.setAttribute("validaciones", e.getMessage());
				request.getRequestDispatcher("/WEB-INF/listado-alumnos.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		} else {
			request.setAttribute("validaciones", "No ha iniciado sesion");
			request.getRequestDispatcher("Logout").forward(request, response);
		}
	}

}
