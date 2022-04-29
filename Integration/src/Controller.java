import Model.FileManager;
import Model.Lead;
import Model.LogManager;
import Model.Model; 
import Model.Validator; 
public class Controller {

	public static void main(String[] args) {
	//	FileManager file = new FileManager();
	//	file.readSettingsFile("C:\\Users\\Thai\\Desktop\\settings.txt");
		//	file.getXmlFile();
	
		
		
		
		Validator v = new Validator(); 
		Lead testLead = new Lead("Company X", "Gågatan 312", "52589", "Karlstad", "Olle Svensson", "+1254-12", "5-10", "Marketing Z", "Olle@gmail.com");  

		Lead lead = v.validateLead(testLead); 
		if(lead != null) {
			System.out.println("Company name: " + lead.getCompanyName());
			System.out.println("Contact person: "+lead.getContactPerson());
			System.out.println("Phone Number: "+lead.getPhoneNumber());
			System.out.println("Mail: "+lead.getEmail()); 
			System.out.println("City: "+lead.getCity());
			System.out.println("Zipcode: "+lead.getZipCode()); 
			System.out.println("Address: "+lead.getAdress());
			System.out.println("Company size: "+lead.getCompanySize()); 
			System.out.println("Current provider: "+lead.getCurrentProvider()); 

			
		} else {
			System.out.println("Lead fail");

		}
		
		

	
	}

}
