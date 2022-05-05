package Model;
import java.sql.*;
import java.util.ArrayList;




public class DollibarConnect {

	
	private LogManager logManager = new LogManager("logg.log");

	
	public void testConnection() {
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
			.getConnection("jdbc:mysql://localhost:3306/dolibarr","root","root");
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
	
	
	
	public void importLeads(ArrayList<Lead> leadList) {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver"); 
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dolibarr","dolibarrmysql","changeme");
			logManager.logInfo("Dolibarr conection established.");

			for(int i = 0; i < leadList.size(); i++) {
				Statement stmtInsert = con.createStatement();	
				
				stmtInsert.executeUpdate("INSERT INTO llx_societe (nom, name_alias, address, zip, town, phone, email, note_private, siren, client, status) values "
						+ "('"+ leadList.get(i).getCompanyName()+"','"+ leadList.get(i).getContactPerson()+"','"+ leadList.get(i).getAdress()+"','"+ leadList.get(i).getZipCode()
						+"','"+ leadList.get(i).getCity()+"','"+ leadList.get(i).getPhoneNumber()+"','"+ leadList.get(i).getEmail()+"','"+ leadList.get(i).getCurrentProvider()
						+"','"+ leadList.get(i).getCompanySize()+"','"+ 2 +"','1')");
			}
			con.close();
			logManager.logInfo(leadList.size() + " leads imported to dolibarr.");

		}
		
		catch (Exception e){
			e.printStackTrace();
			logManager.logInfo("Dolibarr connection failed: " + e.getMessage());

		}
	}
	
	public void removeLead() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dolibarr","dolibarrmysql","changeme");
			Statement stmtDelete = con.createStatement();
			stmtDelete.executeUpdate("DELETE FROM 'llx_societe' where 'client' = '4'");
			con.close();
			logManager.logInfo(" leads with status not a client is removed.");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logManager.logInfo("Failed to remove the lead" + e.getMessage());
		}
	}
	
}
		
	



	

