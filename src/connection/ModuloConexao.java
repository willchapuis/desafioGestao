package connection;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModuloConexao {
	
	//protected static Connection conn = null;
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/dbgestao";
	private static final String user = "root";
	private static final String pass = "";
    
    //public abstract boolean inserir(E element);
    //public abstract boolean alterar(E element);
    //public abstract boolean remover(E element);
    //public abstract List<E> listar();
	
	public static Connection getConnection() {
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			System.out.println("erro ao conectar com o banco: "+e.getMessage());
			return null;
		}
	}
	
	public static void closeConnection(Connection con) {
		try {
			if(con != null) {
				con.close();
			}
		} catch (Exception e) {
			Logger.getLogger(ModuloConexao.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public static void closeConnection(Connection con, PreparedStatement stmt) {
		closeConnection(con);
		
		try {
			if(stmt != null) {
				stmt.close();
			}
		} catch (Exception e) {
			Logger.getLogger(ModuloConexao.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
		closeConnection(con, stmt);
		
		try {
			if(rs!= null) {
				rs.close();
			}
		} catch (Exception e) {
			Logger.getLogger(ModuloConexao.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
