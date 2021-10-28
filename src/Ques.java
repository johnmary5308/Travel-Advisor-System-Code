
public class Ques {
	
	private String loginID;
	private String Question;
	//private String Answer;
	//private String Status;
	private String Attraction;
	
	
	public Ques(String loginID, String attraction, String question/*, String answer, String status*/) {
		super();
		this.loginID = loginID;
		Question = question;
		//Answer = answer;
		//Status = status;
		Attraction = attraction;
	}
	
	
	public String toString() { 
	    return "User: " + loginID + "\nAttraction Title: " + Attraction + "\nQuestion: " + Question + ""/*\nAnswer: " + Answer + ""+ "\nStatus: " + Status + ""*/;
	} 
	
	
	public String getLoginID() {
		return loginID;
	}
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}
	public String getQuestion() {
		return Question;
	}
	public void setQuestion(String question) {
		Question = question;
	}
	public String getAttraction() {
		return Attraction;
	}


	public void setAttraction(String attraction) {
		Attraction = attraction;
	}


//	public String getAnswer() {
//		return Answer;
//	}
//	public void setAnswer(String answer) {
//		Answer = answer;
//	}
//	public String getStatus() {
//		return Status;
//	}
//	public void setStatus(String status) {
//		Status = status;
//	}
	
	
	

}
