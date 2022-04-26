package Model;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class FileManager {
	
	public void getXmlFile() {
		final String token = "299c5fb8e6b25f3c26c2813943cba265";
		final String stringUrl = "http://bizlab.kau.se:8280/leads/v1/currentweek";
		StringBuilder stringBuilder = null;
		try {
			URL url = new URL(stringUrl);
			HttpURLConnection http = (HttpURLConnection)url.openConnection();
			http.setRequestProperty("Accept", "application/json");
			http.setRequestProperty("Authorization", "Bearer " + token);
	
			System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
			BufferedReader bufferedReader = null;
			
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
			
			//System.out.println(stringBuilder);
			
			http.disconnect();
			
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().
			parse(new InputSource(new StringReader(stringBuilder.toString())));
			NodeList nodes = doc.getElementsByTagName("lead");
			
			for(int i = 0; i < nodes.getLength(); i++) {
				Element e = (Element) nodes.item(i);
				System.out.println("Name: " + e.getElementsByTagName("name").item(0).getTextContent());
				System.out.println("Address: " + e.getElementsByTagName("address").item(0).getTextContent());
				System.out.println("Zip: " + e.getElementsByTagName("zip").item(0).getTextContent());
				System.out.println("City: " + e.getElementsByTagName("city").item(0).getTextContent());
				System.out.println("Contact: " + e.getElementsByTagName("contact").item(0).getTextContent());
				System.out.println("Tele: " + e.getElementsByTagName("tele").item(0).getTextContent());
				System.out.println("Size: " + e.getElementsByTagName("size").item(0).getTextContent());
				System.out.println("Current Provider: " + e.getElementsByTagName("current_provider").item(0).getTextContent());
				System.out.println("email: " + e.getElementsByTagName("email").item(0).getTextContent());
				System.out.println("\n");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		String text = stringBuilder.toString();


	}
	


}
