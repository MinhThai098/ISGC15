package Model;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class FileManager {
	LogManager logManager = new LogManager("logg.log");
	private ArrayList<Lead> leadList = new ArrayList<Lead>();

	/**
	 * Called to read and retrieve XML file from a HTTP url
	 */

	// Method to retrieve the response from URL
	public void getUrlResponse() {

		// oauth2 tokenet
		final String token = "299c5fb8e6b25f3c26c2813943cba265";

		// HTML URL
		final String stringUrl = "http://bizlab.kau.se:8280/leads/v1/currentweek";

		StringBuilder stringBuilder = null;
		try {

			// connects to the URL
			URL url = new URL(stringUrl);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestProperty("Accept", "application/json");
			http.setRequestProperty("Authorization", "Bearer " + token);

			System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
			BufferedReader bufferedReader = null;

			// Checks the responsecode. 401 is unauthorized, 200 is OK
			if (100 <= http.getResponseCode() && http.getResponseCode() <= 399) {
				bufferedReader = new BufferedReader(new InputStreamReader(http.getInputStream()));
			} else {
				bufferedReader = new BufferedReader(new InputStreamReader(http.getErrorStream()));
			}

			stringBuilder = new StringBuilder();
			String output;

			while ((output = bufferedReader.readLine()) != null) {
				stringBuilder.append(output);
			}

			bufferedReader.close();
			http.disconnect();
			logManager.logInfo("Response Completed");
			
			saveLeadsInXML(stringBuilder.toString());
			
		} catch (Exception e) {
			logManager.logInfo("Response Failed" + e.getMessage());
		}
	}
	/**
	 * Called upon to save all leads from HTTP to a XML file
	 * @param leads A String that contains all leads
	 */
	public void saveLeadsInXML(String leads) {
		try {
			File leadsFile = new File("leads.xml");
			
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
			}
		} catch (Exception e) {
			e.printStackTrace();
			logManager.logError("Error saving Leads to leads.xml File");
		}

	}

	//Method to read lead XML file and return the list
	public ArrayList<Lead> getLeadsFromXML() {

		try {
			File leadsFile = new File("leads.xml");
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document doc = documentBuilder.parse(leadsFile);

			NodeList nodes = doc.getElementsByTagName("lead");

			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element)node;
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

			logManager.logInfo("Success reading and retriveing XML file");
			return leadList;

		} catch (Exception e) {
			e.printStackTrace();
			logManager.logError("Error reading/retrive XML file and creating a XML file");
			return leadList;
		} 
	}

	/**
	 * Called to retrieve settings in a array
	 * 
	 * @param path The path of a file
	 * @return array A string of array containing settings.
	 */

	/*
	 * public ArrayList<String> readSettingsFile(String path) {
	 * 
	 * File file = new File(path); ArrayList<String> array = null; try { // open
	 * file BufferedReader bufferedReader = new BufferedReader(new
	 * FileReader(file));
	 * 
	 * array = new ArrayList<String>();
	 * 
	 * String placeHolder;
	 * 
	 * //reading every line and appending them to an array while ((placeHolder =
	 * bufferedReader.readLine()) != null) { array.add(placeHolder); }
	 * bufferedReader.close();
	 * 
	 * 
	 * }catch (Exception e) { e.printStackTrace();
	 * logManager.logError("Error reading settings file"); } if (array != null)
	 * {logManager.logInfo("Sucess reading settings file");}
	 * 
	 * return array; }
	 * 
	 */

}
