import Model.FileManager;
import Model.LogManager;
import Model.Model; 
import Model.Validator; 
public class Controller {

	public static void main(String[] args) {
		FileManager file = new FileManager();
		file.readSettingsFile("C:\\Users\\Thai\\Desktop\\settings.txt");
		//	file.getXmlFile();
		//LogManager logManager = new LogManager("logFile.log"); 		
		//logManager.logInfo("bla bla bla");
		//logManager.logError("this is an error!"); 
		
		
		
		//Validator v = new Validator(); 
		//v.validateLead(null); 
	}

}
