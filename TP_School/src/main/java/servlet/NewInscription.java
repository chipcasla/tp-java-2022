package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.*;
import logic.NuevaInscripcion;

/**
 * Servlet implementation class NewInscription
 */
@WebServlet("/new-inscription")
public class NewInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewInscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String curso = request.getParameter("cur")!=null?request.getParameter("cur"):"";
		String mod = request.getParameter("mod")!=null?request.getParameter("mod"):"";
		NuevaInscripcion ni = new NuevaInscripcion();
		if(!curso.isEmpty() && !mod.isEmpty()) {
			Curso c = new Curso();
			c.setNombre(curso);
			c.setModalidad(mod);
			Alumno a = (Alumno)request.getSession().getAttribute("user");
			Inscripcion i = ni.inscribirse(a, ni.cursoPorNombre(c));
			a.setUltInscripcion(i);
			String msg = "Inscripcion exitosa";
			request.setAttribute("msg", msg);
			request.getSession().setAttribute("user", a);
			NuevaInscripcion li = new NuevaInscripcion();
			LinkedList<Inscripcion> inscripciones = li.misInscripciones((Alumno) request.getSession().getAttribute("user"));
			request.setAttribute("inscripciones", inscripciones);
			request.getRequestDispatcher("WEB-INF/mis-inscripciones.jsp").forward(request, response);
		} else {
			String val = "Complete todos los campos";
			request.setAttribute("validaciones", val);
			NuevaInscripcion li = new NuevaInscripcion();
			LinkedList<Curso> insDisp = li.cursosDisponibles((Alumno) request.getSession().getAttribute("user"));
			request.setAttribute("disponibles", insDisp);
			request.getRequestDispatcher("WEB-INF/inscripcion.jsp").forward(request, response);
		}
		
	}

}
