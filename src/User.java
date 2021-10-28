
public abstract class User {
	
	//attributes of user 
	private String LoginID;
	private String Password;
	private dataStorage data;
	
	

	//generate constructor for attributes
	public User(String l, String p) {
		
		LoginID = l;
		Password = p;
	}

	//make it abstract
	
	public abstract void welcome();

	public String getLoginID() {
		return LoginID;
	}

	public void setLoginID(String loginID) {
		LoginID = loginID;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
	public dataStorage getData() {
		return data;
	}

	public void setData(dataStorage data) {
		this.data = data;
	}
	
	//methods of user
	//login
	
	
	
}
