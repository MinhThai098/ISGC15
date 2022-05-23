package Model;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;




public class DollibarConnect {

	private LogManager logManager; 
	
	
	public DollibarConnect(LogManager logManager) {
		
		
		this.logManager = logManager; 
	}

	/*
	 * Get leads from lead list 
	 * Saving objects in variables
	 * return a leadList 
	 * */		

	public ArrayList<Lead> getLeads(){
		
		ArrayList<Lead> leadList = new ArrayList<Lead>(); 
		
		/*
		 * The 'try' begins with establishing the connection with dolibarr and logging when successful.
		 * We then establish the communication with the table llx_societe where we select the columns needed
		 * and linking our setters from Lead.java with the columns from the table and later adding them to our ArrayList leadList.
		 * 
		 * The 'catch' catch and logs all the errors and if the connection with dolibarr somehow failed.
		 * */
		try {

			Class.forName("com.mysql.cj.jdbc.Driver"); 
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:"+ Settings.port +"/dolibarr", Settings.dbName , Settings.dbPassword);
			logManager.logInfo("Dolibarr connection established.");
		
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

	/*
	 * This method once again establish the connection with dolibarr but this time inserting the values from our ArrayList leadList
	 * into the table llx_societe by using our getters from our Lead class.
	 * It logs when the leads has been successfully imported to dolibarr and logging when failed to do so
	 * */


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
	
  
	/*
	 * This method is removing a lead from the table llx_societe / Dolibarr when a lead has the client value as 0.
	 * The value = 0, is a value that indicates that a lead is not a customer or not interested to be a customer.
	 * The method is logging when successful and also when failed to remove a lead
	 * */

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
		
	



	

