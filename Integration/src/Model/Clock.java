package Model;

import java.lang.ModuleLayer.Controller;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

// Anv�nd ej om Windows Scheduler kan anv�ndas
public class Clock {
	
/**
 * Called upon to check if the time is right to get leads from URL
 */
	public void checkTime() {
		
		// intialize Calender Object
		Calendar currentTime = Calendar.getInstance();
		
		// Checks if the current day is monday or not else it will sleep according to the property file and run the method again
		if(currentTime.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
			// checks if the current time is according to the property file or else sleep and run the method again
			if(currentTime.get(Calendar.HOUR_OF_DAY) == Integer.parseInt(Settings.getLeadTime)) {
				return;
			} else {
				try {
					TimeUnit.MINUTES.sleep(Integer.parseInt(Settings.intervalMinute));

					checkTime();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			try {

				TimeUnit.HOURS.sleep(Integer.parseInt(Settings.intervalHour));
				checkTime();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}
	
}