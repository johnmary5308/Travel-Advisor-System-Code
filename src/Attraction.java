import java.util.ArrayList;
import java.util.Scanner;

public class Attraction {
	
	//attributes
	private String Title;
	private String Description;
	private String Location;
	private String Tag;
//	private String Status;
	private String Status;
//	private Double Rating;
	private Double Rating;
	//private dataStorage data;
	//an array of comments of this attraction
	private String question = "null";
	private String answer = "null";
	//private String sta= "unread";
	private ArrayList<Ques> quest = new ArrayList<Ques>();
	String log;
	
	
	//private ArrayList<Comments> mycomments = new ArrayList<Comments>();
	
	public Attraction(String tle, String d, String t, String l, Double r, String s) {
		
		Title = tle;
		Description = d;
		Location = l;
		Tag = t;
		Status = s;
		Rating = r;
		
	}
	//methods
	//questions and answers
	
	public void questionAndanswer(String loginID, dataStorage d)
	{
		Scanner input = new Scanner(System.in);
		String selection = "";
		Scanner input2= new Scanner(System.in);
		quest = d.getQuestions(Title);
        
		while(!selection.equals("x"))
		{
			
			System.out.println("q:Ask your questions");
			System.out.println("a:Answer a question");
			System.out.println("x:leave q and a");
			
			selection = input.next();
			
			if(selection.equals("q"))
	  		{
				System.out.println("Please enter a question");
	  			 question = input2.nextLine();
	  			d.QA(loginID, Title,  question);
	  		}
	        else if(selection.equals("a"))
	        {
	        	if(quest.size() >=1)
	        	{
	        		Scanner input1 = new Scanner(System.in);
	        		String selection1 = "";
	        		for(int i = 0; i < quest.size(); i++)
	            	{
	        			System.out.printf("%s: select %s to answer\n", i+1, quest.get(i).getQuestion());
	            	}
	        			selection1 = input1.next();
	        			
	        			if(isInteger(selection1))
	        	  		{
	        				int intSelection1 = Integer.parseInt(selection1);
	        				question = quest.get(intSelection1-1).getQuestion();
	        				System.out.println("Please enter your answer");
	        	  			answer = input1.next(); 
	        	  			log = quest.get(intSelection1-1).getLoginID();
	        	  		}
	        		d.updateAnswer(log, question, answer);
	        	}
	        	else
	        	{
	        		System.out.println("There are no questions yet");
	        	} 	
	        }	
		}
		//System.out.println(question+loginID + answer);
	}
//	
//	public void answer( String loginID, dataStorage d)
//	{
//		Scanner input = new Scanner(System.in);
//		String selection = "";
//		for(int i = 0; i < quest.size(); i++)
//    	{
//			System.out.printf("%s: select %s to answer\n", i+1, quest.get(i).getQuestion());
//    	}
//			selection = input.next();
//			
//			if(isInteger(selection))
//	  		{
//				int intSelection1 = Integer.parseInt(selection);
//				question = quest.get(intSelection1-1).getQuestion();
//				System.out.println("Please enter your answer");
//	  			answer = input.next(); 
//	  		}
//		
//		
//		d.updateAnswer(loginID, question, answer);
//		
	//}
	// thinking display average score
	
	//display reviews
//	public void reviews()
//	{
//		mycomments = data.getReviews(Title);
//		double sum = 0;
//		for(int i = 0; i < mycomments.size(); i++)
//		{
//			sum = sum + mycomments.get(i).getScore();
//		}
//		 Rating = sum/mycomments.size();
////		System.out.println(Rating);
//		data.updateRating(Title, Rating);
//	}
	
	private boolean isInteger(String a)
    {
        try
        {
            int i = Integer.parseInt(a);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

	public String toString() { 
	    return "Title: " + Title + "\nDescription: " + Description + "\nTag: " + Tag + "\nLocation: " + Location + "";
	} 
	
	
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
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getTag() {
		return Tag;
	}
	public void setTag(String tag) {
		Tag = tag;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public Double getRating() {
		return Rating;
	}
	public void setRating(Double rating) {
		Rating = rating;
	}
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	

}
