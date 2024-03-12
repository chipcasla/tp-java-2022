package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;

import entities.Asignatura;
import entities.Curso;
import entities.Materia;
import entities.Profesor;

public class DataAsignatura {

	public LinkedList<Asignatura> getAsignaturasPorCurso(Asignatura a) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Asignatura> materias = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(			
					  "select c.id id_curso, c.nombre nombre_curso,m.id id_modalidad,"
					+ " m.nombre nombre_mod, mat.id id_mat,mat.nombre mat_nombre, p.* "
					+ "from asignatura a "
					+ "inner join curso c "
					+ "on c.id = a.idCurso "
					+ "inner join modalidad m "
					+ "on m.id = a.idModalidad "
					+ "inner join materia mat "
					+ "on mat.id = a.idMateria "
					+ "left join persona p on p.id = a.idProfesor "
					+ "where a.idCurso = ? and a.idModalidad = ? and a.activo=1 "
					+ "order by mat.id;"
					);
			stmt.setInt(1, a.getCurso().getIdCurso());
			stmt.setInt(2, a.getCurso().getIdModalidad());
			rs= stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Curso c = new Curso(rs.getInt("id_curso"), rs.getString("nombre_curso"),rs.getInt("id_modalidad"), rs.getString("nombre_mod"));
					Materia mat = new Materia(rs.getInt("id_mat"), rs.getString("mat_nombre"));
					Asignatura asig = new Asignatura(c, mat);
					Profesor profesor = new Profesor(rs.getInt("id"), 
							rs.getString("nombre"),
							rs.getString("apellido"),
							rs.getString("mail"),
							rs.getString("dni"),
							rs.getString("cuil"),
							rs.getString("tel"),
							rs.getObject("fechaNacimiento", LocalDate.class));
					asig.setProfesor(profesor);
					materias.add(asig);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return materias;
	}

	public LinkedList<Materia> getAllMaterias() {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Materia> materias = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(			
					  "select mat.id id_mat,mat.nombre mat_nombre "
					+ "from materia mat "
					+ "order by mat.id;"
					);
			rs= stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Materia mat = new Materia(rs.getInt("id_mat"), rs.getString("mat_nombre"));
					materias.add(mat);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return materias;
	}
	
	public LinkedList<Materia> getMateriasCurso(Asignatura a) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Materia> materias = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(			
					  "select mat.id id_mat,mat.nombre mat_nombre "
					+ "from asignatura a "
					+ "inner join materia mat "
					+ "on mat.id = a.idMateria "
					+ "where a.idCurso = ? and a.idModalidad = ? and a.activo=1 "
					+ "order by mat.id;"
					);
			stmt.setInt(1, a.getCurso().getIdCurso());
			stmt.setInt(2, a.getCurso().getIdModalidad());
			rs= stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Materia mat = new Materia(rs.getInt("id_mat"), rs.getString("mat_nombre"));
					materias.add(mat);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return materias;
	}

	public Materia addMateria(Materia m) {
		
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into materia(nombre) values(?);",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, m.getNombre());
			
			stmt.executeUpdate();
			
			// Recuperar el id de la modalidad creada
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                m.setIdMateria(keyResultSet.getInt(1));
            }
			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		
		return m;
		
	}

	public Materia getMateria(Materia materia) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Materia m = null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(			
					  "select id,nombre "
					+ "from materia "
					+ "where id=? ;"
					);
			stmt.setInt(1, materia.getIdMateria());
			rs= stmt.executeQuery();
			if(rs!=null && rs.next()) {
				m = new Materia(rs.getInt("id"), rs.getString("nombre"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return m;
	}

	public Asignatura getAsignatura(Asignatura a) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Asignatura asig = null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(			
					  "select c.id id_curso, c.nombre nombre_curso,m.id id_modalidad,"
					+ " m.nombre nombre_mod, mat.id id_mat,mat.nombre mat_nombre, a.activo, p.* "
					+ "from asignatura a "
					+ "inner join curso c "
					+ "on c.id = a.idCurso "
					+ "inner join modalidad m "
					+ "on m.id = a.idModalidad "
					+ "inner join materia mat "
					+ "on mat.id = a.idMateria "
					+ "left join persona p on p.id = a.idProfesor "
					+ "where a.idCurso = ? and a.idModalidad = ? and a.idMateria=? ;"
					);
			stmt.setInt(1, a.getCurso().getIdCurso());
			stmt.setInt(2, a.getCurso().getIdModalidad());
			stmt.setInt(3, a.getMateria().getIdMateria());
			rs= stmt.executeQuery();
			if(rs!=null && rs.next()) {
				Curso c = new Curso(rs.getInt("id_curso"), rs.getString("nombre_curso"),rs.getInt("id_modalidad"), rs.getString("nombre_mod"));
				Materia mat = new Materia(rs.getInt("id_mat"), rs.getString("mat_nombre"));
				asig = new Asignatura(c, mat);
				Profesor profesor = new Profesor(rs.getInt("id"), 
						rs.getString("nombre"),
						rs.getString("apellido"),
						rs.getString("mail"),
						rs.getString("dni"),
						rs.getString("cuil"),
						rs.getString("tel"),
						rs.getObject("fechaNacimiento", LocalDate.class));
				asig.setProfesor(profesor);
				asig.setActivo(rs.getInt("activo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return asig;
	}

	public void addAsignatura(Asignatura asig) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into asignatura(idCurso,idModalidad,idMateria) values(?,?,?);"
							);
			stmt.setInt(1, asig.getCurso().getIdCurso());
			stmt.setInt(2, asig.getCurso().getIdModalidad());
			stmt.setInt(3, asig.getMateria().getIdMateria());
			
			stmt.executeUpdate();
			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		
	}

	public void darDeAlta(Asignatura a) {
		
		PreparedStatement stmt = null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update asignatura set activo=1 "
							+ "where idCurso=? and idModalidad=? and idMateria=?;");
			stmt.setInt(1, a.getCurso().getIdCurso());
			stmt.setInt(2, a.getCurso().getIdModalidad());
			stmt.setInt(3, a.getMateria().getIdMateria());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
	        e.printStackTrace();
		} finally {
	        try {
	            if(stmt!=null)stmt.close();
	            DbConnector.getInstancia().releaseConn();
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
		}
		
	}
	
	public void darDeBaja(Asignatura a) {
		
		PreparedStatement stmt = null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update asignatura set activo=0 "
							+ "where idCurso=? and idModalidad=? and idMateria=?;");
			stmt.setInt(1, a.getCurso().getIdCurso());
			stmt.setInt(2, a.getCurso().getIdModalidad());
			stmt.setInt(3, a.getMateria().getIdMateria());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
	        e.printStackTrace();
		} finally {
	        try {
	            if(stmt!=null)stmt.close();
	            DbConnector.getInstancia().releaseConn();
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
		}
		
	}

	public void updateProfesor(Asignatura a) {
		
		PreparedStatement stmt = null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update asignatura set idProfesor=? "
							+ "where idCurso=? and idModalidad=? and idMateria=?;");
			stmt.setInt(1, a.getProfesor().getId());
			stmt.setInt(2, a.getCurso().getIdCurso());
			stmt.setInt(3, a.getCurso().getIdModalidad());
			stmt.setInt(4, a.getMateria().getIdMateria());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
	        e.printStackTrace();
		} finally {
	        try {
	            if(stmt!=null)stmt.close();
	            DbConnector.getInstancia().releaseConn();
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
		}
		
	}

}
