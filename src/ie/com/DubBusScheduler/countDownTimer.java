package ie.com.DubBusScheduler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class countDownTimer{
	private static final String TAG = countDownTimer.class.getSimpleName();
	DatabaseHelper db;
	SQLiteDatabase DB;

	
	
	public countDownTimer(Context context)
	{
		db = new DatabaseHelper(context);
		
		DB = db.getReadableDatabase();
	}
	
	//run onThinck every minute interval here
	
	
	//get realtimeStartTime from database add them in a list and start count 
	public synchronized void time()
	{
		//LEOPARDSTOWN::::::
		String sqlL = "SELECT idleopardstown, realTimeStart FROM leopardstown;";
		Cursor resultL = DB.rawQuery(sqlL, null);
	
		while(resultL.moveToNext()){
			int idleopardstown = Integer.parseInt(resultL.getString(0));
			int realTimeStartMinute = Integer.parseInt(resultL.getString(1));
			String RouteL = "leopardstown";
			
			new countDownMinute(realTimeStartMinute, idleopardstown, RouteL ).start();
		
		}
		
				//BALLSBRIDGE::::::
				String sqlB = "SELECT idballsbridge, realTimeStart FROM ballsbridge;";
				Cursor resultB = DB.rawQuery(sqlB, null);
			
				while(resultB.moveToNext()){
					int idballsbridge = Integer.parseInt(resultB.getString(0));
					int realTimeStartMinute = Integer.parseInt(resultB.getString(1));
					String RouteB = "ballsbridge";
					
					new countDownMinute(realTimeStartMinute, idballsbridge, RouteB ).start();
				
				}
				
				//CORK::::::
				String sqlC = "SELECT idcork, realTimeStart FROM cork;";
				Cursor resultC = DB.rawQuery(sqlC, null);
			
				while(resultC.moveToNext()){
					int idcork = Integer.parseInt(resultC.getString(0));
					int realTimeStartMinute = Integer.parseInt(resultC.getString(1));
					String RouteC = "cork";
					
					new countDownMinute(realTimeStartMinute, idcork, RouteC ).start();
				
				}
		resultB.close();
		resultC.close();
		resultL.close();
		
	}
	
	class countDownMinute extends Thread
	{
		
		
		int minutes = 0;
		int counterMinute = 0;
		int counterSeconds = 0;
		int aMinute = 60;
		int db_id = 0;
		int storedMinute = 0;
		int nextMinute =0;
		String route ="";
		public boolean isRunning = false;
		
		public countDownMinute(int minutes, int id, String route)
		{
			this.minutes = minutes*aMinute;
			db_id = id;
			this.route = route;
			storedMinute = minutes*aMinute;
			
			isRunning = true;
		}
		
			public void run()
			{
				while(isRunning){
					Log.d(TAG,"runing'Cd");
					
					
				while(minutes > 0)
				{
					try{
						Thread.sleep(1000);
					}
					catch(InterruptedException e){
						
						isRunning = false;
					}
					minutes--;
					counterMinute++;
					//what to do every minute which is update realtime in the database
					//for routes
					onTimeThick();
					
				}
				
				//what to do when a set time is done
				 onTimeFinish();
			}
			
		}
			
			private void onTimeThick()
			{
			 
				//update database every minute here
				if(counterMinute == aMinute)
				{
					
					long milliCurrentTime  = minutes*1000;
					long milliNextTime = 0;
					String time = formatTime(milliCurrentTime ); 
					String NextTime = formatTime(milliNextTime);
					
					//sql statement here to update database here
					String SQL = "UPDATE "+route+" SET realTime = '"+time+"', nextRealTime = '"+NextTime+"' WHERE id"+route+" = '"+db_id+"' ;";
					DB.execSQL(SQL);
					counterMinute = 0;
				}
				
			}
			
			private String formatTime(long millis) 
			{
				  String output = "00:00";
				  long seconds = millis / 1000;
				  long minutes = seconds / 60;
				  long hours = minutes / 60;
		
				  seconds = seconds % 60;
				  minutes = minutes % 60;
				  hours = hours % 60;
		
				 
				  String minutesD = String.valueOf(minutes);
				  String hoursD = String.valueOf(hours); 
		
				  
				  if (minutes < 10)
				    minutesD = "0" + minutes;
				  if (hours < 10)
				    hoursD = "0" + hours;
		
				  output = hoursD + ":" + minutesD;
				 
				  
				  return output;
			}	
			
			private void onTimeFinish()
			{
				String sql = "SELECT realTimeStart FROM "+route+" WHERE id"+route+" = '"+db_id+"';";
				
				Cursor r = DB.rawQuery(sql, null);
				
				r.move(1);
				int temp = Integer.parseInt(r.getString(0));
				int tempMinute = temp*aMinute;
				if(minutes==0)
				{
					
						minutes = tempMinute;
						storedMinute = tempMinute;	
				}
			
				r.close();
			
			}
			
	}
}
	


