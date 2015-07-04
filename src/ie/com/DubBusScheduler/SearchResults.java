package ie.com.DubBusScheduler;

public class SearchResults {
	 private String stop = "";
	 private String address = "";
	 private String time = "";

	 public void setStop(String name) {
	  this.stop = name;
	 }

	 public String getStop() {
	  return stop;
	 }

	 public void setAddress(String address) {
	  this.address= address;
	 }

	 public String getAddress() {
	  return address;
	 }

	 public void setTime(String time) {
	  this.time = time;
	 }

	 public String getTime() {
	  return time;
	 }
	}