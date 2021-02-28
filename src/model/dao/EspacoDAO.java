package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.ModuloConexao;
import model.bean.EspacoCafe;

public class EspacoDAO {
	
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	private String sql;
	
	public void salvar(EspacoCafe espaco1, EspacoCafe espaco2) {
		Integer numEspacos = getNumEspacos();
		
		if (numEspacos == 0) {
			
			inserir(espaco1);
			inserir(espaco2);
			
		} else if (numEspacos == 1) {
			
			alterar(espaco1);
			inserir(espaco2);
			
		} else {
			
			alterar(espaco1);
			alterar(espaco2);
			
			remover();
			
		}
	}
	
	private void inserir(EspacoCafe espaco) {
		sql = "insert into espaco_cafe (nome) values (?)";
		
		conn = ModuloConexao.getConnection();
		
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, espaco.getNome());
			
			stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("erro ao inserir espaco no banco: "+e.getMessage());
		} finally {
			ModuloConexao.closeConnection(conn, stmt);
		}
	}
	
	private void alterar(EspacoCafe espaco) {
		sql = "update espaco_cafe set nome = ? where id_espaco_cafe = ?";
		
		conn = ModuloConexao.getConnection();
		
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, espaco.getNome());
			stmt.setString(2, espaco.getIdEspacoCafe().toString());
			
			stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("erro ao alterar espaco no banco: "+e.getMessage());
		} finally {
			ModuloConexao.closeConnection(conn, stmt);
		}
	}
	
	private void remover() {
		sql = "DELETE FROM espaco_cafe WHERE id_espaco_cafe > (SELECT MIN(id_espaco_cafe) FROM espaco_cafe WHERE id_espaco_cafe > (SELECT MIN(id_espaco_cafe) FROM espaco_cafe));";
		
		conn = ModuloConexao.getConnection();
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("erro ao deletar espacos sem suporte da tabela espaco_cafe: "+e.getMessage());
		} finally {
			ModuloConexao.closeConnection(conn, stmt, rs);
		}
	}
	
	public Integer getNumEspacos() {
		Integer r = 0;
		sql = "SELECT count(*) over () total_rows FROM espaco_cafe;";
		
		conn = ModuloConexao.getConnection();
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				r = Integer.parseInt(rs.getString(1));
			}
		} catch (Exception e) {
			System.out.println("erro ao consultar o numero de linhas da tabela espaco_cafe: "+e.getMessage());
		} finally {
			ModuloConexao.closeConnection(conn, stmt, rs);
		}
		
		return r;
	}
}
