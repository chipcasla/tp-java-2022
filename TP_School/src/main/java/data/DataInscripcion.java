package data;

import java.sql.*;
import entities.*;
import java.time.*;
import java.util.LinkedList;

public class DataInscripcion {
	
	public void setUltimaInscripcion(Alumno alu) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Inscripcion i;
		DataCurso dc = new DataCurso();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(			
					  "select i.idAlumno, i.id_curso,i.id_modalidad,i.fechaInscripcion "
					+ "from inscripcion i "
					+ "inner join ( "
					+ "select max(ins.fechaInscripcion) ult_fecha "
					+ "from inscripcion ins "
					+ "group by ins.idAlumno) as ultIns "
					+ "on ultIns.ult_fecha=i.fechaInscripcion "
					+ "where i.idAlumno = ?"
					);
			stmt.setInt(1, alu.getIdAlumno());
			rs= stmt.executeQuery();
			if(rs!=null && rs.next()) {
				Curso c = new Curso();
				c.setIdCurso(rs.getInt("id_curso"));
				c.setIdModalidad(rs.getInt("id_modalidad"));
				c = dc.getById(c);
				i = new Inscripcion(alu,c,rs.getObject("fechaInscripcion", LocalDate.class));
				alu.setUltInscripcion(i);
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
		
	}
	
	
	
	public LinkedList<Inscripcion> getInscripciones(Alumno alu) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Inscripcion> inscripciones = new LinkedList<>();
		Inscripcion i = null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(			
					  "select i.idAlumno,i.id_curso,c.nombre nombre_curso,i.id_modalidad,m.nombre nombre_mod,i.fechaInscripcion "
					+ "from inscripcion i "
					+ "inner join curso c "
					+ "on c.id = i.id_curso "
					+ "inner join modalidad m "
					+ "on m.id = i.id_modalidad "
					+ "where i.idAlumno=? "
					+ "order by i.fechaInscripcion;"
					);
			stmt.setInt(1, alu.getIdAlumno());
			rs= stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Curso c = new Curso(rs.getInt("id_curso"), rs.getString("nombre_curso"),rs.getInt("id_modalidad"), rs.getString("nombre_mod"));
					i = new Inscripcion(alu,c,rs.getObject("fechaInscripcion", LocalDate.class));
					inscripciones.add(i);
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
		
		return inscripciones;
		
	}
	
	public void add(Inscripcion i) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into inscripcion(idAlumno, id_curso, id_modalidad, fechaInscripcion) "
							+ "values(?,?,?,?)"
							);
			stmt.setInt(1, i.getAlumno().getIdAlumno());
			stmt.setInt(2, i.getCurso().getIdCurso());
			stmt.setInt(3, i.getCurso().getIdModalidad());
			stmt.setObject(4, i.getFechaInscripcion());

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
	
	public void updateInscripcion(Inscripcion i) {
		PreparedStatement stmt = null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update inscripcion set id_curso=?, id_modalidad=?, fechaInscripcion=? "
							+ "where idAlumno=? and fechaInscripcion=?");
			stmt.setInt(1, i.getCurso().getIdCurso());
			stmt.setInt(2, i.getCurso().getIdModalidad());
			stmt.setObject(3, i.getFechaInscripcion());
			stmt.setInt(4, i.getAlumno().getIdAlumno());
			stmt.setObject(5, i.getAlumno().getUltInscripcion().getFechaInscripcion());
			
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
	
	public void delete(Inscripcion i) {
		PreparedStatement ps = null;
		try {
			
			ps = DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from inscripcion where idAlumno=? and fechaInscripcion=?"
							);
			
			ps.setInt(1, i.getAlumno().getIdAlumno());
			ps.setObject(2, i.getFechaInscripcion());
			
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
