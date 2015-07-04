package ie.com.DubBusScheduler;

import java.util.ArrayList;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.view.View;

public class CustomListView extends Activity {
	DatabaseHelper db;
	String infoRoute;
	int checkboxVal;
	String TABLE;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stoplistview);
        
        //get information from previous page
        Bundle data = getIntent().getExtras();
        
        infoRoute = data.getString("info");
        checkboxVal = data.getInt("checkVal");
        
        //give information taking from database to arraylist
        ArrayList<SearchResults> searchResults = GetSearchResults();
        
       //getting view from xml
        final ListView list = (ListView) findViewById(R.id.ListView01);
        list.setAdapter(new MyCustomBaseAdapter(this, searchResults));
        
        // what to do when an item is click on the list
        list.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				Intent go = new Intent(getApplicationContext(), TimeUpdater.class);
				SearchResults s =(SearchResults)parent.getItemAtPosition(position);
				
				String stop = s.getAddress();
				go.putExtra("stop", stop);
				go.putExtra("checkBox", checkboxVal);
				go.putExtra("route", infoRoute);
				startActivity(go);
				
			}
        });
        
        
        
    }
   
    //method getting information from data base
    private ArrayList<SearchResults> GetSearchResults(){
    	
    	TABLE = infoRoute;
    	
    	db = new DatabaseHelper(this);
        
        SQLiteDatabase DB = db.getReadableDatabase();
        String sql = "";
        if(checkboxVal==1 || checkboxVal==2){
         sql = "SELECT * FROM "+ TABLE+ " WHERE DestinationFrom = "+checkboxVal+";";
        }
        else if(checkboxVal==0)
        {
        	sql = "SELECT * FROM "+ TABLE +";";
        }
        Cursor result = DB.rawQuery(sql, null);
        startManagingCursor(result);
        
        ArrayList<SearchResults> results = new ArrayList<SearchResults>();
        db.Close();
       
      int countStops = 0;
        while(result.moveToNext())
        {
        	countStops++;
        	SearchResults sr1 = new SearchResults();
        	sr1.setStop("		Stop Number From Destination:		"+countStops);
            sr1.setAddress(result.getString(4));
            sr1.setTime("		Estimated Time First Stop:   "+ result.getString(5));
            results.add(sr1);
            
          
       }
       result.close();
     return results;
     
    }//GetSearchResult method ends here
   
}