package data;

import java.sql.*;
import entities.*;
import java.time.*;
import java.util.LinkedList;

public class DataPersona {
	
	public LinkedList<Alumno> getAllStudents(){
		Statement stmt=null;
		ResultSet rs=null;
		DataInscripcion di=new DataInscripcion();
		LinkedList<Alumno> alums= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery
					 ("select id,legajo,nombre,apellido,dni,fechaNacimiento,mail,tel,regular "
					+ "from persona "
					+ "inner join alumno on persona.id=alumno.idAlumno "
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
	
	public LinkedList<Profesor> getAllTeachers(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Profesor> pers= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery
					 ("select id,cuil,nombre,apellido,dni,fechaNacimiento,mail,tel "
					+ "from persona "
					+ "where tipo=\"profesor\" "
					+ "order by apellido,nombre;");
			
			if(rs!=null) {
				while(rs.next()) {
					Profesor p=new Profesor();
					p.setId(rs.getInt("id"));
					p.setCuil(rs.getString("cuil"));
					p.setNombre(rs.getString("nombre"));
					p.setApellido(rs.getString("apellido"));
					p.setDni(rs.getString("dni"));
					p.setFechaNac(rs.getObject("fechaNacimiento",LocalDate.class));
					p.setMail(rs.getString("mail"));
					p.setTel(rs.getString("tel"));
					pers.add(p);
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
		
		return pers;
	}
	
	public Persona getByUser(Persona per) {
		DataInscripcion di=new DataInscripcion();
		Persona p=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					  "select id,legajo,nombre,apellido,dni,fechaNacimiento,mail,tel,regular,tipo "
					+ "from persona as p "
					+ "left join alumno as a on p.id=a.idAlumno "
					+ "where mail=? and contrasena=?"
					);
			stmt.setString(1, per.getMail());
			stmt.setString(2, per.getPassword());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				if(rs.getString("tipo").equals("alumno")) {
					p=new Alumno();
					((Alumno) p).setIdAlumno(rs.getInt("id"));
					((Alumno) p).setLegajo(rs.getInt("legajo"));
					((Alumno) p).setDni(rs.getString("dni"));
					((Alumno) p).setFechaNac(rs.getObject("fechaNacimiento",LocalDate.class));
					((Alumno) p).setTel(rs.getString("tel"));
					((Alumno) p).setRegular(rs.getBoolean("regular"));
					//
					di.setUltimaInscripcion((Alumno)p);
					
				} else if(rs.getString("tipo").equals("admin")) {
					p = new Persona();
					p.setId(rs.getInt("id"));
				}
				p.setNombre(rs.getString("nombre"));
				p.setApellido(rs.getString("apellido"));
				p.setMail(rs.getString("mail"));
				p.setTipo(rs.getString("tipo"));
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
					+ "from persona as p "
					+ "inner join alumno as a on p.id=a.idAlumno "
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
					+ "from persona as p "
					+ "inner join alumno as a on p.id=a.idAlumno "
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
	
	
	public Profesor getProfesorById(Profesor profe) {
		Profesor p = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					  "select id,nombre,apellido,dni,fechaNacimiento,mail,tel,cuil "
					+ "from persona as p "
					+ "where id=? and tipo=\"profesor\";"
					);
			stmt.setInt(1, profe.getId());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				p = new Profesor();
				p.setId(rs.getInt("id"));
				p.setCuil(rs.getString("cuil"));
				p.setNombre(rs.getString("nombre"));
				p.setApellido(rs.getString("apellido"));
				p.setDni(rs.getString("dni"));
				p.setFechaNac(rs.getObject("fechaNacimiento",LocalDate.class));
				p.setMail(rs.getString("mail"));
				p.setTel(rs.getString("tel"));
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
	
	public void add(Alumno a) {
		PreparedStatement stmt=null,stmt2= null;
		Connection conexion = null;
		ResultSet keyResultSet=null,keyResultSet2=null;
		try {
			conexion=DbConnector.getInstancia().getConn();
			//desactivo auto-commit
			conexion.setAutoCommit(false); 
			
			// Crear Persona
			stmt=conexion.prepareStatement(
							"insert into persona(nombre, apellido, dni, fechaNacimiento, mail, tel,contrasena,tipo)"
							+ " values(?,?,?,?,?,?,?,?);",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, a.getNombre());
			stmt.setString(2, a.getApellido());
			stmt.setString(3, a.getDni());
			stmt.setObject(4, a.getFechaNac());
			stmt.setString(5, a.getMail());
			stmt.setString(6, a.getTel());
			stmt.setString(7, a.getPassword());
			stmt.setString(8, "alumno");
			
			stmt.executeUpdate();
			
			// Recuperar el id de la persona creada
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                a.setId(keyResultSet.getInt(1));
            }
            
            // Crear alumno con el mismo id recuperado
            stmt2 = conexion.prepareStatement(
					"insert into alumno(idAlumno, regular)"
					+ " values(?,?);",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
            stmt2.setInt(1, a.getId());
			stmt2.setBoolean(2, true);
            
            stmt2.executeUpdate();
            
            //Recuperar legajo generado
            keyResultSet2=stmt2.getGeneratedKeys();
            if(keyResultSet2!=null && keyResultSet2.next()){
                a.setLegajo(keyResultSet2.getInt(1));
            }

			conexion.commit(); // reflejar operaciones en base de datos
            
		}  catch (SQLException e) {
			e.printStackTrace();
			
			if(conexion != null) {
				try {
					conexion.rollback(); //deshacer operaciones
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
            
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                if(keyResultSet2!=null)keyResultSet2.close();
                if(stmt2!=null)stmt2.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		
    }
	
	public void addProfesor(Profesor p) {
		PreparedStatement stmt=null;
		Connection conexion = null;
		ResultSet keyResultSet=null;
		try {
			conexion=DbConnector.getInstancia().getConn();
			
			// Crear Profesor
			stmt=conexion.prepareStatement(
							"insert into persona(nombre, apellido, dni, fechaNacimiento, mail, tel,contrasena,tipo,cuil)"
							+ " values(?,?,?,?,?,?,?,?,?);",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			
			stmt.setString(1, p.getNombre());
			stmt.setString(2, p.getApellido());
			stmt.setString(3, p.getDni());
			stmt.setObject(4, p.getFechaNac());
			stmt.setString(5, p.getMail());
			stmt.setString(6, p.getTel());
			stmt.setString(7, p.getPassword());
			stmt.setString(8, "profesor");
			stmt.setString(9, p.getCuil());
			
			stmt.executeUpdate();
			
			// Recuperar el id de la persona creada
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                p.setId(keyResultSet.getInt(1));
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
							"update persona inner join alumno on persona.id=? "
							+ "set nombre=?, apellido=?, dni=?, fechaNacimiento=?, mail=?, tel=?, regular=? "
							+ "where id=?");

			stmt.setInt(1, a.getIdAlumno());
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
	
	public void updateProfesor(Profesor p) {
		PreparedStatement stmt = null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update persona "
							+ "set nombre=?, apellido=?, dni=?, fechaNacimiento=?, mail=?, tel=?, cuil=? "
							+ "where id=?");
			stmt.setString(1, p.getNombre());
			stmt.setString(2, p.getApellido());
			stmt.setString(3, p.getDni());
			stmt.setObject(4, p.getFechaNac());
			stmt.setString(5, p.getMail());
			stmt.setString(6, p.getTel());
			stmt.setString(7, p.getCuil());
			stmt.setInt(8, p.getId());
			
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
							"delete from persona where id=?"
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
	
	public void deleteProfesor(Profesor p) {
		PreparedStatement ps = null;
		try {
			
			ps = DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from persona where id=?"
							);
			
			ps.setInt(1, p.getId());
			
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
