package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.DAO;
import model.bean.Pessoa;

public class PessoaDAO_OLD extends DAO<Pessoa> {

	@Override
	public boolean inserir(Pessoa element) {
		String sql = "INSERT INTO pessoa (nome,sobrenome) values (?,?);";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, element.getNome());
            stmt.setString(2, element.getSobrenome());
            
            if(stmt.executeUpdate()==1){
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                element.setIdPessoa(rs.getInt(1));
                return true;
            }
        }catch(SQLException e){
            System.out.println("erro ao inserir: "+e.getMessage());
        }
        return false;
	}

	@Override
	public boolean alterar(Pessoa element) {
		throw new UnsupportedOperationException("Sem suporte ainda.");
	}

	@Override
	public boolean remover(Pessoa element) {
		throw new UnsupportedOperationException("Sem suporte ainda.");
	}

	@Override
	public List<Pessoa> listar() {
		List<Pessoa> lstPessoas = new ArrayList<>();
        
        String sql = "SELECT * FROM cliente;";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                Pessoa p = new Pessoa();
                p.setIdPessoa(rs.getInt("id_pessoa"));
                p.setNome(rs.getString("nome"));
                p.setSobrenome(rs.getString("sobrenome"));
                lstPessoas.add(p);
            }
        }catch(SQLException e){
            System.out.println("erro ao listar: "+e.getMessage());
        }
        return lstPessoas;
	}

}
