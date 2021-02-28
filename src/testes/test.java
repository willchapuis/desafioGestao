package testes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import connection.ModuloConexao;

public class test {
	
	static Connection conn = null;
	static ResultSet rs = null;
	static PreparedStatement stmt;
	
	public static void main(String[] args) {
		conn = ModuloConexao.getConnection();
		String sql = "SELECT count(*) over () total_rows FROM pessoa;";
		//String sql = "SELECT count(*) over () total_rows FROM sala_evento;";
		//String sql = "SELECT count(*) over () total_rows FROM espaco_cafe;";
		
		Integer num;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				num = Integer.parseInt(rs.getString(1));
			}
			else {
				num = 0;
			}
			System.out.println(num);
			
		} catch (Exception e) {
			System.out.println("erro ao consultar: "+e.getMessage());
		}
		
	}
}
