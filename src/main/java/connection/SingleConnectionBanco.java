package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {

	private static String url = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconect=true";
	private static String user = "postgres";
	private static String senha = "12345678";
	private static Connection connection = null;

	public static Connection getConnection() {
		return connection;
	}

	static {
		conectar();
	}

	public SingleConnectionBanco() {/* quando tiver uma isntância vai conectar */
		conectar();
	}

	private static void conectar() {

		try {

			if (connection == null) {
				Class.forName("org.postgresql.Driver");// Carrega o driver de conexão com do banco
				connection = DriverManager.getConnection(url, user, senha);
				connection.setAutoCommit(false);// Para não efetuar auterações no banco sem nosso comando.

			}

		} catch (Exception e) {
			e.printStackTrace();/* Mostrar qualquer erro no momento de conectar */

		}

	}

}
