package Model;

import java.lang.ModuleLayer.Controller;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

// Anv�nd ej om Windows Scheduler kan anv�ndas
public class Clock {

	
	private int hour; 
	
	public Clock() {
	 
		hour = 13; 
	
	}
	
/**
 * Called upon to check if the time is right to get leads from URL
 */
	public void checkTime() {
		
		Calendar currentTime = Calendar.getInstance();
		int hours = 1; // change to settings for what time to get leads!
		
		if(currentTime.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
			if(currentTime.get(Calendar.HOUR_OF_DAY) == hours) {

				return;
			} else {
				try {
					TimeUnit.MINUTES.sleep(60);
					//TimeUnit.HOURS.sleep(1);
					checkTime();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			
		} else {
			try {
				TimeUnit.MINUTES.sleep(60);
				//TimeUnit.HOURS.sleep(1);
				checkTime();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		     
		
		
	
	}
	
}