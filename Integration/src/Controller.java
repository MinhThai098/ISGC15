import Model.FileManager;
import Model.LogManager; 
public class Controller {

	public static void main(String[] args) {
		FileManager file = new FileManager();
		file.getXmlFile();
		//LogManager logManager = new LogManager("logFile.log"); 		
		//logManager.logInfo("bla bla bla");
		//logManager.logError("this is an error!"); 
	}

}
