
//import scanner class to get input from user
import java.util.ArrayList;
import java.util.Scanner;

public class RegUser extends User {
	//attributes
	//dataStorage data; 
	private  String Tag1;
	private  String Tag2;
	private String Type;
//	private String type;
	//--rating for attraction 
	private String [] tags = {"History Buff", "Shopping Fanatic", "Beach Goer", "Urban Explorer", "Nature Lover", "Family Vacationer"};
	// arraylist of you may like attraction based on the tags
	private ArrayList<Attraction> AvAttractions = new ArrayList<Attraction>();
	private ArrayList<Attraction> AvAttractionsByCity = new ArrayList<Attraction>();
	private ArrayList<Attraction> AvAttractionsByTag = new ArrayList<Attraction>();
	private ArrayList<Comments> AttractionReviews = new ArrayList<Comments>();
	private ArrayList<Attraction> youmaylike = new ArrayList<Attraction>();
	private ArrayList<Answer> notifications = new ArrayList<Answer>();
	//private ArrayList<Ques> myQuestions = new ArrayList<Ques>();
	private ArrayList<favoriteAttraction> myFavouriteDestination = new ArrayList<favoriteAttraction>();
	 ArrayList<Integer> score = new ArrayList<Integer>();
	private String AttTitle;
	private Double Rating;
	//ArrayList<Integer> myQuestions = new ArrayList<Integer>();
	//implement arraylist for myfavorite attraction/place	
	//constructor for reguser class and user super class
	
	public RegUser (String l, String p, String t, String t1, String t2/*, dataStorage d*/) {
		super(l, p);
		
		Tag1 = t1;
		Tag2 = t2;
		Type = t;
		//data = d;	
	}	
	
//	public void rating()
//	{
//		AttractionReviews = super.getData().getReviews(AttTitle);
//		
//		
//	}
	
	//or maybe overload
	
	
	@Override
	public void welcome()
    {    
    	System.out.println();
        System.out.println("Hello " + super.getLoginID() + 
                ", welcome to your online account"); 
        
        Scanner input = new Scanner(System.in);
        String selection = "";
        String selection3 = "";         
        while(!selection.equals("x"))
        {
            //welcome msg
            System.out.println("\n***Welcome to your online account***");
            //options
            System.out.println("Please select your options");
           
            //display attractions to select you may like
            youmaylike = super.getData().youmaylike(Tag1, Tag2);
            myFavouriteDestination = super.getData().favouriteDestination(super.getLoginID());
            System.out.println();
            System.out.println("You may like");
            for(int i=0; i<youmaylike.size(); i++)
            {
            	System.out.printf("%s: select %s Attraction to view ---Rating is %s\n", i+1, youmaylike.get(i).getTitle(), youmaylike.get(i).getRating()); 
            	
            	
            	//display review about the attraction
            }
            //System.out.println(myFavouriteDestination);
            //comparing the tags from registration to the tag in attraction
            System.out.println();
            System.out.println("n:view notifications");
            System.out.println("f:view my favorite Destinations");
            System.out.println("c: Create Attractions");
            System.out.println("sC: Search Attractions By City");
            System.out.println("sT: Search Attractions By Tag");
            System.out.println("x: sign out\n");
            
            //after display the menu, we ask the user to input selection
            selection = input.next();
            //selection3 = input.next();//SELECT ONE
  		
	  		if(isInteger(selection))
	  		{
	  			 int intSelection3 = Integer.parseInt(selection);	
	  			AttTitle = youmaylike.get(intSelection3-1).getTitle();
	  				
	  			viewAttraction();
	  			
	  		}
            else if(selection.equals("c"))
            {
            	createAttraction();
            	
				//createAttraction(data);
            }
            else if (selection.equals("sC"))
            {
            	searchAttractionByCity();
            }
            else if (selection.equals("sT"))
            {
            	searchAttractionByTag();
            }
            else if (selection.equals("f"))
            { 
            	fave();
                 	//display review about the attraction      
            }
            else if(selection.equals("n"))
            {
            	notifications = super.getData().getAnswers(super.getLoginID());
            	if(notifications.size() >= 1)
            	{
            		viewNotifications();
            	}
            	else
            	{
            		System.out.println("You have no new notifications");
            	}
            	
            }
            
        }
        
    }
	//methods of reguser
	
	public void fave()
	{
		Scanner input = new Scanner(System.in);
		
		String selection3 = "";
		System.out.println("****My favorite destinations****");
     	if(myFavouriteDestination.size() >= 1)
     	{
     		for(int i=0; i<myFavouriteDestination.size(); i++)
            {
            	System.out.printf(" %s: Select %s to view ---Rating is %s\n", i+1, myFavouriteDestination.get(i).getTitle(), myFavouriteDestination.get(i).getRating());
            	//display review about the attraction
            }
     		
     		selection3 = input.next();//SELECT ONE
    		
    		if(isInteger(selection3))
    		{
    			 int intSelection3 = Integer.parseInt(selection3);			
    			 AttTitle = myFavouriteDestination.get(intSelection3-1).getTitle();
    			 viewAttraction();
    		}
     	}
     	else
     	{
     		System.out.println("You dont have any saved attractions");
     	}	
	}
	
	
	public void viewNotifications()
	{
		Scanner input = new Scanner(System.in);
		String selection = "";
		while(!selection.equals("x"))
		{
			
			System.out.println("Notifications");
	       	for(int i=0; i<notifications.size(); i++)
	        {
	       		
	          System.out.printf("Read notification\n%s: Question: %s Answer: %s\n", i+1, notifications.get(i).getQuestion(), notifications.get(i).getAnswer()); 
	           
	        }
	       	System.out.println("x: Exit");
	       	selection = input.next();
	       	if(isInteger(selection))
	       	{
	       		
	       		int intSelection6 = Integer.parseInt(selection);
	       		String u = notifications.get(intSelection6-1).getLoginID();
	       		//String a = notifications.get(intSelection6-1).getAttraction();
	       		String q = notifications.get(intSelection6-1).getQuestion();
	       		
	       		super.getData().updateQAStatus(u, q);
	       		notifications.remove(intSelection6-1);
	       		
	       	} 
	        
		}
        
	}
	
	public void createAttraction()
	{
		String Tag = "";
		Scanner input = new Scanner(System.in);
		
		String selection1 = "";
		//get all values here
		System.out.println("Please enter your attraction name");
		String Title = input.nextLine();
        System.out.println("Enter your attraction Description");
        String Description = input.nextLine();
        //an array of tags
        System.out.println("select a tag for your attraction");
		for(int i = 0 ; i < tags.length ; i++)
		{
			System.out.printf(" %s Select %s tag \n", i+1 , tags[i]);
		}
		selection1 = input.next();//SELECT ONE
		if(isInteger(selection1))
		{
			 int intSelection1 = Integer.parseInt(selection1);	
			 //get the value of the tag and add it to the mytags array list
			 
			 System.out.println(tags[intSelection1-1]);
			 Tag = tags[intSelection1-1];
						
			
		}
        
		Scanner input2 = new Scanner(System.in);
        System.out.println("Where is your attraction located");
        String Location = input2.nextLine();
//        System.out.println("Rate your attraction");
//        Rating = input2.nextDouble();
		//call the attraction class
        
        //how do we automatically get the inital status 
        //staus would be done in the sql database
        //
        super.getData().createattraction(Title, Description, Tag, Location );
	}
	
	//search city
	public void searchAttractionByCity()
	{
		
		//String Tag = "";
		Scanner input = new Scanner(System.in);
		
		String selection3 = "";
		//get all values here
		System.out.println("Type in a city name to search for attraction");
		String city = input.nextLine();
//        System.out.println("Type in a tag title to search for an attraction");
//        String tag = input.nextLine();
        AvAttractionsByCity = super.getData().getAvAttractionsByCity(city);
        //an array of tags
        
        if(AvAttractionsByCity.size() >= 1)
        {
        	System.out.println("Attraction Descriptions");
        	for(int i = 0 ; i < AvAttractionsByCity.size() ; i++)
    		{
    			System.out.printf(" %s: select %s Attraction to view --- Rating : %s\n", i+1, AvAttractionsByCity.get(i).getTitle(), AvAttractionsByCity.get(i).getRating() ); 
    			//display review about the attraction
    		}
        }
        else
        {
        	System.out.println("No attractions with this City name");
        }
		
		selection3 = input.next();//SELECT ONE
		
		if(isInteger(selection3))
		{
			 int intSelection3 = Integer.parseInt(selection3);			
			 AttTitle = AvAttractionsByCity.get(intSelection3-1).getTitle();
			 viewAttraction();
		}
	}
	//search attraction
	//geta city or a tag from a user and get attraction that has that city or tag
	public void searchAttractionByTag()
	{
		
		String Tag = "";
		Scanner input = new Scanner(System.in);
		
		String selection3 = "";
		//get all values here
//		System.out.println("Type in a city name to search for attraction");
//		String city = input.nextLine();
        System.out.println("Type in a tag title to search for an attraction");
        String tag = input.nextLine();
        AvAttractionsByTag = super.getData().getAvAttractionsByTag(tag);
        //an array of tags
        
        if(AvAttractionsByTag.size() >= 1)
        {
        	System.out.println("Attraction Descriptions");
        	for(int i = 0 ; i < AvAttractionsByTag.size() ; i++)
    		{
    			System.out.printf(" %s: select %s Attraction to view --- Rating : %s\n", i+1, AvAttractionsByTag.get(i).getTitle(), AvAttractionsByTag.get(i).getRating() ); 
    			//display review about the attraction
    		}
        }
        else
        {
        	System.out.println("No attractions with this Tag");
        }
		
		selection3 = input.next();//SELECT ONE
		
		if(isInteger(selection3))
		{
			 int intSelection3 = Integer.parseInt(selection3);			
			 AttTitle = AvAttractionsByTag.get(intSelection3-1).getTitle();
			 viewAttraction();
		}
	}
	
	//view attraction
	public void viewAttraction()
	{	
		System.out.println();
		Attraction view = super.getData().ViewAttractions(AttTitle);
		//Attraction view = super.getData().ViewAttractions(AttTitle);
		System.out.printf("Average rating for this attraction is: %s\n", view.getRating());
		System.out.println();
		AttractionReviews = super.getData().getReviews(AttTitle);
		System.out.println("Attraction Reviews");
		for(int i=0; i<AttractionReviews.size(); i++)
		{
			System.out.printf("Review %s:\n%s\n", i+1, AttractionReviews.get(i));
			
		}
		System.out.println();
		Scanner input = new Scanner(System.in);
		String selection = "";
		while(!selection.equals("b"))
        {
			System.out.println("s: save attraction to favorites");
			System.out.println("q:Ask or Answer a question");
//			System.out.println("a: Answer a question");
			System.out.println("p: Post an attraction review");
			System.out.println("v1: View attraction reviews");
			System.out.println("b: back");
			selection = input.next();
			if(selection.equals("s"))
			{
				System.out.println();
				boolean found = false;
				for(int i = 0; i < myFavouriteDestination.size(); i++ )
				{
					if(myFavouriteDestination.get(i).getTitle().equals(AttTitle) && myFavouriteDestination.get(i).getUser().equals(super.getLoginID())) //match AttTitle in the fav.
					{
						System.out.println("you already saved this destination");
						found = true;
						break;
					}
					 
				}
				//saveAttraction( AttTitle);
				if(!found)
				{
					super.getData().myFavorites(super.getLoginID(),AttTitle, view.getDescription(), view.getLocation(), view.getTag(),view.getRating(), view.getStatus());
				}
				//super.getData().myFavorites(super.getLoginID(),AttTitle, view.getDescription(), view.getLocation(), view.getTag(),view.getRating(), view.getStatus());
				
				//System.out.println("you have added "+AttTitle+" attraction to your favourite destination");
				System.out.println();
			}
			else if(selection.equals("p"))
			{
				postAttractionReview();
			}
			else if (selection.equals("v1"))
			{
				for(int i=0; i<AttractionReviews.size(); i++)
	            {
	            	
	            	System.out.printf("Review %s:\n %s\n", i+1, AttractionReviews.get(i));           
	            }
			}
			else if(selection.equals("q"))
			{
				//call the method from attraction
				view.questionAndanswer(super.getLoginID(), super.getData());
			
			}
			
			
        }	
	}
	//post review for attraction
	//comment
	public void postAttractionReview()
	{
		System.out.println();
	        Scanner input2 = new Scanner(System.in);       
	        System.out.println("Please enter a description");
	        String description = input2.nextLine();
	        System.out.println("Please enter your attraction score between 1-5");
	        int score = input2.nextInt();
	       System.out.println("Please enter your review Date");
	       String Dat = input2.next();
	       if(score>=1 && score<=5){
	    	   super.getData().postComment(AttTitle, description, score, super.getLoginID(), Dat);
		        super.getData().updateRating(AttTitle);
	       }
	       else
	       {
             System.out.println("Score must be between 1 and 5");
	       }
	       System.out.println(); 
	    
	}
	//save attraction into myfavorite
//	public void saveAttraction(String AttTitle)
//	{
//		for (int i = 0; i < myFavouriteDestination.size(); i++)
//		{
//			if(!myFavouriteDestination.get(i).getTitle().equals(AttTitle))
//			{
//				//myFavouriteDestination.add(youmaylike.get(i));
//				Attraction view2 = super.getData().ViewAttractions(AttTitle);
//				super.getData().myFavorites(super.getLoginID(),AttTitle, view2.getDescription(), view2.getLocation(), view2.getTag(),view2.getRating(), view2.getStatus());
//				
//			}
//			else
//			{
//				System.out.printf("you have "+AttTitle+" attraction in your favourite destination\nselect another attraction to add");
//			}
//		}
//		
//	}
	//show favorite
	
	//read notification
	//ask/answer questions
	//to convert selection into an integer
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
//
//		private boolean isValid(String password)
//	    {
//	        if(password.length() >= 3 && password.length() <=10)
//	        {
//	        	return true;
//	        }
//	        return false;
//	    }
//		
//		
		public String getTag1() {
			return Tag1;
		}
		public void setTag1(String tag1) {
			Tag1 = tag1;
		}
		public String getTag2() {
			return Tag2;
		}

		public void setTag2(String tag2) {
			Tag2 = tag2;
		}
		public String[] getTags() {
			return tags;
		}
		public void setTags(String[] tags) {
			this.tags = tags;
		}

		public Double getRating() {
			return Rating;
		}



		public void setRating(Double  rating) {
			Rating = rating;
		}
}
