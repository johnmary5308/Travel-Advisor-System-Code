import java.util.ArrayList;

public class Comments {
	
	//attributes
		private String Title;
		private String Description;
		private int Score;
		private String userLogin;
		private String dateandtime;
		private dataStorage data;
		//private ArrayList<Attraction> atts = new ArrayList<Attraction>();
		//private ArrayList<Comments> mycomments = new ArrayList<Comments>();
		//private ArrayList<Double> myscores = new ArrayList<Double>();
		//double sum = 0;
		

		
	public Comments(String title, String description, int score, String userLogin, String dateandtime) {
		super();
		Title = title;
		Description = description;
		Score = score;
		this.userLogin = userLogin;
		this.dateandtime = dateandtime;
	}
	
	//display reviews per attraction
//	public void reviews()
//	{
//		mycomments = data.getReviews(Title);
//		int sum = 0;
//		if(mycomments.size()>=1)
//		{
//			for(int i = 0; i < mycomments.size(); i++)
//			{
//					sum = sum + mycomments.get(i).getScore();		
//			}
//			int Rating1 = sum/mycomments.size();
//			Double Rating = Double.valueOf(Rating1);
////			System.out.println(Rating);
//			data.updateRating( Rating, Title);
//		}
//		
//		
//	}
	
	
	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public int getScore() {
		return Score;
	}

	public void setScore(int score) {
		Score = score;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getDateandtime() {
		return dateandtime;
	}

	public void setDateandtime(String dateandtime) {
		this.dateandtime = dateandtime;
	}

	public String toString() { 
		
	    return "Title: " + Title+ ";" + " Description: " + Description+ ";" + " Score: " + Score+ ";" + " User: " + this.userLogin+ ";" + " Date: " +  this.dateandtime;
	    
	} 
	
	//average score of attraction
	
	
	
	//get all the score into an arraylist and manipulate from there

	
	
}
