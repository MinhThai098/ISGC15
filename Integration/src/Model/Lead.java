package Model;
import java.io.Serializable;

/*
 * In this class we want to collect ONE lead that we can use in a list
 * in a different class where we collect the leads.
 * */
public class Lead implements Serializable{

	private static final long serialVersionUID = 1L;
	private String companyName; //max 40 char
	private String adress; // max 40 char
	private String zipCode; // 5 numbers
	private String city; // max 40 char
	private String contactPerson; // max 40 char MUST HAVE
	private String phoneNumber;  // Only numbers
	private String companySize; // interval (ex. 5 - 20) max 20 char
	private String currentProvider; // max 40 char
	private String email; // max 100 char
	


	public Lead() {
		
	}
	// Compare CompanyName with CompanyName
	@Override
    public boolean equals(Object anObject) {
        if (!(anObject instanceof Lead)) {
            return false;
        }
        Lead lead = (Lead)anObject;
        return lead.getCompanyName().equals(getCompanyName());
    }

	
	// Constructor to create lead-object 
	public Lead(String companyName, String adress, String zipCode, String city, String contactPerson, String phoneNumber,
			String companySize,String currentProvider, String email) {
		super();
		this.companyName = companyName;
		this.adress = adress;
		this.zipCode = zipCode;
		this.city = city;
		this.contactPerson = contactPerson;
		this.phoneNumber = phoneNumber;
		this.companySize = companySize;
		this.currentProvider = currentProvider; 
		this.email = email;
	}
	
	/*
	 * Methods to return specific values of the Lead object
	 * Getters
	 * */
	
	public String getCompanyName(){return companyName;}
	public String getAdress(){return adress;}
	public String getZipCode(){return zipCode;}
	public String getCity(){return city;}
	public String getContactPerson(){return contactPerson;}
	public String getPhoneNumber(){return phoneNumber;}
	public String getCompanySize(){return companySize;}
	public String getCurrentProvider(){return currentProvider;}
	public String getEmail(){return email;}
	
	
	// Setters
	public void setCompanyName(String companyName) {this.companyName = companyName;}
	public void setAdress(String adress) {this.adress = adress;}
	public void setZipCode(String zipCode) {this.zipCode = zipCode;}
	public void setCity(String city) {this.city = city;}
	public void setContactPerson(String contactPerson) {this.contactPerson = contactPerson;}
	public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
	public void setCompanySize(String companySize) {this.companySize = companySize;}
	public void setCurrentProvider(String currentProvider) {this.currentProvider = currentProvider;}
	public void setEmail(String email) {this.email = email;}

	
	
}