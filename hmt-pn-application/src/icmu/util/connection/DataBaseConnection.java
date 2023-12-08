package icmu.util.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import icmu.util.Constants;
import icmu.util.Graze;

public class DataBaseConnection {
	public static String status = "Não conectou...";

	public DataBaseConnection() {

	}

	public static void main(String[] argvs) {
		// DataBaseConnection bd = new DataBaseConnection();

		try {
			Connection conn = DataBaseConnection.getDataBaseConnection();
			Statement query = conn.createStatement();

			String consulta = "SELECT * from ox";

			ResultSet resultado = query.executeQuery(consulta);
			while (resultado.next()) {

				System.out.println("ID: " + resultado.getString("name") + "\t" + resultado.getString("breed"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static java.sql.Connection getDataBaseConnection() {

		Connection connection = null; // atributo do tipo Connection

		try {

			// Carregando o JDBC Driver padrão

			// String driverName = "com.mysql.jdbc.Driver";
			String driverName = "com.mysql.cj.jdbc.Driver";

			Class.forName(driverName);

			// Configurando a nossa conexão com um banco de dados//

			// String serverName = "192.168.100.196"; // caminho do servidor do BD
			// String mydatabase = "hmt"; // nome do seu banco de dados
			// String url = "jdbc:mysql://" + serverName + "/" + mydatabase +
			// "?useSSL=false";
			// String url = "jdbc:mysql://" + Constants.SERVER_NAME + "/" +
			// Constants.DATABASE + "?useSSL=false";
			String url = Constants.getMYSQL_ADDRESS() + Constants.DATABASE
					+ "?useSSL=false&allowPublicKeyRetrieval=true";

			// jdbc:mysql://ip-do-servidor:porta/banco-de-dados?useSSL=false

			System.out.println("antes");
			connection = DriverManager.getConnection(url, Constants.USERNAME, Constants.PASSWD);
			System.out.println("depois");
			// Testa sua conexão//

			if (connection != null) {

				status = ("STATUS--->Conectado com sucesso!");

			} else {

				status = ("STATUS--->Não foi possivel realizar conexão");

			}
			System.out.println(status);

			return connection;

		} catch (ClassNotFoundException e) { // Driver não encontrado

			System.out.println("O driver expecificado nao foi encontrado.");

			return null;

		} catch (SQLException e) {

			// Não conseguindo se conectar ao banco
			e.printStackTrace();
			System.out.println("Nao foi possivel conectar ao Banco de Dados.");

			return null;

		}

	}

	// Método que retorna o status da sua conexão//

	public static String statusConection() {

		return status;

	}

	// Método que fecha sua conexão//

	public static boolean FecharConexao() {

		try {

			DataBaseConnection.getDataBaseConnection().close();

			return true;

		} catch (SQLException e) {

			return false;

		}

	}

	// Método que reinicia sua conexão//

	public static java.sql.Connection ReiniciarConexao() {

		FecharConexao();

		return DataBaseConnection.getDataBaseConnection();

	}

	public static void insert(String insertBd) {
		Connection conn = DataBaseConnection.getDataBaseConnection();
		Statement query;
		try {
			query = conn.createStatement();
			query.executeUpdate(insertBd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void update(String updadeBd) {
		Connection conn = DataBaseConnection.getDataBaseConnection();
		Statement query;
		try {
			query = conn.createStatement();
			query.executeUpdate(updadeBd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
