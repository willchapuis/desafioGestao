package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.DAO;
import model.bean.EspacoCafe;

public class EspacoCafeDAO_OLD extends DAO<EspacoCafe> {

	@Override
	public boolean inserir(EspacoCafe element) {
		String sql = "INSERT INTO espaco_cafe (nome) values (?);";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, element.getNome());
            
            if(stmt.executeUpdate()==1){
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                element.setIdEspacoCafe(rs.getInt(1));
                return true;
            }
        }catch(SQLException e){
            System.out.println("erro ao inserir: "+e.getMessage());
        }
        return false;
	}

	@Override
	public boolean alterar(EspacoCafe element) {
		throw new UnsupportedOperationException("Sem suporte ainda.");
	}

	@Override
	public boolean remover(EspacoCafe element) {
		throw new UnsupportedOperationException("Sem suporte ainda.");
	}

	@Override
	public List<EspacoCafe> listar() {
		List<EspacoCafe> lstEspacos = new ArrayList<>();
        //lstPessoas = ObservableCollections.observableList(lstPessoas);
        
        String sql = "SELECT * FROM espaco_cafe;";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
            	EspacoCafe e = new EspacoCafe();
                e.setIdEspacoCafe(rs.getInt("id_espaco_cafe"));
                e.setNome(rs.getString("nome"));
                lstEspacos.add(e);
            }
        }catch(SQLException e){
            System.out.println("erro ao listar: "+e.getMessage());
        }
        return lstEspacos;
	}

}
