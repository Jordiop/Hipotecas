package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

/**
 * @author jordiop
 *
 */

public class Bbdd {
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
	
	public static String mostrarPresupuestos(String username) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://192.168.1.25:3306/practica11";
		Connection con = DriverManager.getConnection(url, "daw", "password");
		Statement st = con.createStatement();
		int identifier = obtenerId(username);
		String query = "SELECT `id`, `interes`, `meses`, `capital` FROM `hipotecas` WHERE `user_id` = '"+identifier+"'";
		ResultSet rs = st.executeQuery(query);
		String resultat = "<table class=\"text-center border-separate border-spacing-2 border border-slate-500\" border=\"1\">"
				+ "<tr>"
				+ "<th>id</th>"
				+ "<th>Capital</th>"
				+ "<th>Meses</th>"
				+ "<th>Interes</th>"
				+ "<th>Generate</th>"
				+ "</tr>";
		while (rs.next()) {
			resultat = resultat 
					+"<tr>"
					+ "<td>" + rs.getString("id")+"</td>"
					+ "<td>" + rs.getString("capital")+"</td>"
					+ "<td>" + rs.getString("meses")+"</td>"
					+ "<td>" + rs.getString("interes")+"</td>"
					+ "<td><button onclick=\"cargarPresupuestoID("+rs.getString("id")+")\">Ver mas</button></td>"
					+ "</tr>";
		}
		resultat = resultat +"</table>";
		return resultat;
	}
	
	public static String buscarPresupuestoID(int id) throws SQLException, ClassNotFoundException {
		String resultat = "";
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://192.168.1.25:3306/practica11";
		Connection con = DriverManager.getConnection(url, "daw", "password");
		Statement st = con.createStatement();
		String query = "SELECT `interes`, `meses`, `capital` FROM `hipotecas` WHERE `id` = '"+id+"'";
		ResultSet rs = st.executeQuery(query);
		if (rs.next()) {
			double interes = rs.getDouble("interes");
			double capital = rs.getDouble("capital");
			double meses = rs.getDouble("meses");
			DecimalFormat df = new DecimalFormat("#.00");
			if (meses > 0 && interes > 0 && capital > 0) {
				double i = ((interes/100) / 12);
		        double cuota_mensual = capital / ((1-Math.pow((1+i),-meses))/i);
			    // Montamos la tabla de HTML desde JAVA
			    resultat = "<table class=\"text-center border-separate border-spacing-2 border border-slate-500\" border=\"1\">"
						+ "<tr>"
						+ "<th>Capital pendiente anterior</th>"
						+ "<th>Cuota a pagar</th>"
						+ "<th>Parte de cuota amortizacion</th>"
						+ "<th>Parte de cuota interes</th>"
						+ "<th>Capital pendiente posterior</th>"
						+ "</tr>";
			    
			    while (capital>0) {
			    	resultat = resultat 
			    			+ "<tr>"
			    			+ "<td>"+df.format(capital)+"</td>"
			    			+ "<td>"+df.format(cuota_mensual)+"</td>"
			    			+ "<td>"+df.format(cuota_mensual-(capital*i))+"</td>"
			    			+ "<td>"+df.format(capital*i)+"</td>"
			    			+ "<td>"+df.format(capital - cuota_mensual)+"</td>"
			    			+ "</tr>";
			    	capital = capital - cuota_mensual;
			    }
			}
		}
		return resultat;
	}
}
