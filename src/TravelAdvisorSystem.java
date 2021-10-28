//import the scanner class because that would allow us to take inputs from the user
import java.util.Scanner;

public class TravelAdvisorSystem {
	
//	private User theLoginAccount;
//	private dataStorage data1;
	
//	public TravelAdvisorSystem(dataStorage d)
//	{
//		data1 = d;
//		theLoginAccount = null;
//	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		dataStorage data = new SQL_Database();
		
		Scanner input = new Scanner(System.in);
		String selection = "";
		
		//main menu
		while(!selection.equals("x"))
		{
			//display the menu
			System.out.println();
			System.out.println("Please make your selection: ");
			System.out.println("1: Create an account");
			System.out.println("2: login to your account");//ask if its administrator or not
			System.out.println("x: Finish");
			
			//get the selection from the user
			selection = input.nextLine();
			System.out.println();
			
			if (selection.equals("1"))
			{
				//create an instance of RegUser class
				//RegUser user1 = new RegUser()
				//user1.register();
				//new RegUser(data).createaccount("User");
				
				new AccountCreator(data).createAccount();
			}
			else if (selection.equals("2"))
			{
				//new RegUser(data).createAttraction();
				//check if the person already has an account
				//ask for user name and password, it has to correlate with the username and password of an existing account
				
				new AccountCreator(data).login();
				
				//new TravelAdvisorSystem(data).login();
			}
			
		}

	}
	
	
//	public void login()
//    {
//        //we need id and password
//        Scanner input = new Scanner(System.in);
//        String id="";
//        String password="";
//       
//        //get the login info.
//        System.out.println("Please enter your ID");
//        id = input.next();
//        System.out.println("Please enter your password");
//        password = input.next();
//        
//        //loginaccount is of type online account
//        theLoginAccount = data1.login(id, password);
//        if(theLoginAccount != null && data1 != null)
//        {
//        	//if the login account has values get those values
//            theLoginAccount.setData(data1);
//            System.out.println("The login successful");
//            
//            //if the loginaccount not admin
//            //display you may like
//            theLoginAccount.welcome();
//            //if loginid is admin
//            //show a differnt welcome page
////            if((theLoginAccount).getLoginID().equals("Admin"))
////            {
////            	System.out.println("admin");
////            }
////            else//if loginid is user show a different welcome page
////            {
////            	System.out.println("not admin");
////            }
//            //call the method only if it the method returned is not null       
//        }
//        else
//        {
//        	//don't get any values
//            System.out.println("The login failed");
//            System.out.println();
//        }
//         
//    }

	
	
	

}
