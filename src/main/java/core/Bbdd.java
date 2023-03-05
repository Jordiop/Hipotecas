package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author jordiop
 *
 */

public class Bbdd {
	public static void registroDB(String username, String password) throws ClassNotFoundException, SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://192.168.1.25:3306/practica11?useSSL=false&allowPublicKeyRetrieval=true" , "daw", "password");
			Statement st = con.createStatement();
			String query = "INSERT INTO `users`(`username`, `password`) VALUES ('"+username+"', '"+password+"')";
			st.executeUpdate(query);
			st.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static String loginDB(String username, String password) {
		String resultat = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://192.168.1.25:3306/practica11?useSSL=false&allowPublicKeyRetrieval=true" , "daw", "password");
			Statement st = con.createStatement();
			String query = "SELECT username FROM users WHERE username = '"+username+"' AND password = '"+password+"'";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				resultat = rs.getString("username");
			}
			st.executeUpdate(query);
			st.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return resultat;
	}
	
	public static void guardarPrestamo(double interes, double meses, double capital, String username) throws ClassNotFoundException, SQLException {
		int identifier = obtenerId(username);
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://192.168.1.25:3306/practica11?useSSL=false&allowPublicKeyRetrieval=true" , "daw", "password");
		Statement st = con.createStatement();
		String query = "INSERT INTO `hipotecas` (`user_id`, `interes`, `capital`, `meses`) VALUES ('"+identifier+"','"+interes+"','"+capital+"','"+meses+"')";
		st.execute(query);
		st.close();
		con.close();
	}
	
	public static int obtenerId(String username) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://192.168.1.25:3306/practica11?useSSL=false&allowPublicKeyRetrieval=true" , "daw", "password");
		Statement st = con.createStatement();
		String query = "SELECT id FROM users WHERE username = '"+username+"'";
		int id = -1;
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) { 
			id = rs.getInt("id");
		};
		return id;
	}
}
