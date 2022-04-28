package Model;

public class Validator {

	
	
	
	
	
	public Lead validateLead(Lead nnnn) {
		
		Lead leadToReturn = new Lead(); 
		// testdata
		Lead leadToValidate = new Lead("Company X AB", "Gågatan 312", 52589, "Karlstad", "Olle Svensson", "+1254-12", "5-10", "Marketing Z", "Olle@gmail.com");  
		
		String companyName =     leadToValidate.getCompanyName();  				//max 40 char
		String adress =    	     leadToValidate.getAdress(); 			// max 40 char
		int zipCode = 	         leadToValidate.getZipCode(); 				// 5 numbers
		String city =            leadToValidate.getCity(); 				// max 40 char
		String contactPerson =   leadToValidate.getContactPerson(); 			// max 40 char MUST HAVE
		String phoneNumber = 	 leadToValidate.getPhoneNumber(); 				// Only numbers
		String companySize =	 leadToValidate.getCompanySize();  			// interval (ex. 5 - 20) max 20 char
		String currentProvider = leadToValidate.getCurrentProvider();   // max 40 char
		String email =	 		 leadToValidate.getEmail();			    // max 100 char
		


		// Validating the most important attributes first
		
			// CompanyName 
			if (companyName.length() <= 1) {
				
				return null; 
			}
			
			// ContactPerson
			if (contactPerson.length() <= 1 ) {
				
				return null; 
			}
	
			// Phone Number 
			// Regex from: https://stackoverflow.com/questions/30618955/regex-to-allow-numbers-plus-symbol-minus-symbol-and-brackets
			if (phoneNumber.length() <= 4 || !(phoneNumber.matches("^[\\d\\(\\)\\-+]+$"))) {
	
				return null; 
			} 
		
		

		if (companyName.length() < 2) {
			leadToReturn.setCompanyName(null);
			
		}
		// continue here... 
			
			
		// Trims attribute to desired length DO LAST
		if (companyName.length() > 40) {  companyName = companyName.substring(0, 40); }
		if (adress.length() > 40) {  adress = adress.substring(0, 40); }
		if (city.length() > 40) {  city = city.substring(0, 40); }
		if (contactPerson.length() > 40) {  contactPerson = contactPerson.substring(0, 40); }
		if (companySize.length() > 40) {  companySize = companySize.substring(0, 20); }
		if (currentProvider.length() > 40) {  currentProvider = currentProvider.substring(0, 40); }
		if (email.length() > 40) {  email = email.substring(0, 100); }

		System.out.println(companyName);
		
		
		
		return leadToReturn; 
	}
	
	
	
	
	
}
