import java.util.ArrayList;

import Model.DollibarConnect;
import Model.FileManager;
import Model.Lead;
import Model.Settings;
import Model.Validator; 
public class Controller {

	public static void main(String[] args) {
		
		Settings settings = new Settings();
		String path = "config.properties";
		settings.getSettings(path);
		/*
		FileManager file = new FileManager();
		file.getUrlResponse();
		ArrayList leadList = file.getLeadsFromXML();
		

		
		
		Validator v = new Validator(); 

		ArrayList vLeadList = v.validateLeads(leadList); 
		Validator validator = new Validator(); 
		ArrayList verrifiedList = validator.validateLeads(leadList); 


		
				
		for(int i = 0; i< leadList.size(); i++) {
			System.out.println("Company name: " + ((Lead) leadList.get(i)).getCompanyName());
			System.out.println("Contact person: "+((Lead) leadList.get(i)).getContactPerson());
			System.out.println("Phone Number: "+((Lead) leadList.get(i)).getPhoneNumber());
			System.out.println("Mail: "+((Lead) leadList.get(i)).getEmail()); 
			System.out.println("Adress: "+((Lead) leadList.get(i)).getAdress()); 
			System.out.println("City: "+((Lead) leadList.get(i)).getCity());  

			System.out.println("Zipcode: "+((Lead) leadList.get(i)).getZipCode()); 
			System.out.println("Company size: "+((Lead) leadList.get(i)).getCompanySize()); 
			System.out.println("Current provider: "+((Lead) leadList.get(i)).getCurrentProvider()); 
			System.out.println("\n"); 

			
		}
		
		
		DollibarConnect dolibarrConnect = new DollibarConnect(); 
		
		dolibarrConnect.importLeads(verrifiedList);
		*/
	
	}

}
