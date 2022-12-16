package servlet;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.*;
import logic.*;

/**
 * Servlet implementation class Signin
 */
@WebServlet({ "/Signin", "/signin" })
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		NuevaInscripcion li = null;
		String opc = null;
		opc = request.getParameter("option");
		LinkedList<Inscripcion> inscripciones;
		Alumno a;
		Curso c;
		
		switch (opc) {
		case "edit":
			if(request.getSession().getAttribute("user").getClass()==Persona.class) {
				AlumnoABMC cAlu = new AlumnoABMC();
				a = new Alumno();
				a.setDni(request.getParameter("id"));
				a = cAlu.getByDocumento(a);
				request.setAttribute("mi-alumno", a);
			}
			request.getRequestDispatcher("WEB-INF/editar.jsp").forward(request, response);
		break;
		case "add":
			if(request.getSession().getAttribute("user").getClass()==Persona.class) {
				request.getRequestDispatcher("WEB-INF/agregar-alumno.jsp").forward(request, response);
			} else {
				String val = "Error URL. Vuelva a ingresar";
				request.setAttribute("validaciones", val);
				request.getRequestDispatcher("index.jsp").forward(request, response);										
			}
		break;
		case "darbaja":
			if(request.getSession().getAttribute("user").getClass()==Persona.class) {
				AlumnoABMC cAlu = new AlumnoABMC();
				a = new Alumno();
				a.setIdAlumno(Integer.parseInt(request.getParameter("id")));
				a = cAlu.getById(a);
				cAlu.dltAlumno(a);
				request.setAttribute("msg", "El alumno "+a.getApellido()+", "+a.getNombre()+" ha sido eliminado");
				request.setAttribute("alumnos", cAlu.getAll());
				request.getRequestDispatcher("WEB-INF/listado-alumnos.jsp").forward(request, response);	
			} else {
				String val = "Error URL. Vuelva a ingresar";
				request.setAttribute("validaciones", val);
				request.getRequestDispatcher("index.jsp").forward(request, response);										
			}
		break;
		case "inscription":
			li = new NuevaInscripcion();
			inscripciones = li.misInscripciones((Alumno) request.getSession().getAttribute("user"));
			request.setAttribute("inscripciones", inscripciones);
			request.getRequestDispatcher("WEB-INF/mis-inscripciones.jsp").forward(request, response);
		break;
		case "editinscription":
			a = (Alumno) request.getSession().getAttribute("user");
			c = a.getUltInscripcion().getCurso();
			request.setAttribute("disponible", c);
			request.getRequestDispatcher("WEB-INF/edit-inscripcion.jsp").forward(request, response);
		break;
		case "deleteinscription":
			a = (Alumno) request.getSession().getAttribute("user");
			li = new NuevaInscripcion();
			a = li.dltInscripcion(a,a.getUltInscripcion());
			String msg = "Inscripcion eliminada";
			request.setAttribute("msg", msg);
			request.getSession().setAttribute("user", a);
			inscripciones = li.misInscripciones((Alumno) request.getSession().getAttribute("user"));
			request.setAttribute("inscripciones", inscripciones);
			request.getRequestDispatcher("WEB-INF/mis-inscripciones.jsp").forward(request, response);
		break;
		case "inscribir":
			a = (Alumno) request.getSession().getAttribute("user");
			if(a.getUltInscripcion().getCurso().getIdCurso()!=5 && a.getUltInscripcion().getFechaInscripcion().getYear()!=LocalDate.now().getYear()) {				
				li = new NuevaInscripcion();
				if(li.estaHabilitado(a)) {
					LinkedList<Curso> insDisp = li.cursosDisponibles((Alumno) request.getSession().getAttribute("user"));
					request.setAttribute("disponibles", insDisp);
					request.getRequestDispatcher("WEB-INF/inscripcion.jsp").forward(request, response);
				} else {
					inscripciones = li.misInscripciones((Alumno) request.getSession().getAttribute("user"));
					request.setAttribute("inscripciones", inscripciones);
					String val = "No aprobó suficientes materias en "+a.getUltInscripcion().getCurso().getNombre()+" año para inscribirse.";
					request.setAttribute("validaciones", val);
					request.getRequestDispatcher("WEB-INF/mis-inscripciones.jsp").forward(request, response);
				}
			} else {
				li = new NuevaInscripcion();
				inscripciones = li.misInscripciones((Alumno) request.getSession().getAttribute("user"));
				request.setAttribute("inscripciones", inscripciones);
				String val = "Ya se ha inscripto. No hay cursos disponibles.";
				request.setAttribute("validaciones", val);
				request.getRequestDispatcher("WEB-INF/mis-inscripciones.jsp").forward(request, response);
			}
		break;
		case "report-card":
			li = new NuevaInscripcion();
			inscripciones = li.misInscripciones((Alumno) request.getSession().getAttribute("user"));
			if(!(request.getParameter("cour")==null || request.getParameter("mod")==null)) {
				ConsultaNotas cn = new ConsultaNotas();
				request.setAttribute("calificaciones",cn.miBoletinVirtual((Alumno) request.getSession().getAttribute("user"),request.getParameter("cour"),request.getParameter("mod")));				
			}
			request.setAttribute("inscripciones", inscripciones);
			request.getRequestDispatcher("WEB-INF/boletin.jsp").forward(request, response);
		break;

		default:
			if(request.getSession().getAttribute("user").getClass()==Alumno.class) {
				request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
			} else if(request.getSession().getAttribute("user").getClass()==Persona.class) {
				AlumnoABMC cAlu = new AlumnoABMC();
				request.setAttribute("alumnos", cAlu.getAll());
				request.getRequestDispatcher("WEB-INF/listado-alumnos.jsp").forward(request, response);						
			}
		break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Persona p = new Persona();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(!email.isEmpty() && !password.isEmpty()) {
			Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
			Matcher matcher = pattern.matcher(email);
			if(matcher.matches()) {
				p.setMail(email);
				p.setPassword(password);
				Login login = new Login();
				p = login.validate(p);
				if(p != null) {
					request.getSession().setAttribute("user", p);
					if(p.getClass()==Alumno.class) {
						request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
					} else if(p.getClass()==Persona.class) {
						AlumnoABMC cAlu = new AlumnoABMC();
						request.setAttribute("alumnos", cAlu.getAll());
						request.getRequestDispatcher("WEB-INF/listado-alumnos.jsp").forward(request, response);						
					}
				
				} else {
					String val = "El usuario ingresado no es correcto";
					request.setAttribute("validaciones", val);
					request.getRequestDispatcher("index.jsp").forward(request, response);	
				}
			} else {
				String val = "Correo inválido";
				request.setAttribute("validaciones", val);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		} else {
			String val = "Complete todos los campos";
			request.setAttribute("validaciones", val);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		
	}

}
