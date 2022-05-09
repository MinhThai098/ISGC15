package Model;
import java.sql.*;
import java.util.ArrayList;




public class DollibarConnect {

	
	private LogManager logManager = new LogManager("logg.log");
	private Settings settings; 
	
	public DollibarConnect(Settings settings) {
		
		this.settings = settings; 
		
		
		
	}
	


	public void importLeads(ArrayList<Lead> leadList) {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver"); 
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dolibarr", settings.dbName , settings.dbPassword);
			logManager.logInfo("Dolibarr conection established.");
		
			Statement statement = con.createStatement();	

			for(int i = 0; i < leadList.size(); i++) {
				
				statement.executeUpdate("INSERT INTO llx_societe (nom, name_alias, town, zip, address, email, phone, siren, siret, client) values "
						+ "('"
						+ leadList.get(i).getCompanyName()+"','"
						+ leadList.get(i).getContactPerson()+"','"
						+ leadList.get(i).getCity()+"','"
						+ leadList.get(i).getZipCode()+"','"
						+ leadList.get(i).getAdress()+"','"
						+ leadList.get(i).getEmail()+"','"
						+ leadList.get(i).getPhoneNumber()+"','"
						+ leadList.get(i).getCurrentProvider()
						+"','"+ leadList.get(i).getCompanySize()
						+"','"
						+ 2 
						+"')");
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
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dolibarr",settings.dbName, settings.dbPassword);
			Statement stmtDelete = con.createStatement();
			stmtDelete.executeUpdate("DELETE FROM `llx_societe` where `client` = '0'");
			con.close();
			logManager.logInfo(" leads with status not a client is removed.");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logManager.logInfo("Failed to remove the lead" + e.getMessage());
		}
	}
	
}
		
	



	

