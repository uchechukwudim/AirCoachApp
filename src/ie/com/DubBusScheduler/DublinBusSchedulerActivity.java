package ie.com.DubBusScheduler;




import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.content.Context;
import android.content.Intent;

public class DublinBusSchedulerActivity extends Activity implements OnItemSelectedListener {
    /** Called when the activity is first created. */
	
	//declaring variables:::::::::::::::::::::::::::::
	Spinner Rout;
	CheckBox Destinationcheck1;
	CheckBox Destinationcheck2;
	String temp;
	int checkboxVal;


	
  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searcher);
        
       
        //finding source from XML::::::::;;;:::::::::::::::::::::::::::::
        Rout = (Spinner)findViewById(R.id.Route);
        
        Button search= (Button)findViewById(R.id.Searcher);
        Button Lu= (Button)findViewById(R.id.lu);
        Button Train = (Button)findViewById(R.id.IT);
        Destinationcheck1 = (CheckBox)findViewById(R.id.checkBox1);
        Destinationcheck2 = (CheckBox)findViewById(R.id.checkBox2);
        ArrayAdapter<CharSequence> adapter =  ArrayAdapter.createFromResource(this, R.array.entries, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Rout.setAdapter(adapter);
       
        //checking if the checkboxes are checked
       
        
        //methods for listeners::::::::::::::::::::::::::::::::::::::::::::::::
        Rout.setOnItemSelectedListener(this);
        search.setOnClickListener(new OnClickListener(){ 

			@Override
			public void onClick(View v) {
				if(Destinationcheck1.isChecked() && !Destinationcheck2.isChecked())
		        {
		        	checkboxVal = 1;
		        }
		        else if(Destinationcheck2.isChecked() && !Destinationcheck1.isChecked())
		        {
		        	checkboxVal = 2;
		        }
		        else if(Destinationcheck1.isChecked() && Destinationcheck2.isChecked()){
		        	checkboxVal = 0;
		        }
				
				Intent gotoStopList = new Intent(getApplicationContext(), CustomListView.class);
				gotoStopList.putExtra("info", temp);
				gotoStopList.putExtra("checkVal", checkboxVal);
				startActivity(gotoStopList);	
			}	
        });
        
        Lu.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				Intent map = new Intent(getApplicationContext(), LuasConnt.class);
				
				startActivity(map);
			}

			
        	
        });
        
        Train.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
			
				Intent train = new Intent(getApplicationContext(), TrainConnect.class);
				
				startActivity(train);
			}

			
        });
        
     
        
    }//Oncreate method ends here
    
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position,
					long id) {
				temp =(String)parent.getItemAtPosition(position);	
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
}//class ends here