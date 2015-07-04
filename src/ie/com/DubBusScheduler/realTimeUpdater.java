package ie.com.DubBusScheduler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class realTimeUpdater{
		DatabaseHelper db;
		SQLiteDatabase DB;
		String fromDestIntervalTim, fromAirportIntervalTime;
					
		String leopardstown = "leopardstown";
		String ballsbridge = "ballsbridge";
		String cork = "cork";
		
		public realTimeUpdater(Context context)
		{
			db = new DatabaseHelper(context);
			
			DB = db.getReadableDatabase();
			
		}
		
		
		//it update first stops for Routes gotten from leapInterveral
		public void FirstStopUpdater()
		{
			//getting time from leapintervals table leopardstown
			String sqlL = "SELECT * FROM LeapIntervals WHERE Route = 'leopardstown';";
			Cursor resultL = DB.rawQuery(sqlL, null);
			while(resultL.moveToNext())
			{
				
				fromAirportIntervalTime = resultL.getString(1);
				fromDestIntervalTim = resultL.getString(2);
			}
			
			//:::::::LEOPARDSTOWN START STOP UPDATED
			stopUpdater(leopardstown, fromDestIntervalTim, 1);
			stopUpdater(leopardstown, fromDestIntervalTim, 18);
			
			//getting time from leapintervals table ballsbridge
			String sqlB = "SELECT * FROM LeapIntervals WHERE Route = 'ballsbridge';";
			Cursor resultB = DB.rawQuery(sqlB, null);
			while(resultB.moveToNext())
			{
				
				fromAirportIntervalTime = resultB.getString(1);
				fromDestIntervalTim = resultB.getString(2);
			}
			
			//:::::::BALLSBRIDGE START STOP UPDATED
			stopUpdater(ballsbridge, fromDestIntervalTim, 1);
			stopUpdater(ballsbridge, fromDestIntervalTim, 11);
			
			//getting time from leapintervals table cork
			String sqlC = "SELECT * FROM LeapIntervals WHERE Route = 'cork';";
			Cursor resultC = DB.rawQuery(sqlC, null);
			while(resultC.moveToNext())
			{
				
				fromAirportIntervalTime = resultC.getString(1);
				fromDestIntervalTim = resultC.getString(2);
			}
			
			//:::::::CORK START STOP UPDATED
			stopUpdater(cork, fromDestIntervalTim, 1);
			stopUpdater(cork, fromDestIntervalTim, 12);
			
			resultL.close();
			resultB.close();
			resultC.close();
			
		}
		
		//method to process sql statements to update first stops
		private void stopUpdater(String Route, String IntervalTime, int column)
		{
			//updating first stop in Routes table here
			String ql = "UPDATE "+Route+" SET estTimefromdest = '"+IntervalTime+"' " 
			+ "WHERE id"+Route+" ='"+column+"' ;";
			DB.execSQL(ql);
			
			
		}
		
		//add every other stop's estimated time with time gotten from leapIntervals table
		public void addStartTimeToStops()
		{
			String fromDestIntervalTime ="";
			String fromAirportIntervalTime="";
			String leopardstown = "leopardstown";
			String ballsbridge = "ballsbridge";
			String cork = "cork";
			
			//::::::LEOPARDSTOWN:::::::::::::
			//get time from leapIntervals table to be added
			String sqL = "SELECT * FROM LeapIntervals WHERE Route = 'leopardstown';";
			Cursor resultL = DB.rawQuery(sqL, null);
			
		
			while(resultL.moveToNext())
			{
				fromDestIntervalTime = resultL.getString(2);
				fromAirportIntervalTime = resultL.getString(1);
			}
			//update realtimes for leopardstown for all stops
			RealTimeUpdater(fromDestIntervalTime , leopardstown, 1);
			RealTimeUpdater(fromAirportIntervalTime , leopardstown, 2);
			
			//::::::BALLSBRIDGE::::::::::::::
			//get time from leapIntervals table
			String sqB = "SELECT * FROM LeapIntervals WHERE Route = 'ballsbridge';";
			Cursor resultB = DB.rawQuery(sqB, null);
			while(resultB.moveToNext())
			{
				fromDestIntervalTime = resultB.getString(2);
				fromAirportIntervalTime = resultB.getString(1);
			}
			//update realtimes for ballsbridge for all stops
			RealTimeUpdater(fromDestIntervalTime , ballsbridge, 1);
			RealTimeUpdater(fromAirportIntervalTime , ballsbridge, 2);
			
			//::::::CORK::::::::::::::::::::::
			//get time from leapIntervals table
			String sqC = "SELECT * FROM LeapIntervals WHERE Route = 'cork';";
			Cursor resultC = DB.rawQuery(sqC, null);
			while(resultC.moveToNext())
			{ 
				fromDestIntervalTime = resultC.getString(2);
				fromAirportIntervalTime = resultC.getString(1);
			}
			//update realtimes for cork for all stops
			RealTimeUpdater(fromDestIntervalTime , cork, 1);
			RealTimeUpdater(fromAirportIntervalTime , cork, 2);
			
			resultL.close();
			resultB.close();
			resultC.close();
			
		}
		
		
		//method to process sql statements to update realtime and added intervTime with EstTime
		//addition of both is result of realtime
		private void RealTimeUpdater(String IntervalTime, String route, int destFrom)
		{
			int newTime =0;
			
			String ql = "SELECT estTimefromdest FROM "+route+" WHERE destinationFrom ='"+destFrom+"';";
			//getting estimate from database, tables: Routes
			Cursor r = DB.rawQuery(ql, null);
		
			String EstTime = "";
				r.moveToNext();
				
				EstTime = r.getString(0);
				//updating Realtime for Route
				String SqL = "UPDATE "+route+" SET realTimeStart = '"+IntervalTime+"' " +
						"WHERE estTimefromdest = '"+EstTime+"' AND DestinationFrom ='"+destFrom+"';";
				DB.execSQL(SqL);
				
				while(r.moveToNext())
				{
					//moving through the cursor		
					//estimated time from route
					EstTime = r.getString(0); 
					
					//change to integer
					int estTime  = Integer.parseInt(EstTime);
					
					//change interval time to integer
					int intervalTime = Integer.parseInt(IntervalTime);
					
					//time added
					newTime = estTime + intervalTime;
					
					
					//updating Realtime for Route
					String SQL = "UPDATE "+route+" SET realTimeStart = '"+newTime+"' " +
							"WHERE estTimefromdest = '"+estTime+"' AND DestinationFrom ='"+destFrom+"';";
					DB.execSQL(SQL);
					
				 }
				
			
				
				r.close();
				
				
		}
		
		
}
