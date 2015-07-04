package ie.com.DubBusScheduler;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class CustumPinPoint extends ItemizedOverlay<OverlayItem> {

	private ArrayList<OverlayItem> pinpoint = new ArrayList<OverlayItem>();
	
	private Context c;
	
	public CustumPinPoint(Drawable defaultMaker) {
		super(boundCenter(defaultMaker));
		// TODO Auto-generated constructor stub
	}
	
	public CustumPinPoint(Drawable m, Context context){
		this(m);
		c = context;
	}

	@Override
	protected OverlayItem createItem(int i) {
		// TODO Auto-generated method stub
		return pinpoint.get(i);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return pinpoint.size();
	}
	
	public void insertPinpoint(OverlayItem item)
	{
		pinpoint.add(item);
		this.populate();
	}

}
