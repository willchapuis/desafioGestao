package connection;

import java.sql.*;

public class ModuloConexao {

	public static Connection conector() {
		Connection conn = null;
		String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/dbgestao";
        String user = "root";
        String pass = "";
        try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pass);
			return conn;
		} catch (Exception e) {
			System.out.println("erro ao conectar com o banco: "+e.getMessage());
			return null;
		}
	}
}
