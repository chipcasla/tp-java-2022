package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.*;
import logic.*;

/**
 * Servlet implementation class Signin
 */
@WebServlet({ "/Signin", "/signin" })
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Login login = new Login();
       
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Persona p = new Persona();
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			p.setMail(email);
			p.setPassword(password);
			
			p = login.validate(p);
			HttpSession miSession = request.getSession(true);
			miSession.setAttribute("user", p);
			if(p.getClass()==Alumno.class) {
				request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
			} else if(p.getClass()==Persona.class) {
				request.getSession().setAttribute("admin-key", "soyadmin");
				request.getRequestDispatcher("Admin/controllerAdmin?option=main").forward(request, response);	
			}
			
		} catch (AppException e) {
			request.setAttribute("validaciones", e.getMessage());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("validaciones", "Ocurrió un error inesperado. \n"+e.getMessage());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
	}

}
