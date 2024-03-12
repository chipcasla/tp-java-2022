package servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.AppException;
import entities.Profesor;
import logic.ProfesorABMC;

/**
 * Servlet implementation class EditarProfesor
 */
@WebServlet("/Admin/EditarProfesor")
public class EditarProfesor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProfesorABMC controlProfesor = new ProfesorABMC();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarProfesor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		Profesor p = new Profesor();
		p.setId(id);
		p = controlProfesor.getOne(p);
		if(p == null) {
			request.setAttribute("error", "Profesor no encontrado.");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		} else {
			request.setAttribute("profesor", p);
			request.getRequestDispatcher("/WEB-INF/editar-profesor.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Profesor profesor = new Profesor();
			profesor.setId(Integer.parseInt(request.getParameter("idProfesor")));
			profesor.setNombre(request.getParameter("first-name"));
			profesor.setApellido(request.getParameter("last-name"));
			profesor.setMail(request.getParameter("email"));
			profesor.setDni(request.getParameter("dni"));
			profesor.setCuil(request.getParameter("cuil"));
			profesor.setTel(request.getParameter("tel"));
			profesor.setFechaNac(LocalDate.parse(request.getParameter("date-birth")));
			
			controlProfesor.updProfesor(profesor);
			String mensaje = "El profesor " + profesor.getNombre() +
					" " + profesor.getApellido() + " ha sido actualizado correctamente!";
			request.setAttribute("msg", mensaje);
			request.getRequestDispatcher("/Admin/controllerAdmin?option=profesores").forward(request, response);
		} catch (AppException e) {
			request.setAttribute("validaciones", e.getMessage());
			request.getRequestDispatcher("/Admin/controllerAdmin?option=profesores").forward(request, response);			
		} catch (Exception e) {
			request.setAttribute("error", "Ocurrió un error inesperado.\n"+e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
	}

}
