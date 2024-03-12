package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.AppException;
import entities.Asignatura;
import entities.Curso;
import entities.Materia;
import logic.ControlAsignatura;

/**
 * Servlet implementation class AgregarAsignatura
 */
@WebServlet("/Admin/AgregarAsignaturas")
public class AgregarAsignaturas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ControlAsignatura controlAsignatura = new ControlAsignatura();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarAsignaturas() {
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
		
		try {
			
            String[] materiasSeleccionadas = request.getParameterValues("materia");
            int idCurso = Integer.parseInt(request.getParameter("curso"));
            int idMod = Integer.parseInt(request.getParameter("mod"));
            Curso curso = new Curso();
            curso.setIdCurso(idCurso);
            curso.setIdModalidad(idMod);
            Asignatura asignatura = new Asignatura();
            asignatura.setCurso(curso);
            LinkedList<Materia> misMateriasAntes = controlAsignatura.misMaterias(asignatura);
            
            // Creo listas para almacenar las nuevas materias a agregar y las deseleccionadas para eliminar
            LinkedList<Materia> materiasNuevas = new LinkedList<>();
            LinkedList<Materia> materiasDeseleccionadas = new LinkedList<>(misMateriasAntes);

            // Itero sobre las materias seleccionadas del formulario
            for (String idMateria : materiasSeleccionadas) {
                int idMateriaInt = Integer.parseInt(idMateria);
                // Verifico si la materia seleccionada ya estaba en las materias anteriores
                boolean esNueva = true;
                for (Materia materia : misMateriasAntes) {
                    if (materia.getIdMateria() == idMateriaInt) {
                    	// Si la materia seleccionada ya estaba en las anteriores, la saco de la lista de deseleccionadas
                        esNueva = false;
                        materiasDeseleccionadas.remove(materia);
                        break;
                    }
                }
                // Si la materia seleccionada es nueva, la agrego a la lista de materias nuevas
                if (esNueva) {
                    Materia nuevaMateria = new Materia();
                    nuevaMateria.setIdMateria(idMateriaInt);
                    materiasNuevas.add(nuevaMateria);
                }
            }
            
            for (Materia m : materiasNuevas) {
            	Asignatura asig = new Asignatura();
            	asig.setCurso(curso);
            	asig.setMateria(m);            	
            	controlAsignatura.addAsignatura(asig);
            }
            
            for (Materia m : materiasDeseleccionadas) {
            	Asignatura asig = new Asignatura();
            	asig.setCurso(curso);
            	asig.setMateria(m);  
            	controlAsignatura.darDeBaja(asig);
            }
            
            response.sendRedirect("Materias?idCurso="+idCurso+"&idMod="+idMod);
        } catch (AppException e) {
        	request.setAttribute("validaciones2", e.getMessage());
            request.getRequestDispatcher("/Admin/GestionMaterias?idCurso="+request.getParameter("curso")+"&idMod="+request.getParameter("mod")).forward(request, response);
        } catch (Exception e) {
        	request.setAttribute("error", "Error inesperado. \n "+e.getMessage()+"\n"+e.getCause());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
		
	}

}
