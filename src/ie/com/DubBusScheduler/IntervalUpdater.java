package ie.com.DubBusScheduler;

import java.util.Calendar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class IntervalUpdater {
private DatabaseHelper db;
private SQLiteDatabase DB;

	public IntervalUpdater(Context context)
	{
		
		db = new DatabaseHelper(context);
		DB = db.getReadableDatabase();
	
	}

Calendar calendar = Calendar.getInstance();

int hour = calendar.get(Calendar.HOUR_OF_DAY);
int minute = calendar.get(Calendar.MINUTE);

//methods for updating intervals for Leopardstown from Des to the airport 
		public void leopardstown_fromDestination()
		{
			if(hour >=00 && hour<04 || minute==00)
  		{
  			
  		        String sql = "UPDATE LeapIntervals SET from_Destination = '60' WHERE Route='leopardstown';";
  		        DB.execSQL(sql);
  		}
  		else if(hour >=04 && hour<06 || minute==00)
  		{
 			 
 		        String sql = "UPDATE LeapIntervals SET from_Destination = '20' WHERE Route='leopardstown';";
 		        DB.execSQL(sql);
 		    } 
  		else if(hour >=06 && hour<20 || minute==00)
  		{
 			
 		        String sql = "UPDATE LeapIntervals SET from_Destination = '15' WHERE Route='leopardstown';";
 		        DB.execSQL(sql);
 		    }
  		else if(hour >=20 && hour<24 || minute==00)
  		{
 			 
 		        String sql = "UPDATE LeapIntervals SET from_Destination = '20' WHERE Route='leopardstown';";
 		        DB.execSQL(sql);
 		    }
			
		}
		
		//method for updating intervals for LeopardsTown from Airport to Dest
				public void leopardstown_fromAirport()
				{
					if(hour >=00 && hour<=03 ||  minute ==45)
		    		{
		    			 
		    		        String sql = "UPDATE LeapIntervals SET from_Airport = '60' WHERE Route='leopardstown';";
		    		        DB.execSQL(sql);
		    		}
		    		else if(hour >=04 && hour<=05  ||  minute ==45)
		    		{
		   			 
				        String sql = "UPDATE LeapIntervals SET from_Airport = '20' WHERE Route='leopardstown';";
				        DB.execSQL(sql);
		    		}
		    		else if(hour >=06 &&  hour<=19 ||   minute ==45 )
		    		{
		   			 
				        String sql = "UPDATE LeapIntervals SET from_Airport = '15' WHERE Route='leopardstown';";
				        DB.execSQL(sql);
		    		}
		    		else if(hour >=20 &&hour<=23||  minute ==45)
		    		{
		   			 
				        String sql = "UPDATE LeapIntervals SET from_Airport = '20' WHERE Route='leopardstown';";
				        DB.execSQL(sql);
		    		}
					
				}
				
				//method for updating intervals for ballsbridge from Dest to Airport
				public void ballsbridge_fromDes()
				{
					if(hour >=00 && hour<=03 ||  minute ==30)
		    		{
		    			 
		    		        String sql = "UPDATE LeapIntervals SET from_Destination = '60' WHERE Route='ballsbridge';";
		    		        DB.execSQL(sql);
		    		}
		    		else if(hour >=04 && hour<=05)
		    		{
		   			 
				        String sql = "UPDATE LeapIntervals SET from_Destination = '20' WHERE Route='ballsbridge';";
				        DB.execSQL(sql);
		    		}
		    		else if(hour >=06 && hour<=19)
		    		{
		   			 
				        String sql = "UPDATE LeapIntervals SET from_Destination = '15' WHERE Route='ballsbridge';";
				        DB.execSQL(sql);
		    		}
		    		else if(hour >=20 && hour<=23)
		    		{
		   			 
				        String sql = "UPDATE LeapIntervals SET from_Destination = '20' WHERE Route='ballsbridge';";
				        DB.execSQL(sql);
		    		}
					
				}
				//method for updating intervals for ballsbridge from Airport to Dest
				public void ballsbridge_fromAirport()
				{
					if(hour >=00 && hour<=03  || minute ==30)
		    		{
		    			 
		    		        String sql = "UPDATE LeapIntervals SET from_Airport = '60' WHERE Route='ballsbridge';";
		    		        DB.execSQL(sql);
		    		}
		    		else if(hour >=04 && hour<=06 )
		    		{
		   			 
				        String sql = "UPDATE LeapIntervals SET from_Airport = '20' WHERE Route='ballsbridge';";
				        DB.execSQL(sql);
		    		}
		    		else if(hour >=07 &&  hour<=20)
		    		{
		   			 
				        String sql = "UPDATE LeapIntervals SET from_Airport = '15' WHERE Route='ballsbridge';";
				        DB.execSQL(sql);
		    		}
		    		else if(hour >=21 && hour<=23|| minute>=10 && minute <=30)
		    		{
		   			 
				        String sql = "UPDATE LeapIntervals SET from_Airport = '20' WHERE Route='ballsbridge';";
				        DB.execSQL(sql);
		    		}
					
				}
				//method for updating intervals for cork from Dest to Airport
				public void cork_fromDes()
				{
					if(hour >=01 && hour<=06  || minute ==00)
		    		{
		    			 
		    		        String sql = "UPDATE LeapIntervals SET from_Destination = '420' WHERE Route='cork';";
		    		        DB.execSQL(sql);
		    		}
		    		else if(hour >=07 && hour<=19  || minute ==00)
		    		{
		    			 
		    		        String sql = "UPDATE LeapIntervals SET from_Destination = '120' WHERE Route='cork';";
		    		        DB.execSQL(sql);
		    		}
					
				}
				//method for updating intervals for cork from Airport to Dest
				public void cork_fromAirport()
				{
					String sql = "UPDATE LeapIntervals SET from_Airport = '120' WHERE Route='cork';";
    		        DB.execSQL(sql);
    		      
    		        
				}
				
				
}
