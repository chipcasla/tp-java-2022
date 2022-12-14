package data;

import java.sql.*;
import java.util.LinkedList;

import entities.*;

public class DataNota {
	
	public LinkedList<Nota> getMisNotas(Alumno alu, Integer idCur, Integer idMod) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Nota> boletin = new LinkedList<>();
		Nota n = null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(			
					  "select i.id_curso,c.nombre nombre_curso,i.id_modalidad, m.nombre nombre_mod, mat.id id_mat,mat.nombre mat_nombre "
					+ ", n.notaFinal,n.nota1,n.nota2,n.nota3 "
					+ "from inscripcion i "
					+ "inner join curso c "
					+ "on c.id = i.id_curso "
					+ "inner join modalidad m "
					+ "on m.id = i.id_modalidad "
					+ "inner join asignatura asig "
					+ "on asig.idCurso = i.id_curso "
					+ "and asig.idModalidad = i.id_modalidad "
					+ "inner join materia mat "
					+ "on mat.id = asig.idMateria "
					+ "left join nota n "
					+ "on n.idAlumno = i.idAlumno "
					+ "and n.idCurso = asig.idCurso "
					+ "and n.idModalidad = asig.idModalidad "
					+ "and n.idMateria = asig.idMateria "
					+ "where i.idAlumno = ? and i.id_curso = ? and i.id_modalidad = ? "
					+ "order by i.id_curso,i.id_modalidad,mat.id;"
					);
			stmt.setInt(1, alu.getIdAlumno());
			stmt.setInt(2, idCur);
			stmt.setInt(3, idMod);
			rs= stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Curso c = new Curso(rs.getInt("id_curso"), rs.getString("nombre_curso"),rs.getInt("id_modalidad"), rs.getString("nombre_mod"));
					Materia mat = new Materia(rs.getInt("id_mat"), rs.getString("mat_nombre"));
					Asignatura asig = new Asignatura(c, mat);
					n = new Nota(alu,asig);
					n.setNotaFinal(rs.getInt("notaFinal"));
					n.setNota1C(rs.getInt("nota1"));
					n.setNota2C(rs.getInt("nota2"));
					n.setNota3C(rs.getInt("nota3"));
					boletin.add(n);
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
		
		return boletin;
		
	}

}
