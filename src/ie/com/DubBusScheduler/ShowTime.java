package ie.com.DubBusScheduler;

public class ShowTime {
	private String realTime = "";
	 private String address = "";
	 private String Destination = "";
	 private String route;

	 public void setRealTime(String time) {
	  this.realTime = time;
	 }

	 public String getRealTime() {
	  return realTime;
	 }

	 public void setAddress(String address) {
	  this.address= address;
	 }

	 public String getAddress() {
	  return address;
	 }

	 public void setDestination(String Destination) {
	  this.Destination = Destination;
	 }

	 public String getDestination() {
	  return Destination;
	 }
	 
	 public void setRoute(String route) {
		  this.route = route;
		 }

		 public String getRoute() {
		  return route;
		 }
}
