package data;

import java.sql.*; //tiene todos los objetos que vamos a usar para trabajar con la base de datos
import java.time.LocalDateTime;
import java.util.LinkedList;

import entities.Producto;

public class DbHandler {
	
	private String nombreBD = "javaMarket";
	private String usuario = "ale";
	private String password = "himitsu";
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String host = "localhost";
	private String port = "3306";
	//private String options = "";
	
	private Connection conn = null;
	
	
	public DbHandler() {
		// registrar el conector - objeto, driver de conexion que se va a registrar para hacer las conexiones a la bd
		try {
				Class.forName(driver);			
		} catch (ClassNotFoundException e) {
				e.printStackTrace();
		}	
	}
	
	private Connection getConnection() {
		
		try {
			
			conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+nombreBD, usuario, password);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	private void releaseConnection() {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	}
	
	public LinkedList<Producto> listar() {
		
		LinkedList<Producto> misProductos = new LinkedList<>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = this.getConnection();
			//ejecutar una query
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from product");
			//mapear del rs a objeto
			while (rs.next()) {
				Producto p = new Producto();
				
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("nombre"));
				p.setPrice(rs.getDouble("precio"));
				p.setDisabledOn(rs.getObject("desabilitar", LocalDateTime.class));
				
				misProductos.add(p);	
			}
			return misProductos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;		
		} finally {
			try {
				if(rs != null) {rs.close();};
				if(stmt != null) {stmt.close();};
				this.releaseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void nuevoProducto(Producto miP) {
		PreparedStatement pstmt = null;
		ResultSet rsKey = null;
		try {
			Connection conn = this.getConnection();

			pstmt = conn.prepareStatement("insert into product(nombre,descripcion,precio,stock,envioIncluido,desabilitar) "+
										  "values(?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1,miP.getName());
			pstmt.setString(2,miP.getDescription());
			pstmt.setDouble(3,miP.getPrice());
			pstmt.setInt(4,miP.getStock());
			pstmt.setBoolean(5,miP.isShippingIncluded());
			pstmt.setObject(6,miP.getDisabledOn());

			pstmt.executeUpdate();

			rsKey = pstmt.getGeneratedKeys();
			
			if(rsKey != null && rsKey.next()) {
				miP.setId(rsKey.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rsKey != null) {rsKey.close();};
				if(pstmt != null) {pstmt.close();};
				this.releaseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Producto buscar(Producto miProd) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Producto miP = null;
			Connection conn = this.getConnection();

			pstmt = conn.prepareStatement("select * from product where id = ?");
			pstmt.setInt(1,miProd.getId());
			
			rs = pstmt.executeQuery();			

			if (rs != null && rs.next()) {
				miP = new Producto();
				
				miP.setId(rs.getInt("id"));
				miP.setName(rs.getString("nombre"));
				miP.setDescription(rs.getString("descripcion"));
				miP.setPrice(rs.getDouble("precio"));
				miP.setStock(rs.getInt("stock"));
				miP.setShippingIncluded(rs.getBoolean("envioIncluido"));
				miP.setDisabledOn(rs.getObject("desabilitar", LocalDateTime.class));
				
			}
			return miP;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;		
		} finally {
			try {
				if(rs != null) {rs.close();};
				if(pstmt != null) {pstmt.close();};
				this.releaseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	
	public void borrar(Producto delP) {
		
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			
			conn = this.getConnection();
			stmt = conn.prepareStatement("delete from product where id = ?");
			stmt.setInt(1, delP.getId());
			
			stmt.executeUpdate();
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) {stmt.close();};
				this.releaseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void actualizar(Producto updP) {
		
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			
			conn = this.getConnection();
			ps = conn.prepareStatement("update product " +
						"set nombre = ?, descripcion = ?, precio = ?, stock = ?, envioIncluido = ?, desabilitar = ? " +
						 "where id = ?");
			
			ps.setString(1, updP.getName());
			ps.setString(2, updP.getDescription());
			ps.setDouble(3, updP.getPrice());
			ps.setInt(4, updP.getStock());
			ps.setBoolean(5, updP.isShippingIncluded());
			ps.setObject(6, updP.getDisabledOn());
			ps.setInt(7, updP.getId());
			
			ps.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {ps.close();};
				this.releaseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}











