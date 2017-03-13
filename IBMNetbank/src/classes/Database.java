package classes;

import java.sql.*;

public final class Database {
	
	static Connection con;
	private final static String IP = "192.86.32.54";
	private final static String PASSWORD = "FAGP2017";
	private static Statement stmt;
	private static ResultSet rs;
	
	public static void main(String[] args) {
		System.out.println(logIn("Alexander", "Armstrong7"));
	}
	
	private static void createConnection() {
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			con = DriverManager.getConnection(IP, "DTU19", PASSWORD);
			con.setAutoCommit(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean logIn(String usr, String pass) {
		createConnection();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT password FROM User WHERE username = " + usr);
			if(rs.getString(0).equals(pass)) {
				con.close();
				return true;
			}
			con.close();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
