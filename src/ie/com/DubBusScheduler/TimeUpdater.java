package ie.com.DubBusScheduler;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class TimeUpdater extends Activity {
	DatabaseHelper db;
	SQLiteDatabase DB;
	
	TextView txt;
	String stop_id;
	int checkB = 0;
	String Route;
	String Destination;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.realtime);
		db = new DatabaseHelper(this);
		DB = db.getReadableDatabase();
		Bundle bundle = getIntent().getExtras();
		
		stop_id = bundle.getString("stop");
		checkB = bundle.getInt("checkBox");
		Route = bundle.getString("route");
		
		if(checkB==2)
		{
			Destination = ""+Route;
		}
		else if(checkB==1)
		{
			Destination = "AirPort";
		}
		txt =(TextView)findViewById(R.id.textView1);
		ArrayList<ShowTime> showTimeResults = getShowTimeResults();
		final ListView list = (ListView) findViewById(R.id.listView1);
        list.setAdapter(new MyShowTimeBaseAdapter(this, showTimeResults));
		
	}

	
	private ArrayList<ShowTime> getShowTimeResults()
	{
		ArrayList<ShowTime> results = new ArrayList<ShowTime>();
		String sql = "SELECT * FROM "+Route+" WHERE Stops = '"+stop_id+"' AND DestinationFrom = '"+checkB+"';";
		//String sql = "select * from  "+Route+" where stops ='Dawson Street' and destinationfrom =1;";
		ShowTime st = new ShowTime();
		ShowTime st1 = new ShowTime();
		Cursor r = DB.rawQuery(sql,null);
		//while(r.moveToNext())
		//{
			r.moveToNext();
			
			
			st.setAddress("Stop Address:    "+r.getString(4));
        	st.setRealTime("Bus Due Time:   "+r.getString(1));
            st.setRoute("Destination Route: "+Route);
            st.setDestination("Destination:  "+Destination);
            results.add(st);
              
            st1.setAddress("Stop Address:    "+r.getString(4));
        	st1.setRealTime("Next Bus Due Time:   "+r.getString(0));
            st1.setRoute("Destination Route: "+Route);
            st1.setDestination("Destination:  "+Destination);
            results.add(st1);
		//}
		
	
		
		r.close();
	
		return results;
	}
}
