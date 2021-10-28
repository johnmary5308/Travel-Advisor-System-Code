import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
//this class implements the methods in the interface 
//database is only to get values from the database
public class SQL_Database implements dataStorage{
	
	 final String DATABASE_URL = 
             "jdbc:mysql://mis-sql.uhcl.edu/johnm8793?useSSL=false";
	 final String db_id = "johnm8793";
	 final String db_psw = "1901105";
	 Connection connection = null;
	 Statement statement = null;
	 ResultSet resultSet = null;
 
	 @Override
	 public void createaccount(String LoginID, String Password, String type, String Tag1, String Tag2)
	 {       
        try
        {
             //connect to the database
             connection = DriverManager.getConnection(DATABASE_URL, 
                     "johnm8793", "1901105");
             connection.setAutoCommit(false);
            //create a statement
             statement = connection.createStatement(); 
             


             int r = statement.executeUpdate("insert into user values "
                     + "('" + LoginID + "', '" + Password + "', '" +type+ "', '" + Tag1 + "', '"+Tag2+"' )");
             
             connection.commit();
             connection.setAutoCommit(true);
             
              //display msg
            System.out.println("***The new account is created!***");
            
            System.out.println();
             
         }
         catch(SQLException e)
         {
             //handle the exceptions
             System.out.println("Account creation failed");
             e.printStackTrace();
         }
         finally
         {
             //close the database
             try
             {
            	 //resultSet.close();//only for if you are trying to get a result
                 statement.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
         }
	 }
	 
	 @Override
	 public User login(String id, String password)
	 {
		try
        {
            
            //connect to the databse
			connection = DriverManager.getConnection(DATABASE_URL, 
                  "johnm8793", "1901105");
            //create statement
            statement = connection.createStatement();
            //search the accountID in the onlineAccount table
            resultSet = statement.executeQuery("Select * from User "
                    + "where LoginID = '" + id + "'");
            
            if(resultSet.next())
            {
                //the id is found, check the password
                if(password.equals(resultSet.getString(2)))
                {
                    //password is good
                	//check for type
                    //if rs.getstring typr.equals("admin")
                    //else get regular user
                    //create an object of admin then call the object.welcome
                    //return an object of the person returned
                    //check if returned object is null 
                	
                	if(resultSet.getString(3).equals("Admin"))
                	{
                		//return admin object
                		return new Admin (resultSet.getString(1), resultSet.getString(2));
                        
                	}
                	else
                	{
                		//return user object
                		//return new 
                		return new RegUser (resultSet.getString(1), resultSet.getString(2),resultSet.getString(3), resultSet.getString(4), resultSet.getString(5) );
                	}
                	
                	
                    
                }
                else
                {
                    //password is not correct
                    return null;
                }
            }
            else
            {
            	return null;
            }
            
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }

	 }
	 
	 
	 @Override
	 public void createattraction(String Title, String Description, String Location, String Tag)
	 {       
        try
        {
             //connect to the database
             connection = DriverManager.getConnection(DATABASE_URL, 
                     "johnm8793", "1901105");
             statement = connection.createStatement();
             String s = "not approved";
             connection.setAutoCommit(false);
        //     String ss = statement.executeUpdate("Update attraction set status = approved where title = ...")
             //insert a record into bankAccount Table
             Double Rating = 0.0;
             int r = statement.executeUpdate("insert into attraction values "
                     + "('" + Title + "', '" + Description + "', '" 
                     + Tag + "', '" + Location + "', '" + Rating + "', '" + s + "')");//s for status
             
             connection.commit();
             connection.setAutoCommit(true);
             
              //display msg
            System.out.println("***A new attraction is created!***");
            
            System.out.println();
             
         }
         catch(SQLException e)
         {
             //handle the exceptions
             System.out.println("Account creation failed");
             e.printStackTrace();
         }
         finally
         {
             //close the database
             try
             {
                 resultSet.close();
                 statement.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
         }
	 }
	 
	 
	 @Override 
	 public ArrayList<Attraction> getAttractions()
	 {
	     try
	     {
	         //connect to the databse
	         connection = DriverManager.getConnection(DATABASE_URL, 
	               db_id, db_psw);
	         //create statement
	         statement = connection.createStatement();
	         //search the accountID in the onlineAccount table
	         String Status = "not approved";
	         resultSet = statement.executeQuery("Select * from Attraction "
	                 + "where Status = '" + Status + "'" +"ORDER BY Rating DESC");
	         
	         
	         ArrayList<Attraction> aList = new ArrayList<Attraction>();
	         
	         while(resultSet.next())
	         {
	        	 if(resultSet.getString("Status").equals("not approved"))
	        	 {
	        		 Attraction anAttraction = new Attraction(resultSet.getString(1), 
	        	     resultSet.getString(2), resultSet.getString(3), 
	        	     resultSet.getString(4), resultSet.getDouble(5),resultSet.getString(6) );
	        		 
	        	     aList.add(anAttraction);
	        	 }
	        	
	              
	         }
	         return aList;
	         
	     }
	     catch (SQLException e)
	     {
	         e.printStackTrace();
	         return null;
	     }
	     finally
	     {
	         //close the database
	         try
	         {
	             connection.close();
	             statement.close();
	             resultSet.close();
	         }
	         catch(Exception e)
	         {
	             e.printStackTrace();
	         }
	         
	     }
	 }
	 
	
	 
	 @Override 
	 public void updateStatus(String title, String status)
	 {
	         
	     try
	     {
	         //connect to the database
	         connection = DriverManager.getConnection(DATABASE_URL, 
	                 db_id, db_psw);
	         connection.setAutoCommit(false);
	         //create the statement
	         statement = connection.createStatement();
	         //update the balance
	         int r = statement.executeUpdate("Update attraction set "
	                 + "status = '" + status + "' "
	                 + "where title = '"
	                 + title + "'");
	         //update the activity
//	         r = statement.executeUpdate("Update bankAccount set "
//	              + "statement = '" + stm
//	                 + "' where accountNumber = '"
//	                 + accountNumber + "'");
	         connection.commit();
	         connection.setAutoCommit(true);
	
	
	     }
	     catch (SQLException e)
	     {
	         //handle the exception
	         e.printStackTrace();
	     }
	     finally
	     {
	         //close the database
	         try
	         {
	             statement.close();
	             connection.close();
	         }
	         catch(Exception e)
	         {
	             e.printStackTrace();
	         }
	     }
	 }
	 
	 
	 @Override 
	 public ArrayList<Attraction> getAvAttractions(String city, String tag)
	 {
	     try
	     {
	         //connect to the databse
	         connection = DriverManager.getConnection(DATABASE_URL, 
	               db_id, db_psw);
	         //create statement
	         statement = connection.createStatement();
	         //search the accountID in the onlineAccount table
	         String Status = "Approved";
	         resultSet = statement.executeQuery("Select * from Attraction "
	                 + "where Location = '" + city + "'" + " or Tag = '" + tag + "'" + "ORDER BY Rating DESC");
	         
	         ArrayList<Attraction> aList1 = new ArrayList<Attraction>();
	         
	         while(resultSet.next())
	         {
	        	 if(resultSet.getString("Status").equals("Approved"))
	        	 {
	        		 Attraction anAttraction = new Attraction(resultSet.getString(1), 
	        	     resultSet.getString(2), resultSet.getString(3), 
	        	     resultSet.getString(4), resultSet.getDouble(5),resultSet.getString(6) );
	        		 
	        	     aList1.add(anAttraction);
	        	 }
	        	
	              
	         }
	         return aList1;
	         
	     }
	     catch (SQLException e)
	     {
	    	 System.out.println("No attractions with this specifications");
	         e.printStackTrace();
	         return null;
	     }
	     finally
	     {
	         //close the database
	         try
	         {
	             connection.close();
	             statement.close();
	             resultSet.close();
	         }
	         catch(Exception e)
	         {
	             e.printStackTrace();
	         }
	         
	     }
	 }
	 
	 @Override 
	 public ArrayList<Attraction> youmaylike(String Tag1, String Tag2)
	 {
	     try
	     {
	         //connect to the databse
	         connection = DriverManager.getConnection(DATABASE_URL, 
	               db_id, db_psw);
	         //create statement
	         statement = connection.createStatement();
	         //search the accountID in the onlineAccount table
	         //String Status = "Approved";
	         resultSet = statement.executeQuery("Select * from Attraction "
	                 + "where Tag = '" + Tag1 + "'" + " or Tag = '" + Tag2 +"'" + " ORDER BY Rating DESC LIMIT 3");
	         
	         ArrayList<Attraction> aList2 = new ArrayList<Attraction>();
	         
	         while(resultSet.next())
	         {
	        	 if(resultSet.getString(6).equals("Approved"))
	        	 {
	        		 
	        			 Attraction anAttraction = new Attraction(resultSet.getString(1), 
	        	        	     resultSet.getString(2), resultSet.getString(3), 
	        	        	     resultSet.getString(4), resultSet.getDouble(5),resultSet.getString(6) );
	        	        		 
	        	        	     aList2.add(anAttraction);    		 	 
	        		
	        	 }
	        	
	              
	         }
	         return aList2;
	         
	     }
	     catch (SQLException e)
	     {
	         e.printStackTrace();
	         return null;
	     }
	     finally
	     {
	         //close the database
	         try
	         {
	             connection.close();
	             statement.close();
	             resultSet.close();
	         }
	         catch(Exception e)
	         {
	             e.printStackTrace();
	         }
	         
	     }
	 }
	 
	 @Override
	 public Attraction ViewAttractions(String title)
	 {
		try
        {
            
            //connect to the databse
			connection = DriverManager.getConnection(DATABASE_URL, 
                  "johnm8793", "1901105");
            //create statement
            statement = connection.createStatement();
            //search the accountID in the onlineAccount table
            resultSet = statement.executeQuery("Select * from Attraction "
                    + "where Title = '" + title + "'");
            
            if(resultSet.next())
            {
                //the id is found, check the password
                if(title.equals(resultSet.getString(1)))
                {
                    
                	
                	if(resultSet.getString(6).equals("Approved"))
                	{
                		//return admin object
                		return new Attraction (resultSet.getString(1), resultSet.getString(2),resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6) );
                        
                	}
                	else
                	{
                		//return user object
                		//return new 
                		System.out.println("Attraction cannot be viewed");
                		return null;
                	}
                	
                	
                    
                }
                else
                {
                    //password is not correct
                    return null;
                }
            }
            else
            {
            	return null;
            }
            
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }

	 }
	 
	 @Override
	 public void postComment(String Title, String Description, int score,String loginID, String dt)
	 {
		 
		 try
	        {
	             //connect to the database
	             connection = DriverManager.getConnection(DATABASE_URL, 
	                     "johnm8793", "1901105");
	             connection.setAutoCommit(false);
	            //create a statement
	             statement = connection.createStatement(); 
	             


	             int r = statement.executeUpdate("insert into comments values "
	                     + "('" + Title + "', '" + Description + "', '" +score+ "', '" + loginID + "', '"+dt+"' )");
	             
	             connection.commit();
	             connection.setAutoCommit(true);
	             
	              //display msg
	            System.out.println("***Your comment is Submitted!***");
	            
	            System.out.println();
	             
	         }
	         catch(SQLException e)
	         {
	             //handle the exceptions
	             System.out.println("Comment not posted");
	             e.printStackTrace();
	         }
	         finally
	         {
	             //close the database
	             try
	             {
	            	 //resultSet.close();//only for if you are trying to get a result
	                 statement.close();
	                 connection.close();
	             }
	             catch(Exception e)
	             {
	                 e.printStackTrace();
	             }
	         }
		 
	 }
	 
	 @Override 
	 public ArrayList<Comments> getReviews(String Title)
	 {
	     try
	     {
	         //connect to the databse
	         connection = DriverManager.getConnection(DATABASE_URL, 
	               db_id, db_psw);
	         //create statement
	         statement = connection.createStatement();
	         //search the accountID in the onlineAccount table
	         //String Status = "not approved";
	         resultSet = statement.executeQuery("Select * from comments "
	                 + "where Title = '" + Title + "'");
	         
	         
	         ArrayList<Comments> aList = new ArrayList<Comments>();
	         
	         while(resultSet.next())
	         {
	        	 
	        		 Comments mycomments = new Comments(resultSet.getString(1), 
	        	     resultSet.getString(2), resultSet.getInt(3), 
	        	     resultSet.getString(4), resultSet.getString(5));
	        		 
	        	     aList.add(mycomments); 	
	              
	         }
	         return aList;
	         
	     }
	     catch (SQLException e)
	     {
	         e.printStackTrace();
	         return null;
	     }
	     finally
	     {
	         //close the database
	         try
	         {
	             connection.close();
	             statement.close();
	             resultSet.close();
	         }
	         catch(Exception e)
	         {
	             e.printStackTrace();
	         }
	         
	     }
	 }
	 
	 
	 @Override 
	 public void updateRating(String Title)
	 {
	     try
	     {
	         //connect to the databse
	         connection = DriverManager.getConnection(DATABASE_URL, 
	               db_id, db_psw);
	         connection.setAutoCommit(false);
	         //create statement
	         statement = connection.createStatement();
	         //search the accountID in the onlineAccount table
	         //String Status = "not approved";
	         resultSet = statement.executeQuery("Select Avg(Score) from comments " + "where Title = '" + Title + "'");
	         DecimalFormat df = new DecimalFormat("##.0");
	        
	   
	         
	         if(resultSet.next())
	         {
	        	 
	        	 double avgScore  = resultSet.getDouble(1);
	        	 String avg = df.format(avgScore);
	        	 double str1 = Double.parseDouble(avg);
	        	 int r = statement.executeUpdate("Update Attraction set "
		                 + "Rating = '" + str1 + "' "
		                 + "where Title = '"
		                 + Title + "'");
	        		//Double score = resultSet.getDouble("Score");	     
	              
	         }
	         
	         connection.commit();
	         connection.setAutoCommit(true);
	         
	         
	     }
	     catch (SQLException e)
	     {
	         e.printStackTrace();
	         
	     }
	     finally
	     {
	         //close the database
	         try
	         {
	             connection.close();
	             statement.close();
	             resultSet.close();
	         }
	         catch(Exception e)
	         {
	             e.printStackTrace();
	         }
	         
	     }
	 }
	 
	 @Override 
	 public ArrayList<favoriteAttraction> favouriteDestination(String user)
	 {
	     try
	     {
	         //connect to the databse
	         connection = DriverManager.getConnection(DATABASE_URL, 
	               db_id, db_psw);
	         //create statement
	         statement = connection.createStatement();
	         //search the accountID in the onlineAccount table
	         //String Status = "Approved";
	         resultSet = statement.executeQuery("Select * from favorite "
	                 + "where User = '" + user + "'" + " ORDER BY Rating DESC");
	         
	         ArrayList<favoriteAttraction> aList2 = new ArrayList<favoriteAttraction>();
	         
	         while(resultSet.next())
	         {
	        	 if(resultSet.getString(1).equals(user))
	        	 {
	        		 favoriteAttraction anAttraction = new favoriteAttraction(resultSet.getString(1), 
	        	        	     resultSet.getString(2), resultSet.getString(3), 
	        	        	     resultSet.getString(4), resultSet.getString(5), resultSet.getDouble(6),resultSet.getString(7) );
	        	        		 
	        	        	     aList2.add(anAttraction);    		 
	        	 }
	        	
	              
	         }
	         return aList2;
	         
	     }
	     catch (SQLException e)
	     {
	         e.printStackTrace();
	         return null;
	     }
	     finally
	     {
	         //close the database
	         try
	         {
	             connection.close();
	             statement.close();
	             resultSet.close();
	         }
	         catch(Exception e)
	         {
	             e.printStackTrace();
	         }
	         
	     }
	 }
	 
	 
	 @Override
	 public void myFavorites(String User, String Title, String Description, String Location, String Tag,Double Rating, String Status)
	 {       
        try
        {
             //connect to the database
             connection = DriverManager.getConnection(DATABASE_URL, 
                     "johnm8793", "1901105");
             connection.setAutoCommit(false);
            //create a statement
             statement = connection.createStatement(); 
	        		 int r =  statement.executeUpdate("insert into favorite values "
	                         + "('" + User + "', '" + Title + "', '" +Description+ "', '" + Location + "', '"+Tag+"', '"+Rating+"', '"+Status+"' )");
             connection.commit();
             connection.setAutoCommit(true);
             
              //display msg
            System.out.println("You have added " + Title + " to your favorites");
            
            System.out.println();
             
         }
         catch(SQLException e)
         {
             //handle the exceptions
             System.out.println("Favorites not added");
             e.printStackTrace();
         }
         finally
         {
             //close the database
             try
             {
            	 //resultSet.close();//only for if you are trying to get a result
                 statement.close();
                 connection.close();
                 //resultSet.close();
                 
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
         }
	 }
	 
	 @Override
	 public void QA (String loginID, String Title, String Question)
	 {       
        try
        {
             //connect to the database
             connection = DriverManager.getConnection(DATABASE_URL, 
                     "johnm8793", "1901105");
             connection.setAutoCommit(false);
             
             //String Status = "Unread";
            //create a statement
             statement = connection.createStatement(); 
	        		 int r =  statement.executeUpdate("insert into qa values "
	                         + "('" + loginID + "', '" + Title + "', '" + Question + "')");
             connection.commit();
             connection.setAutoCommit(true);
             
              //display msg
            //System.out.println("You have added " + Title + " to your favorites");
            
            System.out.println();
             
         }
         catch(SQLException e)
         {
             //handle the exceptions
             System.out.println("Favorites not added");
             e.printStackTrace();
         }
         finally
         {
             //close the database
             try
             {
            	 //resultSet.close();//only for if you are trying to get a result
                 statement.close();
                 connection.close();
                 //resultSet.close();
                 
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
         }
	 }
	 
	 @Override 
	 public ArrayList<Ques> getQuestions(String attraction)
	 {
	     try
	     {
	         //connect to the databse
	         connection = DriverManager.getConnection(DATABASE_URL, 
	               db_id, db_psw);
	         //create statement
	         statement = connection.createStatement();
	         //search the accountID in the onlineAccount table
	         //String Status = "Approved";
	        // String ans = "null";
	         resultSet = statement.executeQuery("Select * from qa "
	                 + "where Attraction = '" + attraction +"'");
	         
	         ArrayList<Ques> aList2 = new ArrayList<Ques>();
	         
	         while(resultSet.next())
	         {
	        	 if(resultSet.getString(2).equals(attraction) )
	        	 {
	        		 Ques questionandanswer = new Ques(resultSet.getString(1), 
	        	        	     resultSet.getString(2), resultSet.getString(3));
	        	        		 
	        	        	     aList2.add(questionandanswer);    		 
	        	 }
	        	
	              
	         }
	         return aList2;
	         
	     }
	     catch (SQLException e)
	     {
	         e.printStackTrace();
	         return null;
	     }
	     finally
	     {
	         //close the database
	         try
	         {
	             connection.close();
	             statement.close();
	             resultSet.close();
	         }
	         catch(Exception e)
	         {
	             e.printStackTrace();
	         }
	         
	     }
	 }
	 
	 
	 @Override 
	 public void updateAnswer(String loginID, String question, String answer)
	 {
	         
	     try
	     {
	         //connect to the database
	         connection = DriverManager.getConnection(DATABASE_URL, 
	                 db_id, db_psw);
	         connection.setAutoCommit(false);
	         //create the statement
	         String status = "Unread";
	         statement = connection.createStatement();
    		 int r =  statement.executeUpdate("insert into answer values "
                     + "('" + loginID + "', '" + question + "', '" + answer + "', '" + status + "')");
	        
	         //update the balance
//	         int r = statement.executeUpdate("Update qa set "
//	                 + "status = '" + status + "' "
//	                 + "where question = '"
//	                 + question + "'");
	         //update the activity
//	         int r = statement.executeUpdate("Update answer set "
//	              + "Answer = '" + answer
//	                 + "' where question = '"
//	                 + question + "'");
	         connection.commit();
	         connection.setAutoCommit(true);
	
	
	     }
	     catch (SQLException e)
	     {
	         //handle the exception
	         e.printStackTrace();
	     }
	     finally
	     {
	         //close the database
	         try
	         {
	             statement.close();
	             connection.close();
	         }
	         catch(Exception e)
	         {
	             e.printStackTrace();
	         }
	     }
	 }
	 
 
	 
	 @Override 
	 public ArrayList<Answer> getAnswers(String User)
	 {
	     try
	     {
	         //connect to the databse
	         connection = DriverManager.getConnection(DATABASE_URL, 
	               db_id, db_psw);
	         //create statement
	         statement = connection.createStatement();
	         //search the accountID in the onlineAccount table
	         //select user from question where question= question
	         String Status = "Unread";
	         //String ans = "null";
	         resultSet = statement.executeQuery("Select * from answer "
	                 + "where User = '" + User + "'" + " and Status = '" +  Status +"'");
	         
	         ArrayList<Answer> aList2 = new ArrayList<Answer>();
	         
	         while(resultSet.next())
	         {
	        	 if(!resultSet.getString(4).equals("null") )
	        	 {
	        		 Answer questionandanswer = new Answer(resultSet.getString(1), 
	        	        	     resultSet.getString(2), resultSet.getString(3), 
	        	        	     resultSet.getString(4));
	        	        		 
	        	        	     aList2.add(questionandanswer);    		 
	        	 }
	        	
	              
	         }
	         return aList2;
	         
	     }
	     catch (SQLException e)
	     {
	         e.printStackTrace();
	         return null;
	     }
	     finally
	     {
	         //close the database
	         try
	         {
	             connection.close();
	             statement.close();
	             resultSet.close();
	         }
	         catch(Exception e)
	         {
	             e.printStackTrace();
	         }
	         
	     }
	 }
	 
	 @Override 
	 public void updateQAStatus(String User, String question)
	 {
	         
	     try
	     {
	         //connect to the database
	         connection = DriverManager.getConnection(DATABASE_URL, 
	                 db_id, db_psw);
	         connection.setAutoCommit(false);
	         //create the statement
	         statement = connection.createStatement();
	         String status = "read";
	         //update the balance
//	         int r = statement.executeUpdate("Update qa set "
//	                 + "status = '" + status + "' "
//	                 + "where question = '"
//	                 + question + "'");
	         //update the activity
	         int r = statement.executeUpdate("Update answer set "
	              + "Status = '" + status
	                 + "' where question = '" + question + "' and User = '"+ User + "'" );
	         connection.commit();
	         connection.setAutoCommit(true);
	
	
	     }
	     catch (SQLException e)
	     {
	         //handle the exception
	         e.printStackTrace();
	     }
	     finally
	     {
	         //close the database
	         try
	         {
	             statement.close();
	             connection.close();
	         }
	         catch(Exception e)
	         {
	             e.printStackTrace();
	         }
	     }
	 }
	 
	 @Override 
	 public ArrayList<Attraction> getAvAttractionsByCity(String city)
	 {
	     try
	     {
	         //connect to the databse
	         connection = DriverManager.getConnection(DATABASE_URL, 
	               db_id, db_psw);
	         //create statement
	         statement = connection.createStatement();
	         //search the accountID in the onlineAccount table
	         String Status = "Approved";
	         resultSet = statement.executeQuery("Select * from Attraction "
	                 + "where Location = '" + city + "'" + "ORDER BY Rating DESC");
	         
	         ArrayList<Attraction> aList1 = new ArrayList<Attraction>();
	         
	         while(resultSet.next())
	         {
	        	 if(resultSet.getString("Status").equals("Approved"))
	        	 {
	        		 Attraction anAttraction = new Attraction(resultSet.getString(1), 
	        	     resultSet.getString(2), resultSet.getString(3), 
	        	     resultSet.getString(4), resultSet.getDouble(5),resultSet.getString(6) );
	        		 
	        	     aList1.add(anAttraction);
	        	 }
	        	
	              
	         }
	         return aList1;
	         
	     }
	     catch (SQLException e)
	     {
	    	 System.out.println("No attractions with this city name");
	         e.printStackTrace();
	         return null;
	     }
	     finally
	     {
	         //close the database
	         try
	         {
	             connection.close();
	             statement.close();
	             resultSet.close();
	         }
	         catch(Exception e)
	         {
	             e.printStackTrace();
	         }
	         
	     }
	 }
	 
	 
	 @Override 
	 public ArrayList<Attraction> getAvAttractionsByTag (String tag)
	 {
	     try
	     {
	         //connect to the databse
	         connection = DriverManager.getConnection(DATABASE_URL, 
	               db_id, db_psw);
	         //create statement
	         statement = connection.createStatement();
	         //search the accountID in the onlineAccount table
	         String Status = "Approved";
	         resultSet = statement.executeQuery("Select * from Attraction "
	                 + "where Tag = '" + tag + "'" + "ORDER BY Rating DESC");
	         
	         ArrayList<Attraction> aList1 = new ArrayList<Attraction>();
	         
	         while(resultSet.next())
	         {
	        	 if(resultSet.getString("Status").equals("Approved"))
	        	 {
	        		 Attraction anAttraction = new Attraction(resultSet.getString(1), 
	        	     resultSet.getString(2), resultSet.getString(3), 
	        	     resultSet.getString(4), resultSet.getDouble(5),resultSet.getString(6) );
	        		 
	        	     aList1.add(anAttraction);
	        	 }
	        	
	              
	         }
	         return aList1;
	         
	     }
	     catch (SQLException e)
	     {
	    	 System.out.println("No attractions with this Tag");
	         e.printStackTrace();
	         return null;
	     }
	     finally
	     {
	         //close the database
	         try
	         {
	             connection.close();
	             statement.close();
	             resultSet.close();
	         }
	         catch(Exception e)
	         {
	             e.printStackTrace();
	         }
	         
	     }
	 }
	 
}