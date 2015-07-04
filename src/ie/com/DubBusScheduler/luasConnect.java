package ie.com.DubBusScheduler;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

public class luasConnect extends MapActivity{
	MapView mapView;
	long start;
	long stop;
	int x,y;
	MyLocationOverlay compass;
	MapController Controller;
	GeoPoint touchPoint;
	List<Overlay> overLay;
	Drawable draw;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.main);

		 mapView = (MapView) findViewById(R.id.map);
		 mapView.setBuiltInZoomControls(true);
		 
		Touch touch = new Touch();
		
		overLay = mapView.getOverlays();
		overLay.add(touch);
		
		compass = new MyLocationOverlay(luasConnect.this,mapView);
		overLay.add(compass);
		
		Controller = mapView.getController();
		
		double lati = 53.346773 * 1E6;
		double longi = -6.292012 * 1E6;
		GeoPoint point =  new GeoPoint((int)lati, (int)longi);
		Controller.animateTo(point);
		Controller.setZoom(6);
		 
		draw = getResources().getDrawable(R.drawable.pin);
		
		OverlayItem overlayItem = new OverlayItem(point, "am here", "sencond");
		CustumPinPoint pPoint = new CustumPinPoint(draw, luasConnect.this);
		pPoint.insertPinpoint(overlayItem);
		overLay.add(pPoint);
		
		//mapView.setStreetView(true);
		
	}
		
			//:::::::::::::::::for compass
			@Override
			protected void onPause() {
				// TODO Auto-generated method stub
					 compass.disableCompass();
				super.onPause();
			}
			@Override
			protected void onResume() {
				// TODO Auto-generated method stub
				compass.enableCompass();
				super.onResume();
			}
	
			@Override
			 protected boolean isRouteDisplayed()
			 {
			 return false;
			 }
			//:::::::::::::::::::::::::::::::::::::::::
	
			//things to do when the map is touched
	class Touch extends Overlay{
		public boolean onTouchEvent(MotionEvent e, MapView m)
		{
			if(e.getAction() == MotionEvent.ACTION_DOWN)
			{
				start = e.getEventTime();
				x = (int)e.getX();
				y = (int)e.getY();
				
				touchPoint = mapView.getProjection().fromPixels(x, y);
			}
			
			if(e.getAction() == MotionEvent.ACTION_UP)
			{
				stop = e.getEventTime();
				
			}
			
			if(start - stop > 60000)
			{
				
			}
			
			return false;
		}
	}
	
}
