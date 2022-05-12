package Model;

import java.util.ArrayList;

public class Validator {

	private LogManager logManager = new LogManager("logg.log");

	public ArrayList<Lead> validateLeads(ArrayList<Lead> leadListIn) {
	
		ArrayList<Lead> verifiedLeadList = new ArrayList<Lead>(); 
		
		// Loops through all leads in leadList
		for (int i = 0; i< leadListIn.size(); i++) {
			Lead lead = validateLead(leadListIn.get(i), i+1); 
			if (lead != null) {
				verifiedLeadList.add(lead); 
				
			}
		}
		
		// the difference between leads in and approved leads
		int failedLeads = leadListIn.size() -  verifiedLeadList.size(); 
		
		logManager.logInfo("Leads validated. " +verifiedLeadList.size()+"/" +leadListIn.size()  +" leads passed" );
		
		if (failedLeads > 0) {
			logManager.logError(failedLeads + " leads failed to validate");
		}
		return verifiedLeadList; 
		
	}
	
	
	// Validates one lead
	private Lead validateLead(Lead leadToValidate, int leadNumber) {
		
		// Must have attributes 
		String companyName =     leadToValidate.getCompanyName();  				
		String contactPerson =   leadToValidate.getContactPerson(); 			
		String phoneNumber = 	 leadToValidate.getPhoneNumber(); 				
		
		// Other attributes
		String adress =    	     leadToValidate.getAdress(); 			// max 40 char
		String zipCode = 	     leadToValidate.getZipCode();    		// 5 numbers
		String city =            leadToValidate.getCity(); 				// max 40 char
		String companySize =	 leadToValidate.getCompanySize();  		// interval (ex. 5 - 20) max 20 char
		String currentProvider = leadToValidate.getCurrentProvider();   // max 40 char
		String email =	 		 leadToValidate.getEmail();			    // max 100 char
		
		// Validating the most important attributes first
		
			// CompanyName must have atleast 2 char
			if (companyName.length() < 2 || !contactPerson.matches(".*[a-zA-Z]+.*")) {
				logManager.logError("Lead " + leadNumber+ " failed at company name: "+ companyName );

				return null; 
			}
			
			
			// Contactperson must have atleast 2 char, and contain atleast 1 letter
			// Regex from: https://stackoverflow.com/questions/14278170/how-to-check-whether-a-string-contains-at-least-one-alphabet-in-java
			if (contactPerson.length() < 2 || !contactPerson.matches(".*[a-zA-Z]+.*")) {
				logManager.logError("Lead " + leadNumber+ " failed at contact person" + contactPerson);

				return null; 
			}
	
			// Phone Number must have atleast 4 char, can also contain + and -  
			// Regex from: https://stackoverflow.com/questions/30618955/regex-to-allow-numbers-plus-symbol-minus-symbol-and-brackets
			if (phoneNumber.length() < 4 || !(phoneNumber.matches("^[\\d\\(\\)\\-+]+$"))) {
				logManager.logError("Lead " + leadNumber+ " failed at phone number" + phoneNumber);

				return null; 
			} 

			Lead leadToReturn = new Lead(); 

			leadToReturn.setCompanyName(companyName);
			leadToReturn.setAdress(adress);
			leadToReturn.setZipCode(zipCode);
			leadToReturn.setContactPerson(contactPerson);
			leadToReturn.setCity(city);
			leadToReturn.setPhoneNumber(phoneNumber);
			leadToReturn.setCompanySize(companySize);
			leadToReturn.setCurrentProvider(currentProvider);
			leadToReturn.setEmail(email);

		
		if(adress.length() < 2) {
			leadToReturn.setAdress(null);
		}
		
		if(zipCode.length() != 5 ) {
			leadToReturn.setZipCode(null);
		}
		
		if(city.length() < 1 ) {
			leadToReturn.setCity(null);
		}

		if(companySize.length() < 1) {
			leadToReturn.setCompanySize(null);

		}
		
		if(currentProvider.length() < 1) {
			leadToReturn.setCurrentProvider(null);

		}
		
		if(email.length() < 1) {
			leadToReturn.setEmail(null);

		}
		
		// Trims attribute to desired length DO LAST
		if (companyName.length() > 40) {  companyName = companyName.substring(0, 40); }
		if (adress.length() > 40) {  adress = adress.substring(0, 40); }
		if (city.length() > 40) {  city = city.substring(0, 40); }
		if (contactPerson.length() > 40) {  contactPerson = contactPerson.substring(0, 40); }
		if (companySize.length() > 40) {  companySize = companySize.substring(0, 20); }
		if (currentProvider.length() > 40) {  currentProvider = currentProvider.substring(0, 40); }
		if (email.length() > 40) {  email = email.substring(0, 100); }

		
		return leadToReturn; 
	}
	
	
	// Compares two lists of leads and removes duplicates
	public ArrayList<Lead> compareLeadLists(ArrayList<Lead> oldList, ArrayList<Lead> newList) {
		
		int newListInQuantity = newList.size(); 
		
			for (int i = 0; i< newList.size(); i++) {
				for(int j=0; j< oldList.size(); j++) {
					if (newList.get(i).getCompanyName().equals(oldList.get(j).getCompanyName()) &&
						newList.get(i).getContactPerson().equals(oldList.get(j).getContactPerson()
							)) {
								newList.remove(i); 
					}
				}
			}
		
			int difference = newListInQuantity - newList.size(); 
			
			if(difference != 0) {
				logManager.logError(difference +"/"+ newListInQuantity +" leads already exists from last week");

			}
			
		return newList; 
	}
	
	
	
	
}
