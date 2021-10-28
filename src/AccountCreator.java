import java.util.ArrayList;
import java.util.Scanner;

public class AccountCreator {
	private User theLoginAccount;
	private dataStorage data;
	private  String Tag1;
	String LoginID;
	String Password;

	private  String Tag2;
	private ArrayList<Attraction> youmaylike = new ArrayList<Attraction>();
	private String type;
	//--rating for attraction 
	private String [] tags = {"History Buff", "Shopping Fanatic", "Beach Goer", "Urban Explorer", "Nature Lover", "Family Vacationer"};
	public AccountCreator(dataStorage d)
	{
		data = d;
		//theLoginAccount = null;
		
	}
	
	public void createAccount()
	{
		
		
		Scanner input = new Scanner(System.in);
		String selection1 = "";
		String selection2 = "";
		
		type = "User";
		
		System.out.println("Please enter your Login1D: ");
		LoginID = input.nextLine();
		 
		System.out.println("Please enter your initial Password: ");
		Password = input.nextLine();
		 
		System.out.println("Please select a tag ");
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
			 Tag1 = tags[intSelection1-1];	
		}
		System.out.println("Please select another Tag2: ");
		selection2 = input.next();
		if (isInteger(selection2))
		{
			
			int intSelection2 = Integer.parseInt(selection2);	
			 //get the value of the tag and add it to the mytags array list
			 
			 System.out.println(tags[intSelection2-1]);
			 Tag2 = tags[intSelection2-1];
		}
		if((Password.length() >=3 && Password.length() <= 10) && (Password.matches("^(?:.*[a-z].*)(?:.*[0-9].*)$")) && (Password!=LoginID)){
			data.createaccount(LoginID, Password, type ,Tag1, Tag2);
		}
		else
		{
			System.out.println();
			System.out.println("*********Account not Created*********");
			System.out.println("Password must be between 3 and 10 characters and "
           + "must contain at least one number or letter and must not be the same with user id ");
		}   
		
	}
	

	
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

			//login implemented in travel advisor system
			
			public void login()
		    {
				//youmaylike = data.youmaylike(Tag1, Tag2);
		        //we need id and password
		        Scanner input = new Scanner(System.in);
		        String id="";
		        String password="";
		       
		        //get the login info.
		        System.out.println("Please enter your ID");
		        id = input.next();
		        System.out.println("Please enter your password");
		        password = input.next();
		        
		        //loginaccount is of type online account
		        theLoginAccount = data.login(id, password);
		        if(theLoginAccount != null && data != null)
		        {
		        	//if the login account has values get those values
		            theLoginAccount.setData(data);
		            System.out.println("The login successful");
		            theLoginAccount.welcome();
		        }
		        else
		        {
		        	//don't get any values
		            System.out.println("The login failed");
		            System.out.println();
		        }
		         
		    }
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

}
