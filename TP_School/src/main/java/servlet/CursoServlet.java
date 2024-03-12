package servlet;

import java.io.IOException;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import entities.Curso;
import logic.ControlCursos;

/**
 * Servlet implementation class CursoServlet
 */
@WebServlet("/CursoServlet")
public class CursoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CursoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //el argumento
		Curso cursoSelected = new Curso();
		cursoSelected.setIdCurso(Integer.parseInt(request.getParameter("cur")));
        //la lista
        ControlCursos controlCursos = new ControlCursos();
        List<Curso> modList = controlCursos.getAllMods(cursoSelected);
        
        //de la misma manera, crearemos un arreglo json
        JsonArrayBuilder array = Json.createArrayBuilder();
        
        //esta vez recorremos la lista como lambda
        modList.stream().map((mod) -> Json.createObjectBuilder()
        		.add("modId", mod.getIdModalidad())
        		.add("modName", mod.getModalidad())
        		.build()).forEach((item) -> {
            array.add(item);
        });
        response.setContentType(MediaType.APPLICATION_JSON);//tipo application/json
        try (JsonWriter jsonWriter = Json.createWriter(response.getOutputStream())) {
            jsonWriter.writeArray(array.build());
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
