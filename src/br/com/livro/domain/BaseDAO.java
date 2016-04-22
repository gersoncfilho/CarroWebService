package br.com.livro.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
	public BaseDAO()
	{
		try
		{
			//Necessário para usar o driver JDBC do MySQL
			Class.forName("com.mysql.jdbc.Driver");
			
		}catch(ClassNotFoundException ex){
			//Erro do driver jdbc
			ex.printStackTrace();
		}
	}
	
	protected Connection getConnection() throws SQLException{
		//URL de conexao com o banco de dados
		String url = "jdbc:mysql://localhost:8889/livro";
		//conecta utilizando a URL, usuario e senha
		Connection conn = DriverManager.getConnection(url, "livro", "livro123");
		return conn;
	}
	
	public static void main (String[] args) throws SQLException{
		BaseDAO db = new BaseDAO();
		//Testa conexao
		Connection conn = db.getConnection();
		System.out.println(conn);
	}
}
