package application.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		String dbURL = "jdbc:sqlite:C:\\Users\\EHG\\Downloads\\database\\zodiacdb2";
        return DriverManager.getConnection(dbURL);
	}
}
