package ie.com.DubBusScheduler;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class TrainDetails extends Activity{
	DatabaseHelper db;
	TextView Tstation;
	TextView Bstop;
	TextView taxiTime;
    TextView walkTime; 
    TextView RealTime;
    
    String db_id;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.traindetails);
        
        Tstation = (TextView)findViewById(R.id.tstation);
        Bstop = (TextView)findViewById(R.id.bstop);
        taxiTime = (TextView)findViewById(R.id.taxiTime);
        walkTime = (TextView)findViewById(R.id.walkTime);
        RealTime = (TextView)findViewById(R.id.realTim);
        
        
        Bundle bundle = getIntent().getExtras();
        
        db_id = bundle.getString("Tdb_id");
        
        db = new DatabaseHelper(this); 
     
        SQLiteDatabase DB = db.getReadableDatabase();
        
        String sql = "SELECT * FROM TrainConnect WHERE idTrainConnect = '"+db_id+"';";
        
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
