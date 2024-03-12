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
 * Servlet implementation class ProfesorServlet
 */
@WebServlet({ "/Admin/ProfesorServlet", "/Admin/profesorServlet" })
public class ProfesorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProfesorABMC controlProfesor = new ProfesorABMC();
       
    public ProfesorServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Profesor profesor = new Profesor();
			
			profesor.setNombre(request.getParameter("first-name"));
			profesor.setApellido(request.getParameter("last-name"));
			profesor.setMail(request.getParameter("email"));
			profesor.setDni(request.getParameter("dni"));
			profesor.setCuil(request.getParameter("cuil"));
			profesor.setTel(request.getParameter("tel"));
			profesor.setFechaNac(LocalDate.parse(request.getParameter("date-birth")));
			profesor.setPassword(request.getParameter("password"));
			
			this.controlProfesor.add(profesor);
			String mensaje = "El profesor " + profesor.getNombre() +
					" " + profesor.getApellido() + " ha sido agregado correctamente!";
			request.setAttribute("msg", mensaje);
			request.getRequestDispatcher("/Admin/controllerAdmin?option=profesores").forward(request, response);
		} catch (AppException e) {
			request.setAttribute("validaciones", e.getMessage());
			request.getRequestDispatcher(request.getContextPath()+"/Admin/controllerAdmin?option=agregar-profesor").forward(request, response);			
		} catch (Exception e) {
			request.setAttribute("validaciones", "Ocurrió un error inesperado.");
			request.getRequestDispatcher(request.getContextPath()+"/Admin/controllerAdmin?option=agregar-profesor").forward(request, response);
		}
		}

}
