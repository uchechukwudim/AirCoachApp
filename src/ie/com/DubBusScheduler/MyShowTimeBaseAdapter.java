package ie.com.DubBusScheduler;



import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyShowTimeBaseAdapter extends BaseAdapter{
	private static ArrayList<ShowTime> searchArrayList;
	 
	 private LayoutInflater mInflater;

	 public MyShowTimeBaseAdapter(Context context, ArrayList<ShowTime> results) {
	  searchArrayList = results;
	  mInflater = LayoutInflater.from(context);
	 }

	 public int getCount() {
	  return searchArrayList.size();
	 }

	 public Object getItem(int position) {
	  return searchArrayList.get(position);
	 }

	 public long getItemId(int position) {
	  return position;
	 }

	 public View getView(int position, View convertView, ViewGroup parent) {
	  ViewHolder holder;
	  if (convertView == null) {
	   convertView = mInflater.inflate(R.layout.showtime_row_view, null);
	   holder = new ViewHolder();
	   holder.txtRealTime = (TextView) convertView.findViewById(R.id.realtime);
	   holder.txtAddress = (TextView) convertView.findViewById(R.id.address);
	   holder.txtDestination = (TextView) convertView.findViewById(R.id.destination);
	   holder.txtRoute = (TextView) convertView.findViewById(R.id.route);

	   convertView.setTag(holder);
	  } else {
	   holder = (ViewHolder) convertView.getTag();
	  }
	  
	  holder.txtRealTime.setText(searchArrayList.get(position).getRealTime());
	  holder.txtAddress.setText(searchArrayList.get(position).getAddress());
	  holder.txtDestination.setText(searchArrayList.get(position).getDestination());
	  holder.txtRoute.setText(searchArrayList.get(position).getRoute());

	  return convertView;
	 }

	 static class ViewHolder {
	  TextView txtRealTime;
	  TextView txtAddress;
	  TextView txtDestination;
	  TextView txtRoute;
	 }
}
