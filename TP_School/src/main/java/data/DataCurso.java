package data;

import java.sql.*;
import java.util.LinkedList;

import entities.*;

public class DataCurso {
	
	public Curso getById(Curso cur) {
		Curso c=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					  "select idCurso,c.nombre nombreC,idModalidad,m.nombre nombreM,activo "
					+ "from curso_modalidad cm "
					+ "inner join curso c "
					+ "on cm.idCurso=c.id "
					+ "inner join modalidad m "
					+ "on cm.idModalidad = m.id "
					+ "where idCurso=? and idModalidad=? "					
					);
			stmt.setInt(1, cur.getIdCurso());
			stmt.setInt(2, cur.getIdModalidad());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				c = new Curso();
				c.setIdCurso(rs.getInt("idCurso"));
				c.setNombre(rs.getString("nombreC"));
				c.setIdModalidad(rs.getInt("idModalidad"));
				c.setModalidad(rs.getString("nombreM"));
				c.setActivo(rs.getInt("activo"));
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
		
		return c;
	}
	
	public Curso getCursoById(Curso cur) {
		Curso c=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					  "select idCurso,c.nombre nombreC,idModalidad,m.nombre nombreM,activo "
					+ "from curso_modalidad cm "
					+ "inner join curso c "
					+ "on cm.idCurso=c.id "
					+ "inner join modalidad m "
					+ "on cm.idModalidad = m.id "
					+ "where idCurso=? "					
					);
			stmt.setInt(1, cur.getIdCurso());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				c = new Curso();
				c.setIdCurso(rs.getInt("idCurso"));
				c.setNombre(rs.getString("nombreC"));
				c.setIdModalidad(rs.getInt("idModalidad"));
				c.setModalidad(rs.getString("nombreM"));
				c.setActivo(rs.getInt("activo"));
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
		
		return c;
	}
	
	public Curso getModById(Curso mod) {
		Curso c=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					  "select id, nombre "
					+ "from modalidad m "
					+ "where id=? "					
					);
			stmt.setInt(1, mod.getIdModalidad());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				c = new Curso();
				c.setIdModalidad(rs.getInt("id"));
				c.setModalidad(rs.getString("nombre"));
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		
		return c;
	}
	
	public Curso getByName(Curso cur) {
		Curso c=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					  "select idCurso,c.nombre nombreC,idModalidad,m.nombre nombreM,activo "
					+ "from curso_modalidad cm "
					+ "inner join curso c "
					+ "on cm.idCurso=c.id "
					+ "inner join modalidad m "
					+ "on cm.idModalidad = m.id "
					+ "where c.nombre=? and m.nombre=? "					
					);
			stmt.setString(1, cur.getNombre());
			stmt.setString(2, cur.getModalidad());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				c = new Curso();
				c.setIdCurso(rs.getInt("idCurso"));
				c.setNombre(rs.getString("nombreC"));
				c.setIdModalidad(rs.getInt("idModalidad"));
				c.setModalidad(rs.getString("nombreM"));
				c.setActivo(rs.getInt("activo"));
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
		
		return c;
	}
	
	public LinkedList<Curso> getCursosDisp(Alumno alu) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Curso> cursos = new LinkedList<>();
		Curso c = null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(			
					  "select c.id,c.nombre nombre_curso "
					+ "from curso c "
					+ "where c.id >= ? "
					+ "order by c.id "
					+ "limit 2;"
					);
			stmt.setInt(1, alu.getUltInscripcion().getCurso().getIdCurso());
			rs= stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					c = new Curso();
					c.setIdCurso(rs.getInt("id"));
					c.setNombre(rs.getString("nombre_curso"));
					cursos.add(c);
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
		
		return cursos;
		
	}
	
	public LinkedList<Curso> getAll() {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Curso> cursos = new LinkedList<>();
		Curso c = null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(			
					  "select c.id,c.nombre nombre_curso "
					+ "from curso c "
					+ "order by c.id;"
					);
			rs= stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					c = new Curso();
					c.setIdCurso(rs.getInt("id"));
					c.setNombre(rs.getString("nombre_curso"));
					cursos.add(c);
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
		
		return cursos;
		
	}
	
	public LinkedList<Curso> getMisMods(Curso miCurso) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Curso> cursos = new LinkedList<>();
		Curso c = null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(			
					  "select cm.idModalidad, m.nombre nombre_mod "
					+ "from curso_modalidad cm "
					+ "inner join modalidad m "
					+ "  on cm.idModalidad=m.id "
					+ "where cm.idCurso = ? and activo=1 "
					+ "order by cm.idModalidad;"
					);
			
			stmt.setInt(1, miCurso.getIdCurso());
			rs= stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					c = new Curso();
					c.setIdCurso(miCurso.getIdCurso());
					c.setIdModalidad(rs.getInt("idModalidad"));
					c.setModalidad(rs.getString("nombre_mod"));
					cursos.add(c);
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
		
		return cursos;
		
	}
	
	public LinkedList<Curso> getModalidades() {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Curso> cursos = new LinkedList<>();
		Curso c = null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(			
					  "select m.id, m.nombre "
					+ "from modalidad m; "
					);
			rs= stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					c = new Curso();
					c.setIdModalidad(rs.getInt("id"));
					c.setModalidad(rs.getString("nombre"));
					cursos.add(c);
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
		
		return cursos;
		
	}
	
	public boolean tieneCursosActivos(Curso cur) {
		Curso c=null;
		LinkedList<Curso> cursosActivos = new LinkedList<>();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					  "select idCurso,c.nombre nombreC,idModalidad,m.nombre nombreM,activo "
					+ "from curso_modalidad cm "
					+ "inner join curso c "
					+ "on cm.idCurso=c.id "
					+ "inner join modalidad m "
					+ "on cm.idModalidad = m.id "
					+ "where idModalidad=? and activo=1;"					
					);
			stmt.setInt(1, cur.getIdModalidad());
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					c = new Curso();
					c.setIdCurso(rs.getInt("idCurso"));
					c.setIdModalidad(rs.getInt("idModalidad"));
					c.setNombre(rs.getString("nombreC"));
					c.setModalidad(rs.getString("nombreM"));
					cursosActivos.add(c);
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
		
		return !cursosActivos.isEmpty();
	}
	
	public void addCurso(Curso c, LinkedList<Curso> modalidades) {
		PreparedStatement stmt= null, stmt2= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into curso(id, nombre) values(?,?);"
							);
			stmt.setInt(1, c.getIdCurso());
			stmt.setString(2, c.getNombre());
			
			stmt.executeUpdate();
			
			if(!modalidades.isEmpty()) {
				for (Curso mod : modalidades) {
					stmt2=DbConnector.getInstancia().getConn().
							prepareStatement(
									"insert into curso_modalidad(idCurso, idModalidad) values(?,?);"
									);
					stmt2.setInt(1, c.getIdCurso());
					stmt2.setInt(2, mod.getIdModalidad());
					
					stmt2.executeUpdate();
				}
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
    }
	
	public Curso addModalidad(Curso m) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into modalidad(nombre) values(?);",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, m.getModalidad());
			
			stmt.executeUpdate();
			
			// Recuperar el id de la modalidad creada
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                m.setIdModalidad(keyResultSet.getInt(1));
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
	
	public void addCursoModalidad(Curso c) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into curso_modalidad(idCurso, idModalidad, activo) "
							+ "values(?,?,1);"
							);
			stmt.setInt(1, c.getIdCurso());
			stmt.setInt(2, c.getIdModalidad());
			
			stmt.executeUpdate();
			
		}  catch (SQLException e) {
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
	
	public void updateModalidad(Curso m) {
		PreparedStatement stmt = null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update modalidad "
							+ "set nombre=? "
							+ "where id=?");
			stmt.setString(1, m.getModalidad());
			stmt.setInt(2, m.getIdModalidad());
			
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
	
public void darAltaCursoMod(Curso c) {
		
		PreparedStatement stmt = null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update curso_modalidad set activo=1 "
							+ "where idCurso=? and idModalidad=?");
			stmt.setInt(1, c.getIdCurso());
			stmt.setInt(2, c.getIdModalidad());
			
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
	
	public void darBajaCursoMod(Curso c) {
		
		PreparedStatement stmt = null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update curso_modalidad set activo=0 "
							+ "where idCurso=? and idModalidad=?");
			stmt.setInt(1, c.getIdCurso());
			stmt.setInt(2, c.getIdModalidad());
			
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
	
	public void deleteModalidad(Curso c) {
		PreparedStatement ps = null;
		try {
			
			ps = DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from modalidad where id=?"
							);
			
			ps.setInt(1, c.getIdModalidad());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null) ps.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
