import java.util.ArrayList;
import java.util.Iterator;


public class Member {
    private int memberNum; //member number
    private String memberName; //member name
    private String password; //member password
    private ArrayList<Integer> hasConcert; //reserved concerts (ID)
    
    //generate instance of member information
    Member(int memberNum, String memberName, String password) {
        this.memberNum = memberNum;
        this.memberName = memberName;
        this.password = password;
        this.hasConcert = new ArrayList<Integer>();
    }

    public int getMemberNum() { return memberNum; } //return member number
    public String getMemberName() { return memberName; } //return member name
    public ArrayList<Integer> getHasConcert() { return hasConcert; } //return reserved concert ids
    public String getPassword() {return password;} // return password
    public void setPassword(String newpw) {password = newpw;} //set password
    
    //check key is same to password
    public boolean isPassword(String key) {					  // password checker
    	if(password.compareTo(key)==0)	return true;
    	return false;
    	}
    
    //add concert 
    public void addConcert(int concertId) {hasConcert.add(concertId);}
    
    //make CSV string of member's information
    public String makeCSVstring() {
		String res = "";
		res = memberNum + "," + memberName +"," + password;
		for(Iterator<Integer> itr = hasConcert.iterator();itr.hasNext();) {
			res += "," +itr.next();
		}
		return res;
	}
   
    // print reserved concerts
    public void printHasConcert() {
    	System.out.println("(reserved concerts)");
    	for(Iterator<Integer> itr = hasConcert.iterator();itr.hasNext();) {
    		int i = itr.next();
    		int index = ConcertOpe.searchConcert(i);
    		assert index != -1;
    		System.out.println(ConcertOpe.toString(index));
    	}    	
    }
    
    //check if the member has the concert with id 
    public boolean isHasConcert(int id) {
    	for(Iterator<Integer> itr = hasConcert.iterator();itr.hasNext();) {
			int tmp = itr.next();
			if(tmp == id) {
				return true;
			}
		}
		return false;
	}
    
    //remove the concert with id
    public void removeConcert(int id) {
		for(Iterator<Integer> itr = hasConcert.iterator();itr.hasNext();) {
			int tmp = itr.next();
			if(tmp == id) {
				itr.remove();
				System.out.println("This concert has been canceled.");
				return;
			}
		}
		System.out.println("You have not reserved this concert.");
	}

    //make string (No.id,Name:name)
    public String toString() { return "No." + this.getMemberNum() + ", Name: " + this.getMemberName(); } //add
}
