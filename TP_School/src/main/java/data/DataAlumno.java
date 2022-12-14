package data;

import java.sql.*;
import entities.*;
import java.time.*;

public class DataAlumno {
	
	public Alumno getByUser(Alumno alu) {
		DataInscripcion di=new DataInscripcion();
		Alumno a=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					  "select id,legajo,nombre,apellido,dni,fechaNacimiento,mail,tel,regular "
					+ "from alumno "
					+ "where mail=? and contrasena=?"
					);
			stmt.setString(1, alu.getMail());
			stmt.setString(2, alu.getPassword());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				a=new Alumno();
				a.setIdAlumno(rs.getInt("id"));
				a.setLegajo(rs.getInt("legajo"));
				a.setNombre(rs.getString("nombre"));
				a.setApellido(rs.getString("apellido"));
				a.setDni(rs.getString("dni"));
				a.setFechaNac(rs.getObject("fechaNacimiento",LocalDate.class));
				a.setMail(rs.getString("mail"));
				a.setTel(rs.getString("tel"));
				a.setRegular(rs.getBoolean("regular"));
				//
				di.setUltimaInscripcion(a);
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
		
		return a;
	}
	
	public Alumno getByDni(Alumno alu) {
		Alumno a=null;
		DataInscripcion di = new DataInscripcion();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					  "select id,legajo,nombre,apellido,dni,fechaNacimiento,mail,tel,regular "
					+ "from alumno "
					+ "where dni=?"
					);
			stmt.setString(1, alu.getDni());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				a = new Alumno();
				a.setIdAlumno(rs.getInt("id"));
				a.setLegajo(rs.getInt("legajo"));
				a.setNombre(rs.getString("nombre"));
				a.setApellido(rs.getString("apellido"));
				a.setDni(rs.getString("dni"));
				a.setFechaNac(rs.getObject("fechaNacimiento",LocalDate.class));
				a.setMail(rs.getString("mail"));
				a.setTel(rs.getString("tel"));
				a.setRegular(rs.getBoolean("regular"));
				di.setUltimaInscripcion(a);
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
		
		return a;
	}
	
	public void add(Alumno a) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into alumno(legajo, nombre, apellido, dni, fechaNacimiento, mail, tel, regular)"
							+ " values(?,?,?,?,?,?,?,?)",
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

}
