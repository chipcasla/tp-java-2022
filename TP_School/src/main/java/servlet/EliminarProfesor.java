package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Profesor;
import logic.ProfesorABMC;

/**
 * Servlet implementation class EliminarProfesor
 */
@WebServlet("/Admin/EliminarProfesor")
public class EliminarProfesor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProfesorABMC controlProfesor = new ProfesorABMC();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarProfesor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Profesor p = new Profesor();
			p.setId(Integer.parseInt(request.getParameter("id")));
			
			controlProfesor.dltProfesor(p);
			
			String mensaje = "Profesor eliminado correctamente";
			request.setAttribute("msg", mensaje);
			request.getRequestDispatcher("/Admin/controllerAdmin?option=profesores").forward(request, response);
		} 
		catch(Exception e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);			

		}
		
	}

}
