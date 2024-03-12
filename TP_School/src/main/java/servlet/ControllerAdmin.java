package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Alumno;
import entities.Curso;
import logic.AlumnoABMC;
import logic.ControlCursos;
import logic.ProfesorABMC;

/**
 * Servlet implementation class ControllerAdmin
 */
@WebServlet({ "/Admin/ControllerAdmin", "/Admin/controllerAdmin" })
public class ControllerAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ControlCursos controlCursos = new ControlCursos();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String opc = null;
		opc = request.getParameter("option");
		opc = opc!=null?opc.toLowerCase():null;
		AlumnoABMC cAlu = new AlumnoABMC();
		ProfesorABMC cPro = new ProfesorABMC();
		
		if(request.getSession().getAttribute("admin-key") != null && 
				request.getSession().getAttribute("user") != null) {
			if(opc!=null) {
		switch (opc) {
		case "stlist":
			cAlu = new AlumnoABMC();
			request.setAttribute("alumnos", cAlu.getAll());
			request.getRequestDispatcher("/WEB-INF/listado-alumnos.jsp").forward(request, response);
		break;
		case "edit":
			Alumno a = new Alumno();
			a.setDni(request.getParameter("id"));
			a = cAlu.getByDocumento(a);
			request.setAttribute("mi-alumno", a);
			request.setAttribute("servlet", request.getContextPath()+"/Admin/controllerAdmin?option=stlist");
			request.setAttribute("action", request.getContextPath()+"/Admin/EditarAlumnoAdmin");
			request.getRequestDispatcher("/WEB-INF/editar.jsp").forward(request, response);
		break;
		case "profesores":
			request.setAttribute("profesores", cPro.getAll());
			request.getRequestDispatcher("/WEB-INF/listado-profesores.jsp").forward(request, response);
		break;
		case "agregar-profesor":
			request.getRequestDispatcher("/WEB-INF/agregar-profesor.jsp").forward(request, response);		
		break;
		case "cursos":
			request.setAttribute("cursos", controlCursos.getAllCursos());
			request.getRequestDispatcher("/WEB-INF/listado-cursos.jsp").forward(request, response);
		break;
		case "agregar-modalidad":
			if(request.getParameter("curso") != null) {
				int idCurso = Integer.parseInt(request.getParameter("curso"));
				Curso curso = new Curso();
				curso.setIdCurso(idCurso);
				curso = controlCursos.soloCursoPorId(curso);
				if(curso == null) {
					request.setAttribute("error", "Curso no encontrado");
					request.getRequestDispatcher("/error.jsp").forward(request, response);
				} else {
					request.setAttribute("curso", curso);
					request.setAttribute("modalidades", controlCursos.getAllMods());
					request.getRequestDispatcher("/WEB-INF/agregar-modalidad.jsp").forward(request, response);		
				}
			} else {
				request.setAttribute("curso", null);
				request.getRequestDispatcher("/WEB-INF/agregar-modalidad.jsp").forward(request, response);
			}
		break;
		case "modalidades":
			request.setAttribute("modalidades", controlCursos.getAllMods());
			request.getRequestDispatcher("/WEB-INF/listado-completo-modalidades.jsp").forward(request, response);
		break;
		case "add":
				request.setAttribute("cursos", controlCursos.getAllCursos());
				request.setAttribute("servlet", request.getContextPath()+"/Admin/controllerAdmin?option=stlist");
				request.getRequestDispatcher("/WEB-INF/agregar-alumno.jsp").forward(request, response);		
		break;
		case "darbaja":
				cAlu = new AlumnoABMC();
				a = new Alumno();
				a.setIdAlumno(Integer.parseInt(request.getParameter("id")));
				a = cAlu.getById(a);
				cAlu.dltAlumno(a);
				request.setAttribute("msg", "El alumno "+a.getApellido()+", "+a.getNombre()+" ha sido eliminado");
				request.setAttribute("alumnos", cAlu.getAll());
				request.getRequestDispatcher("/WEB-INF/listado-alumnos.jsp").forward(request, response);
		break;
		default:
			request.getRequestDispatcher("/WEB-INF/controller-admin.jsp").forward(request, response);						
		break;
		
		}
			} else {
				request.setAttribute("validaciones", "Error URL");
				request.getRequestDispatcher("/WEB-INF/controller-admin.jsp").forward(request, response);
			}
		} else {
			String val = "Error URL. Vuelva a ingresar";
			request.setAttribute("validaciones", val);
			request.getRequestDispatcher("/index.jsp").forward(request, response);										
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
