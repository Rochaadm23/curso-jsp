package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {
	
	
	private Connection connection;
	
	public DAOUsuarioRepository() {
		
		connection = SingleConnectionBanco.getConnection();
		
	}
	
	
	public void gravarUsusario(ModelLogin objeto) throws SQLException {
		
		
			
			
			String sql = "insert into model_login(login,senha,nome,sobrenome,email) values ('?','?','?','?','?');";
			
			PreparedStatement preparedSql = connection.prepareStatement(sql);
			
			preparedSql.setString(1, objeto.getLogin());
			preparedSql.setString(2, objeto.getSenha());
			preparedSql.setString(3, objeto.getNome());
			preparedSql.setString(4, objeto.getSobrenome());
			preparedSql.setString(5, objeto.getEmail());
			
			preparedSql.execute();
			
			connection.commit();
	}
	
	
	
}
