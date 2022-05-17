package Model;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;




public class DollibarConnect {

	
	private LogManager logManager = new LogManager("logg" + Settings.currentDate + ".log");;

		
	
	public ArrayList<Lead> getLeads(){
		
		ArrayList<Lead> leadList = new ArrayList<Lead>(); 
	
		try {

			Class.forName("com.mysql.cj.jdbc.Driver"); 
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:"+ settings.port +"/dolibarr", Settings.dbName , Settings.dbPassword);
			logManager.logInfo("Dolibarr conection established.");
		
			Statement statement = con.createStatement();	
		     ResultSet results = statement.executeQuery("SELECT * FROM llx_societe");

		     
		     while (results.next())
		      {
		    	 

		    	Lead lead = new Lead(); 
		    	lead.setCompanyName(results.getString("nom")); 
		    	lead.setContactPerson(results.getString("name_alias")); 
		    	lead.setCity(results.getString("town")); 
		    	lead.setZipCode(results.getString("zip")); 
		    	lead.setAdress(results.getString("address")); 
		    	lead.setEmail(results.getString("email")); 
		    	lead.setPhoneNumber(results.getString("siren")); 
		    	lead.setCurrentProvider(results.getString("siret")); 

		    	leadList.add(lead); 
		      }
				
			
			con.close();
			logManager.logInfo("Fetched last weeks leads from dolibarr");
		}
		
		catch (Exception e){
			e.printStackTrace();
			logManager.logInfo("Dolibarr connection failed: " + e.getMessage());

		}
			
		return leadList; 	
		
	}



	public void importLeads(ArrayList<Lead> leadList) {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver"); 

			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:"+ Settings.port +"/dolibarr", Settings.dbName , Settings.dbPassword);

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
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dolibarr",Settings.dbName, Settings.dbPassword);
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
		
	



	

