package data;

import java.sql.*;
import entities.*;
import java.time.*;
import java.util.LinkedList;

public class DataPersona {
	
	public LinkedList<Alumno> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		DataInscripcion di=new DataInscripcion();
		LinkedList<Alumno> alums= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery
					 ("select id,legajo,nombre,apellido,dni,fechaNacimiento,mail,tel,regular "
					+ "from alumno "
					+ "where tipo=\"alumno\";");
			
			if(rs!=null) {
				while(rs.next()) {
					Alumno a=new Alumno();
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
					alums.add(a);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return alums;
	}
	
	
	public Persona getByUser(Persona per) {
		DataInscripcion di=new DataInscripcion();
		Persona p=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					  "select id,legajo,nombre,apellido,dni,fechaNacimiento,mail,tel,regular,tipo "
					+ "from alumno "
					+ "where mail=? and contrasena=?"
					);
			stmt.setString(1, per.getMail());
			stmt.setString(2, per.getPassword());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				if(rs.getString("tipo").equals("alumno")) {
					p=new Alumno(rs.getInt("id"), rs.getInt("legajo"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("dni"), rs.getObject("fechaNacimiento",LocalDate.class), rs.getString("mail"), rs.getString("tel"));
					//
					di.setUltimaInscripcion((Alumno)p);
					
				} else if(rs.getString("tipo").equals("admin")) {
					p = new Persona();
				}
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				p.setApellido(rs.getString("apellido"));
				p.setMail(rs.getString("mail"));
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
		
		return p;
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
	
	public Alumno getById(Alumno alu) {
		Alumno a=null;
		DataInscripcion di = new DataInscripcion();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					  "select id,legajo,nombre,apellido,dni,fechaNacimiento,mail,tel,regular "
					+ "from alumno "
					+ "where id=?"
					);
			stmt.setInt(1, alu.getIdAlumno());
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
							"insert into alumno(legajo, nombre, apellido, dni, fechaNacimiento, mail, tel, regular,contrasena,tipo)"
							+ " select max(legajo) +1,?,?,?,?,?,?,?,?,? from alumno;",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			
			stmt.setString(1, a.getNombre());
			stmt.setString(2, a.getApellido());
			stmt.setString(3, a.getDni());
			stmt.setObject(4, a.getFechaNac());
			stmt.setString(5, a.getMail());
			stmt.setString(6, a.getTel());
			stmt.setBoolean(7, true);
			stmt.setString(8, a.getPassword());
			stmt.setString(9, "alumno");
			
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
