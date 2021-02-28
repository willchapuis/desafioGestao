package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.ModuloConexao;
import model.bean.SalaEvento;

public class SalaEventoDAO {
	
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	private String sql;
	
	public void inserir(SalaEvento sala) {
		sql = "insert into sala_evento (nome, lotacao) values (?, ?)";
		
		conn = ModuloConexao.getConnection();
		
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, sala.getNome());
			stmt.setInt(2, sala.getLotacao());
			
			stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("erro ao inserir sala no banco: "+e.getMessage());
		} finally {
			ModuloConexao.closeConnection(conn, stmt);
		}
	}
	
	public void remover(SalaEvento sala) {
		sql = "DELETE FROM sala_evento WHERE id_sala_evento = ?";
		
		conn = ModuloConexao.getConnection();
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sala.getIdSalaEvento().toString());
			
			stmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("erro ao deletar sala no banco: "+e.getMessage());
		} finally {
			ModuloConexao.closeConnection(conn, stmt, rs);
		}
	}
	
}
