package ie.com.DubBusScheduler;





import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	 static final String DATABASE_NAME ="AirCoachScheduler.db";
	 static final int DATABASE_VERSION = 1;
	 static final String TAG = "AirCoachScheduler/DatabaseHelper";
	 
	 static final String TABLEB = "ballsbridge";
	 static final String TABLEL = "leopardstown";
	 static final String TABLEC = "cork";
	 static final String TABLELeap = "LeapIntervals";
	 static final String TABLETc = "TrainConnect";
	 static final String TABLELc = "LuasConnect";
	 
	 SQLiteDatabase db;
	 static Context t;
	DatabaseHelper(Context context)
	{
		
		super(context, DATABASE_NAME, null, DATABASE_VERSION );
		db = getWritableDatabase();
	}
	
	DatabaseHelper()
	{
		super(t, DATABASE_NAME, null, DATABASE_VERSION );
		db = getWritableDatabase();
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		Log.d("TAG", "DatabaseHelper onCreate called");
		
		 db.execSQL("CREATE TABLE " + TABLEB + " ("
	 				+ "idballsbridge INTEGER PRIMARY KEY AUTOINCREMENT,"
	                 + "Stops TEXT,"
	                 + "EstTimefromDes TEXT,"
	                 + "DestinationFrom TEXT,"
	                 + "realTimeStart TEXT,"
	                 + "realTime TEXT,"
	                 + "nextRealTime TEXT,"
	                 + ");");
		 
		 db.execSQL("CREATE TABLE " + TABLEL + " ("
	 				+ "idleopardstown INTEGER PRIMARY KEY AUTOINCREMENT, "
	                 + "Stops TEXT,"
	                 + "EstTimefromDes TEXT,"
	                 + "DestinationFrom TEXT,"
	                 + "realTimeStart TEXT,"
	                 + "realTime TEXT,"
	                 + "nextRealTime TEXT,"
	                 + ");");
		 
		 db.execSQL("CREATE TABLE " + TABLEC + " ("
	 				+ "idcork INTEGER PRIMARY KEY AUTOINCREMENT,"
	                 + "Stops TEXT,"
	                 + "EstTimefromDes TEXT,"
	                 + "DestinationFrom TEXT,"
	                 + "realTimeStart TEXT,"
	                 + "realTime TEXT,"
	                 + "nextRealTime TEXT,"
	                 + ");");
		 
		 db.execSQL("CREATE TABLE " + TABLELeap + " ("
	 				+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
	                 + "Route TEXT,"
	                 + "from_Airport TEXT,"
	                 + "from_Destination TEXT,"
	                 + ");");
		 
		 db.execSQL("CREATE TABLE " + TABLETc + " ("
	 				+ "id"+TABLETc+" INTEGER PRIMARY KEY AUTOINCREMENT,"
	                 + "TrainStattion TEXT,"
	                 + "BusStops TEXT,"
	                 + "TimeByTaxi TEXT,"
	                 + "TimeByWalk TEXT,"
	                 + ");");
		 
		 db.execSQL("CREATE TABLE " + TABLELc + " ("
	 				+ "idluasconnect INTEGER PRIMARY KEY AUTOINCREMENT,"
	                 + "LuasStops TEXT,"
	                 + "BusStops TEXT,"
	                 + "TimeByTaxi TEXT,"
	                 + "TimeByWalk TEXT,"
	                 + ");");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	public void Close(){
		
	}
}
