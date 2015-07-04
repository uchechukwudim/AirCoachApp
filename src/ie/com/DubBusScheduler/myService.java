package ie.com.DubBusScheduler;

import java.util.Calendar;

import android.app.Service;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.util.Log;

public class myService extends Service{
		
		private static final String TAG = myService.class.getSimpleName();
		private Updater update;
		DatabaseHelper db;
		public boolean isRunning= false;
		IntervalUpdater inUpdater;
		realTimeUpdater rtUpdater;
		countDownTimer countingDown;
		
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
	super.onCreate();
		update = new Updater();
		inUpdater = new IntervalUpdater(this.getApplicationContext());
		rtUpdater = new realTimeUpdater(this.getApplicationContext());
		countingDown = new countDownTimer(this.getApplicationContext());
		Log.d(TAG,"onCreate'd");
	}

	

	@Override
	public synchronized void onStart(Intent intent, int startId) {
	super.onStart(intent, startId);
	
		if(!this.isRunning){
			this.isRunning =true;
		update.start();
		countingDown.time();
		
		}
		Log.d(TAG,"onStart'd");
	}
	
	@Override
	public synchronized void onDestroy() {
	super.onDestroy();
	
		if(this.isRunning){
		update.interrupt();
		}
		Log.d(TAG,"onDestry'd");
	}
	
	
class Updater extends Thread{
	Calendar calendar = Calendar.getInstance();
	   
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minute = calendar.get(Calendar.MINUTE);
	SQLiteDatabase DB;
	
		public void run()
		{
			while(isRunning)
			{
				try{
					Log.d(TAG,"Run'ing");
					//update database for Leopardstown intervals
					inUpdater.leopardstown_fromDestination();
					inUpdater.leopardstown_fromAirport();
					
					//updating database for ballsbridge intervals
					inUpdater.ballsbridge_fromDes();
					inUpdater.ballsbridge_fromAirport();
					
					//updating database for cork intervals
					inUpdater.cork_fromDes();
					inUpdater.cork_fromAirport();
					
					//update firststops
					rtUpdater.FirstStopUpdater();
					
					// add firtstop time every other stop time
					rtUpdater.addStartTimeToStops();
					
					Thread.sleep(30000);
				}catch(InterruptedException e)
				{
					isRunning =false;
				}
			}//while loop
		}//run method
	}//thread class

}//service class
