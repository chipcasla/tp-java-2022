package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Alumno;
import entities.AppException;
import entities.Inscripcion;
import entities.Nota;
import logic.ConsultaNotas;
import logic.NuevaInscripcion;

/**
 * Servlet implementation class AppAlumno
 */
@WebServlet({"/Alumno","/alumno"})
public class AppAlumno extends HttpServlet {
	private static final long serialVersionUID = 1L;
	NuevaInscripcion li = new NuevaInscripcion();
	ConsultaNotas cn = new ConsultaNotas();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppAlumno() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opc = null;
		opc = request.getParameter("opcion");
		opc = opc!=null?opc.toLowerCase():null;
		if(request.getSession().getAttribute("user") != null) {
		if(opc!=null) {
		switch (opc) {
		case "editar":
			request.getRequestDispatcher("/WEB-INF/editar.jsp").forward(request, response);
		break;
		case "inscripciones":
			LinkedList<Inscripcion> inscripciones = li.misInscripciones((Alumno) request.getSession().getAttribute("user"));
			if(!inscripciones.isEmpty()) {
				request.setAttribute("inscripciones", inscripciones);
				request.getRequestDispatcher("/WEB-INF/mis-inscripciones.jsp").forward(request, response);
			} else {
				String val = "Debe solicitar su primera inscripcion en secretaría.";
				request.setAttribute("validaciones", val);
				request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
			}
		break;
		case "boletin":
			try {
			li = new NuevaInscripcion();
			inscripciones = li.misInscripciones((Alumno) request.getSession().getAttribute("user"));
			request.setAttribute("inscripciones", inscripciones);
			if(!(request.getParameter("cour")==null || request.getParameter("mod")==null)) {
				LinkedList<Nota> notas = cn.miBoletinVirtual((Alumno) request.getSession().getAttribute("user"),request.getParameter("cour"),request.getParameter("mod"));
				request.setAttribute("calificaciones", notas);				
			}
			request.getRequestDispatcher("/WEB-INF/boletin.jsp").forward(request, response);
			} catch(AppException e) {
				request.setAttribute("validaciones", e.getMessage());
				request.getRequestDispatcher("/WEB-INF/boletin.jsp").forward(request, response);
			} catch(Exception e) {
				request.setAttribute("error", "Ocurrió un error inesperado. \n"+e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		break;

		default:
			request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
		break;
		}
		} else {
			request.setAttribute("validaciones", "Error URL");
			request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
		}
		} else {
			request.setAttribute("validaciones", "No ha iniciado sesion");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
