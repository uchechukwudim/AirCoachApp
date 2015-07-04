package ie.com.DubBusScheduler;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class LuasDetails extends Activity{
	DatabaseHelper db;
	TextView Tstation;
	TextView Bstop;
	TextView taxiTime;
    TextView walkTime; 
    TextView RealTime;
    
    String db_id;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luasdetails);
        
        Tstation = (TextView)findViewById(R.id.tstationLu);
        Bstop = (TextView)findViewById(R.id.bstopLu);
        taxiTime = (TextView)findViewById(R.id.taxiTimeLu);
        walkTime = (TextView)findViewById(R.id.walkTimeLu);
        RealTime = (TextView)findViewById(R.id.realTimLu);
        
        
        Bundle bundle = getIntent().getExtras();
        
        db_id = bundle.getString("Tdb_id");
        
        db = new DatabaseHelper(this); 
     
        SQLiteDatabase DB = db.getReadableDatabase();
        
        String sql = "SELECT * FROM LuasConnect WHERE idluasconnect = '"+db_id+"';";
        
        Cursor result = DB.rawQuery(sql,null);
        
       result.move(1);
       
        	Tstation.setText(result.getString(1));
        	Bstop.setText(result.getString(2));
        	taxiTime.setText(result.getString(3));
        	walkTime.setText(result.getString(4));
        	String stop = result.getString(2);
    
        sql = "select realtime from leopardstown where Stops = '"+stop+"' AND destinationfrom = '1'";
    
        result = DB.rawQuery(sql, null);
        result.move(1);
        RealTime.setText(result.getString(0));
       
        
	}

}
