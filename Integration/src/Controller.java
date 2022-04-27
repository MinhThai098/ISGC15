import Model.FileManager;
import Model.LogManager;
import Model.Model; 
public class Controller {

	public static void main(String[] args) {
		Model model = new Model();
		model.getLead();
		//LogManager logManager = new LogManager("logFile.log"); 		
		//logManager.logInfo("bla bla bla");
		//logManager.logError("this is an error!"); 
	}

}
