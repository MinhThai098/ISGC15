package Model;
import java.sql.*;

public class DollibarConnect {
	
	public static void main (String args[]) {
		System.out.println("Testing DB");
		
		//Try block for finding the SQL Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		} catch(ClassNotFoundException e) {
			System.out.println("where is the SQL");
			e.printStackTrace();
			return;
		}
		System.out.println("Succesfully registrered");
		Connection connection = null;
		
		//Try Block for the connection the database
		try {
			connection = DriverManager
			.getConnection("jdbc:mysql://localhost:8888/dolibarr","root","root");
		}catch(SQLException e) {
			System.out.println("connection failed");
			e.printStackTrace();
			return;
		}
		
		if(connection != null) {
			System.out.println("Success");
		}else {
			System.out.println("failed");
		}
	}
}
		
	



	

