
public class favoriteAttraction extends Attraction {
	
	public String user;

	public favoriteAttraction(String u, String tle, String d, String t, String l, Double r, String s) {
		super(tle, d, t, l, r, s);
		// TODO Auto-generated constructor stub
		user= u;
	}
	public String toString() { 
	    return "Title: " + super.getTitle() + "\nDescription: " + super.getDescription() + "\nTag: " + super.getTag() + "\nLocation: " + super.getLocation() + "";
	} 

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
