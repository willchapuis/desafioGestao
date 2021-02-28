package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import connection.ModuloConexao;
import model.bean.Pessoa;

public class PessoaDAO {
	
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	private String sql;

	public void inserir(Pessoa p) {
		sql = "insert into pessoa (nome, sobrenome, id_sala_etapa1, id_sala_etapa2, id_espaco) values (?, ?, ?, ?, ?)";
		
		conn = ModuloConexao.getConnection();
		
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, p.getNome());
			stmt.setString(2, p.getSobrenome());
			stmt.setInt(3, p.getIdSalaEtapa1());
			stmt.setInt(4, p.getIdSalaEtapa2());
			stmt.setInt(5, p.getIdEspaco());
			
			Integer adicionado = stmt.executeUpdate();
			if (adicionado > 0) {
				JOptionPane.showMessageDialog(null, "Pessoa adicionada com sucesso");
			}
		} catch (Exception e) {
			System.out.println("erro ao adicionar: "+e.getMessage());
		} finally {
			ModuloConexao.closeConnection(conn, stmt);
		}
	}
	
	public Integer getNumRows(String table) {
		Integer r = 0;
		sql = "SELECT count(*) over () total_rows FROM "+ table + ";";
		
		conn = ModuloConexao.getConnection();
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				r = Integer.parseInt(rs.getString(1));
			}
		} catch (Exception e) {
			System.out.println("erro ao consultar o numero de linhas da tabela " + table + ": "+e.getMessage());
		} finally {
			ModuloConexao.closeConnection(conn, stmt, rs);
		}
		
		return r;
	}
	
	public Pessoa getPessoa(Integer id) {
		Pessoa p = new Pessoa();
		sql = "SELECT * FROM pessoa WHERE id_pessoa = ?";
		
		conn = ModuloConexao.getConnection();
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id.toString());
			rs = stmt.executeQuery();
			if (rs.next()) {
				p.setIdPessoa(Integer.parseInt(rs.getString(1)));
				p.setNome(rs.getString(2));
				p.setSobrenome(rs.getString(3));
				p.setIdSalaEtapa1(Integer.parseInt(rs.getString(4)));
				p.setIdSalaEtapa2(Integer.parseInt(rs.getString(5)));
				p.setIdEspaco(Integer.parseInt(rs.getString(6)));
			} else {
				System.out.println("erro ao pegar pessoa do banco em PessoaDAO.getPessoa(Integer id)");
			}
		} catch (Exception e) {
			System.out.println("erro ao consultar o consultar informacao da tabela pessoa no banco: "+e.getMessage());
		} finally {
			ModuloConexao.closeConnection(conn, stmt, rs);
		}
		
		return p;
	}
}
