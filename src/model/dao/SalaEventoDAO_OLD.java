package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connection.DAO;
import model.bean.SalaEvento;

public class SalaEventoDAO_OLD extends DAO<SalaEvento> {
	
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	@Override
	public boolean inserir(SalaEvento element) {
		String sql = "INSERT INTO sala_evento (nome,lotacao) values (?,?);";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, element.getNome());
            stmt.setInt(2, element.getLotacao());
            
            if(stmt.executeUpdate()==1){
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                element.setIdSalaEvento(rs.getInt(1));
                return true;
            }
        }catch(SQLException e){
            System.out.println("erro ao inserir: "+e.getMessage());
        }
        return false;
	}

	@Override
	public boolean alterar(SalaEvento element) {
		throw new UnsupportedOperationException("Sem suporte ainda.");
	}

	@Override
	public boolean remover(SalaEvento element) {
		throw new UnsupportedOperationException("Sem suporte ainda.");
	}

	@Override
	public List<SalaEvento> listar() {
		List<SalaEvento> lstSalas = new ArrayList<>();
        //lstPessoas = ObservableCollections.observableList(lstPessoas);
        
        String sql = "SELECT * FROM sala_evento;";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
            	SalaEvento s = new SalaEvento();
                s.setIdSalaEvento(rs.getInt("id_sala_evento"));
                s.setNome(rs.getString("nome"));
                s.setLotacao(rs.getInt("lotacao"));
                lstSalas.add(s);
            }
        }catch(SQLException e){
            System.out.println("erro ao listar: "+e.getMessage());
        }
        return lstSalas;
	}

}
