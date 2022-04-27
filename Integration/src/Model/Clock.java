package Model;

import java.lang.ModuleLayer.Controller;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

// Använd ej om Windows Scheduler kan användas
public class Clock {

	
	private int hour; 
	
	public Clock() {
	 
		hour = 13; 
	
	}
	

	public void checkTime() {
		
			TimerTask task = new TimerTask() {  
			Calendar today = Calendar.getInstance();

			@Override  
		    public void run() {  


				if(Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
					
					// get leadlist
					
				}
				
			
			};  
		};  
		
		
		
		     
	Calendar today = Calendar.getInstance();
	today.set(Calendar.HOUR_OF_DAY, hour);
	today.set(Calendar.MINUTE, 36);
	today.set(Calendar.SECOND, 00);

	Timer timer = new Timer();
	timer.schedule(task, today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)); 

		
		
	
	}
	
}