package Model;

public class Lead {

	
	
	private String companyName; //max 40 char
	private String adress; // max 40 char
	private int zipCode; // 5 numbers
	private String city; // max 40 char
	private String contactPerson; // max 40 char MUST HAVE
	private String phoneNumber;  // Only numbers
	private String companySize; // interval (ex. 5 - 20) max 20 char
	private String currentProvider; // max 40 char
	private String email; // max 100 char
	
	
	// Constructor to create lead-object 
	public Lead(String companyName, String adress, int zipCode, String city, String contactPerson, String phoneNumber,
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
	

	
	
}