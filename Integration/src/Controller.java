import java.util.ArrayList;

import Model.DollibarConnect;
import Model.FileManager;
import Model.Lead;
import Model.LogManager;
import Model.Model; 
import Model.Validator; 
public class Controller {

	public static void main(String[] args) {
		
		FileManager file = new FileManager();
		file.getUrlResponse();
		ArrayList leadList = file.getLeadsFromXML();
		

		
		
		Validator v = new Validator(); 
		ArrayList vLeadList = v.validateLeads(leadList); 
		
				
		
		for(int i = 0; i< leadList.size(); i++) {
			System.out.println("Company name: " + ((Lead) vLeadList.get(i)).getCompanyName());
			System.out.println("Contact person: "+((Lead) vLeadList.get(i)).getContactPerson());
			System.out.println("Phone Number: "+((Lead) vLeadList.get(i)).getPhoneNumber());
			System.out.println("Mail: "+((Lead) vLeadList.get(i)).getEmail()); 
			System.out.println("Adress: "+((Lead) vLeadList.get(i)).getAdress()); 
			System.out.println("City: "+((Lead) vLeadList.get(i)).getCity());  

			System.out.println("Zipcode: "+((Lead) vLeadList.get(i)).getZipCode()); 
			System.out.println("Company size: "+((Lead) vLeadList.get(i)).getCompanySize()); 
			System.out.println("Current provider: "+((Lead) vLeadList.get(i)).getCurrentProvider()); 
			System.out.println("\n"); 

			
		}
		
		

	
	}

}
