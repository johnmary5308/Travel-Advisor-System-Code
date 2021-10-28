import java.util.ArrayList;

//declare these methods 
public interface dataStorage {

	void createaccount(String LoginID, String Password, String type, String Tag1, String Tag2);
	void createattraction(String Title, String Description, String Location, String Tag);
	void myFavorites(String User, String Title, String Description, String Location, String Tag, Double Rating, String Status);
	void QA (String loginID, String Title, String Question);
	User login(String id, String password);
	ArrayList<Attraction> getAttractions();
	ArrayList<Attraction> getAvAttractions(String city, String tag);
	ArrayList<Attraction> youmaylike(String Tag1, String Tag2);
	ArrayList<favoriteAttraction> favouriteDestination(String user);
	ArrayList<Ques> getQuestions(String attraction);
	ArrayList<Answer> getAnswers(String User);
	ArrayList<Attraction> getAvAttractionsByCity(String city);
	ArrayList<Attraction> getAvAttractionsByTag(String tag);
	//User getUser();
	//change attraction status
	void updateStatus(String title, String status);
	void updateAnswer(String loginID, String question, String answer);
	Attraction ViewAttractions(String title);
	void postComment(String Title, String Description, int score,String loginID, String dateTime);
	ArrayList<Comments> getReviews(String Title);
	void updateRating(String Title);
	void updateQAStatus(String User, String question);
	//void updateRating(Double rating , String title);
	
	
}
