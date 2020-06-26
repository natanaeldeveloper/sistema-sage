package br.com.projeto.estoque.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Banco {
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		String driver = "com.mysql.jdbc.Driver";
		String banco = "estoquetecfour";
		String host = "localhost";
		String str_conn = "jdbc:mysql://" + host + ":3306/" + banco + "?serverTimezone=GMT-3"; // URL de conexão
		String usuario = "root";
		String senha = "root";

		try {
			Class.forName(driver); // Carrega o driver

			conn = DriverManager.getConnection(str_conn, usuario, senha);

			System.out.println("CONECTOU! :)");

		} catch (Exception ex) {

			System.out.println("ERRO!");
			ex.printStackTrace();

		} // fim do try-catch

		return conn; // envia a conexao pra quem solicitou

	}
}
