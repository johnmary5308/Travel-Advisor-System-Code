import java.util.Scanner;
import java.util.ArrayList;

public class Admin extends User {
	
	//--thinking there would be a variable of type attraction
	
	 private ArrayList<Attraction> PendingRequests = new ArrayList<Attraction>();
	 private ArrayList<Attraction> ApprovedRequests = new ArrayList<Attraction>();
	
	
	public Admin(String l, String p) {
		super(l, p);
		// TODO Auto-generated constructor stub
	}
	//methods
	//implement welcome from the user class
	@Override
	public void welcome()
	{
		System.out.println();
        System.out.println("Hello " + super.getLoginID() + 
                ", welcome to your online account"); 
        Scanner input = new Scanner(System.in);
        String selection = "";
        
        
        PendingRequests = super.getData().getAttractions();
         
        while(!selection.equals("x"))
        {
            //welcome msg
            System.out.println("\n***Welcome to your online account***");
            //options
            System.out.println("Please select your options");
            //display attractions to select you may like
            System.out.println("a: Approve Attractions");
            System.out.println("r: Reject Attractions");
            System.out.println("x: sign out\n");
            
            //after display the menu, we ask the user to input selection
            selection = input.next();
            
          //view pending requests
        	//approve attraction
            if(selection.equals("a"))
            {     
            	approve();
            	
            }
            else if (selection.equals("r"))
            {
            	reject();
            }
            
        }
        
        
	}
	public void approve()
	{
		 Scanner input = new Scanner(System.in);
		String selection1 = "";
		//display all attractions in the arraylist of not approved attractions
    	//select 1 to approve 
    	for(int i=0; i<PendingRequests.size(); i++)
        {
            System.out.printf("%s: select %s Attraction to approve\n", i+1, PendingRequests.get(i).getTitle());
           		//PendingRequests.get(i).get());
        }
    	selection1 = input.next();
    	//once approved it should be available to be seen by the user    	
    	if(isInteger(selection1))
		{
    		String status = "Approved";
			 int intSelection1 = Integer.parseInt(selection1);	
			 //get the value of the attraction and add it to the approved array list
			 super.getData().updateStatus(PendingRequests.get(intSelection1-1).getTitle(), status);
			 PendingRequests.remove(intSelection1-1);
		}
	}
	
	public void reject()
	{
		 Scanner input = new Scanner(System.in);
		String selection1 = "";
		//display all attractions in the arraylist of not approved attractions
    	//select 1 to approve 
    	for(int i=0; i<PendingRequests.size(); i++)
        {
            System.out.printf("%s: select %s Attraction to reject \n", i+1, PendingRequests.get(i).getTitle());
           		//PendingRequests.get(i).get());
        }
    	selection1 = input.next();
    	//once approved it should be available to be seen by the user    	
    	if(isInteger(selection1))
		{
    		String status = "Rejected";
			 int intSelection1 = Integer.parseInt(selection1);	
			 //get the value of the attraction and add it to the approved array list
			 super.getData().updateStatus(PendingRequests.get(intSelection1-1).getTitle(), status);
			 PendingRequests.remove(intSelection1-1);
		}
	}
	
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

}

