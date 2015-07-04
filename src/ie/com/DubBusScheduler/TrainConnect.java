package ie.com.DubBusScheduler;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TrainConnect extends ListActivity {
	DatabaseHelper db;
	String Tdb_id;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trainconnect);
        
       List<String> Tstops = new ArrayList<String>();
        db = new DatabaseHelper(this);
        
        SQLiteDatabase DB = db.getReadableDatabase(); 
        
        String sql = "SELECT TrainStation, idTrainConnect  FROM TrainConnect;";
        
        Cursor result = DB.rawQuery(sql, null);
        
        while(result.moveToNext())
        {
        	Tstops.add(result.getString(0));
        	
        	Tdb_id = result.getString(1);
        }
        
        result.close();
        db.Close();
        
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Tstops));
	}
	
	@Override // what will happen when list is clicked
    public void onListItemClick(ListView parent, View v, int position, long id) { 
    	
    	Intent go = new Intent(getApplicationContext(), TrainDetails.class); // creating an intent setting action and category starting activity.
    	
    	go.putExtra("Tdb_id", Tdb_id);
    	startActivity(go);
    	
    }
}
