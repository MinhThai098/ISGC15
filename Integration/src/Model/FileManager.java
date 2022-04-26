package Model;

import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

public class FileManager {
	
	private void getXmlFile() {
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
			
			System.out.println(stringBuilder);
			
			http.disconnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		String text = stringBuilder.toString();


	}
	


}
