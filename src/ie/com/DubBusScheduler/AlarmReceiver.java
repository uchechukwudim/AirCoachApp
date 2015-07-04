package ie.com.DubBusScheduler;


import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

	private static final String TAG = AlarmReceiver.class.getSimpleName();
	 AlarmManager am;

		@Override
		
		public void onReceive(Context context, Intent intent) {
				if("android.intent.action.BOOT_COMPLETED".equals(intent.getAction()))
				  {
					ComponentName comp = new ComponentName(context.getPackageName(), myService.class.getName());
					ComponentName service = context.startService(new Intent().setComponent(comp));
					Log.d(TAG,"Received'S");
						if(null==service)
						{
							Log.d(TAG, "Could not start service " + comp.toString());
						}
						
						
						 // get a Calendar object with current time
					      Calendar cal = Calendar.getInstance();
					      // add 5 minutes to the calendar object
					      cal.add(Calendar.SECOND, 30);
					      Intent it = new Intent(context, myService.class);
					      // In reality, you would want to have a static variable for the request code instead of 192837
					      PendingIntent sender = PendingIntent.getService(context, 0, it,PendingIntent.FLAG_UPDATE_CURRENT);
					      
					      // Get the AlarmManager service
					      AlarmManager am = (AlarmManager)context.getSystemService(Service.ALARM_SERVICE);
					      am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), sender);
							
				 }
				
			
	}

		
}
