package data;

import java.sql.*;
import entities.*;

public class DataCurso {
	
	public Curso getById(Curso cur) {
		Curso c=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					  "select idCurso,c.nombre nombreC,idModalidad,m.nombre nombreM"
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
	/*
	public void add(Curso x) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into alumno(legajo, nombre, apellido, dni, fechaNacimiento, mail, tel, regular) values(?,?,?,?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, a.getLegajo());
			stmt.setString(2, a.getNombre());
			stmt.setString(3, a.getApellido());
			stmt.setString(4, a.getDni());
			stmt.setObject(5, a.getFechaNac());
			stmt.setString(6, a.getMail());
			stmt.setString(7, a.getTel());
			stmt.setBoolean(8, true);
			
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                a.setIdAlumno(keyResultSet.getInt(1));
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
	
	public void updatePerson(Alumno a) {
		PreparedStatement stmt = null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update alumno set legajo=?, nombre=?, apellido=?, dni=?, fechaNacimiento=?, mail=?, tel=?, regular=? "
							+ "where id=?");
			stmt.setInt(1, a.getLegajo());
			stmt.setString(2, a.getNombre());
			stmt.setString(3, a.getApellido());
			stmt.setString(4, a.getDni());
			stmt.setObject(5, a.getFechaNac());
			stmt.setString(6, a.getMail());
			stmt.setString(7, a.getTel());
			stmt.setBoolean(8, a.isRegular());
			stmt.setInt(9, a.getIdAlumno());
			
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
	
	public void delete(Alumno a) {
		PreparedStatement ps = null;
		try {
			
			ps = DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from alumno where id=?"
							);
			
			ps.setInt(1, a.getIdAlumno());
			
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
*/
}
