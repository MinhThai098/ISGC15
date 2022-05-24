package Model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FileManager {

	private LogManager logManager;

	public FileManager(LogManager logManager) {

		this.logManager = logManager;
	}
	
	public ArrayList<Lead> leadTest() {
		logManager.logInfo("Starting test...");

		ArrayList <Lead> leads = new ArrayList<Lead>(); 
		
		URL url;
		StringBuilder stringBuilder = null;

		try {
			url = new URL("http://localhost:3000/leadsTest");
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestProperty("Accept", "application/json");
			http.setRequestProperty("Authorization", "Bearer " + Settings.oauth2);
			
			BufferedReader bufferedReader = null;

			bufferedReader = new BufferedReader(new InputStreamReader(http.getInputStream()));
									
			stringBuilder = new StringBuilder();
			String output;

			while ((output = bufferedReader.readLine()) != null) {
				stringBuilder.append(output);
				}
			
			bufferedReader.close();
			http.disconnect();

			logManager.logInfo("Response Completed");

			
			String jsonString = stringBuilder.toString(); 
			JSONObject obj = new JSONObject(jsonString);

			JSONArray arr = obj.getJSONArray("users"); 
			for (int i = 0; i < arr.length(); i++)
			{
			
				
			    String companyName = arr.getJSONObject(i).getString("name");
			    String contactPerson = arr.getJSONObject(i).getString("contactPerson");
			    String phoneNumber = arr.getJSONObject(i).getString("phoneNumber");
			    String adress = arr.getJSONObject(i).getString("adress");
			    String zipCode = arr.getJSONObject(i).getString("zipCode");
			    String city = arr.getJSONObject(i).getString("city");
			    String companySize = arr.getJSONObject(i).getString("companySize");
			    String currentProvider = arr.getJSONObject(i).getString("currentProvider");
			    String email = arr.getJSONObject(i).getString("email");
			
			    
		
			    Lead lead = new Lead(); 
				
			    lead.setCompanyName(companyName);
				lead.setContactPerson(contactPerson);
				lead.setCompanySize(companySize);
				lead.setPhoneNumber(phoneNumber);
				lead.setAdress(adress);
				lead.setZipCode(zipCode);
				lead.setCity(city);
				lead.setCurrentProvider(currentProvider);
				lead.setEmail(email);
			   
				
				leads.add(lead); 
			}
			
		
		} catch (Exception e) {

			System.out.println("");
			e.printStackTrace();
		} 
	
		
		
		logManager.logInfo(leads.size() + " leads fetched from mockoon");
		return leads; 
	}

	// Method to retrieve the response from URL
	public boolean getUrlResponse() {

		StringBuilder stringBuilder = null;
		try {

			// connects to the URL
			URL url = new URL(Settings.httpURL);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestProperty("Accept", "application/json");
			http.setRequestProperty("Authorization", "Bearer " + Settings.oauth2);

			System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
			BufferedReader bufferedReader = null;

			// if the response code is 200 (Successful), get input stream otherwise get the
			// error stream
			if (http.getResponseCode() == 200) {
				bufferedReader = new BufferedReader(new InputStreamReader(http.getInputStream()));
			} else {
				bufferedReader = new BufferedReader(new InputStreamReader(http.getErrorStream()));
			}
			stringBuilder = new StringBuilder();
			String output;

			// reading each line of the stream and initialize/appending it to the
			// stringBuilder
			while ((output = bufferedReader.readLine()) != null) {
				stringBuilder.append(output);
			}
			// closing down the reader and closing down the connection to the http
			bufferedReader.close();
			http.disconnect();

			// logging when the response is completed
			logManager.logInfo("Response Completed");

			// calling to the method saveLeadsInXML() and sending in the stringBuilder
			// converted to a String
			saveLeadsInXML(stringBuilder.toString());
			return true;

		} catch (Exception e) {
			// logging if the response failed
			logManager.logInfo("Response Failed" + e.getMessage());
			return false;
		}
	}

	/**
	 * Called upon to save all leads from HTTP to a XML file
	 * 
	 * @param leads A String that contains all leads
	 */
	private void saveLeadsInXML(String leads) {
		try {
			File leadsFile = new File("leads" + Settings.currentDate2 + ".xml");

			// Checking if file exists or not
			if (!leadsFile.exists()) {
				// Creates a new file if it does not exist
				leadsFile.createNewFile();
				logManager.logInfo("Created leads.xml File");
			} else {
				// Writing over all old leads and saving new
				FileOutputStream writer = new FileOutputStream(leadsFile);
				writer.write(leads.toString().getBytes());
				logManager.logInfo("Saved leads to leads.xml File");
				writer.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logManager.logError("Error saving Leads to leads.xml File");
		}

	}

	// Method to read lead XML file and return the list
	public ArrayList<Lead> getLeadsFromXML() {
		ArrayList<Lead> leadList = new ArrayList<Lead>();

		try {
			File leadsFile = new File("leads" + Settings.currentDate2 + ".xml");
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document doc = documentBuilder.parse(leadsFile);

			NodeList nodes = doc.getElementsByTagName("lead");

			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);

				/*
				 * if the type is a correct node element we create a string variable for each
				 * column, and initialise it with the text content inside the node element we
				 * then create a new lead object with values for each column and add our lead to
				 * an our ArrayList (leadList)
				 */

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) node;
					String name = e.getElementsByTagName("name").item(0).getTextContent();
					String adress = e.getElementsByTagName("address").item(0).getTextContent();
					String zipCode = e.getElementsByTagName("zip").item(0).getTextContent();
					String city = e.getElementsByTagName("city").item(0).getTextContent();
					String contactPerson = e.getElementsByTagName("contact").item(0).getTextContent();
					String phoneNumber = e.getElementsByTagName("tele").item(0).getTextContent();
					String companySize = e.getElementsByTagName("size").item(0).getTextContent();
					String currentProvider = e.getElementsByTagName("current_provider").item(0).getTextContent();
					String email = e.getElementsByTagName("email").item(0).getTextContent();

					Lead lead = new Lead(name, adress, zipCode, city, contactPerson, phoneNumber, companySize,
							currentProvider, email);
					leadList.add(lead);
				}

			}
			// logging the success
			logManager.logInfo("Success reading and retriveing XML file");
			return leadList;

		} catch (Exception e) {
			e.printStackTrace();
			// logging the errors and creating an XML if non-existed
			return leadList;
		}

	}

}
