import java.util.Scanner;

public class Reservation {
	private static Scanner scan = new Scanner(System.in);
	static Member member;

	// login method: set a member and  authentication
	public static boolean login() {
		int i,index,count=0;
		String key;
		//set a member
		while(true) {
			try {
				System.out.print("input your id:");
				i = Integer.valueOf(scan.next());    //scan id(integer)
				index = MemberOpe.searchMember(i);   //convert id to index
				if( index != -1) {
					member = MemberOpe.getMember(index);
					break;
				}else {
					System.out.println("id:" + i + " does\'t exist." );
				}
			}catch(Exception e) {
				System.out.println("invalid id");
			}
		}
		//Authorization
		while(true) { 
			System.out.print("input password:");
			key = scan.next();                       //scan password(String)
			if(member.isPassword(key)) {
				System.out.println("Welcome " + member.getMemberName() + " !");
				return true;
			}else {
				System.out.println("Access denied");
				if(++count >= 3) {				  	//limitation times of input:3 
					System.out.println("you cannnot use.");
					return false;
				}
				continue;
			}
		}
	}

	//reservation method: reserve a concert
	public static void reserve() {
		int id,index;
		String c;
		while(true) {
			System.out.print("input concert No. (back 'b'):");
			//set id
			try {
				c=scan.next();						//scan id(integer)
				if(c.compareTo("b")==0) return;
				id = Integer.valueOf(c);
			}catch(Exception e) {
				System.out.println("invalid id");
				continue;
			}
			index = ConcertOpe.searchConcert(id);	//convert id to index
			if(index == -1) {
				System.out.println("id:"+id+" does not exist");
				continue;
			}else if(member.isHasConcert(id)) {
				System.out.println("This concert has been already reserved");
				continue;
			}
			break;

		}
		ConcertOpe.printConcertInfo(index);	//print Concert Information
		//Confirm the reservation
		while(true) {
			System.out.print("Do you confirm the reservation?(yes or no) :");
			String str = scan.next();		//scan "yes" or "No"
			if(str.compareTo("yes")==0) {
				System.out.println("Reserved!");
				member.addConcert(id);      //add the Concert to hasConcert
				return;
			}else if(str.compareTo("no")==0) {
				System.out.println("Canceled!");
				return;
			}else {
				System.out.println("invalid string:input \"yes\" or \"no\"");
				continue;
			}		
		}
	}
	
	//[search mode] : search concert
	public static void search() {
		int mode;

		System.out.println("[search mode] please input");
		System.out.print("[0-4]: 0:sports, 1:movie, 2:music, 3:museum, 4:all concerts:");
		try {
			mode = Integer.valueOf(scan.next());  //scan number(integer)
		}catch(Exception e) {
			System.out.println("invalid input");
			return;
		}
		
		switch(mode) {
			case 0:			//sports
				ConcertOpe.genreSearch("sports");
				break;
			case 1:			//movie
				ConcertOpe.genreSearch("movie");
				break;
			case 2:			//music
				ConcertOpe.genreSearch("music");
				break;
			case 3:			//museum
				ConcertOpe.genreSearch("museum");
				break;
			case 4:			//all
				System.out.println();
				for (int j=0; j<ConcertOpe.getSizeOfconcerts(); j++) {
					System.out.println(ConcertOpe.toString(j));
				}
				break;
			default:
				System.out.println("invalid input");
		}
	}
	
	
	//cancellation method: cancel a concert
	public static void cancel() {
		int id;
		String c;
		while(true) {
			System.out.print("input concert No. (back 'b'):");
			//set concert id
			try {
				c = scan.next();				//scan id(integer)
				if(c.compareTo("b")==0) return;
				id = Integer.valueOf(c);					
			}catch(Exception e) {
				System.out.println("invalid input");
				continue;
			}
			//confirm the cancellation
			if(member.isHasConcert(id)) {
				while(true) {
					System.out.println(ConcertOpe.toString(ConcertOpe.searchConcert(id)));
					System.out.print("Do you confirm the cancellation?(yes or no) :");
					String str = scan.next();		//scan "yes" or "No"
					if(str.compareTo("yes")==0) {
						member.removeConcert(id);
						return;
					}else if(str.compareTo("no")==0){
						return;
					}else {
						System.out.println("invalid string:input \"yes\" or \"no\"");
						continue;
					}		
				}		
			}else {
				System.out.println("You have not reserved this concert.");
			}	
		}
	}

	//password change method: change member's password
	public static void changePassword() {
		while(true){
			System.out.print("input new password(return 'b'):");
			String pw = scan.next();		//scan password(String)
			if(pw.compareTo("b")==0) return;
			System.out.print("input the same password:");
			String pw2 = scan.next();		//scan the same password(String)
			if(pw.compareTo(pw2)==0) {
				member.setPassword(pw);		//change password
				System.out.println("password has been changed.");
				return;
			}else {
				System.out.println("second input is different from first input.");
				continue;
			}
		}
	}
	
	//[MyPage] mode
	public static void mypage() {
		int mode;
		while(true) {
			System.out.println();
			System.out.println("[MyPage]" + " (" + member.toString() +") "+ "Please input [0-3] :");
			System.out.print("0:exit MyPage, 1:reserved concerts, 2:cancel concert 3:change password  :");
			//select mode
			try {
				mode = Integer.valueOf(scan.next());	//scan mode(integer)
			}catch(Exception e) {
				System.out.println("invalid id");
				continue;
			}

			switch(mode) {
			case 0:		// exit MyPage
				System.out.println("exit MyPage");
				return;
			case 1:		// print reserved concerts
				member.printHasConcert();
				break;
			case 2:		//cancel concert 
				cancel();
				break;
			case 3:		//change password
				changePassword();
				break;
			}
		}
	}	

	public static void main(String[] args) {
		// login: if authentication failed,then back to User.java
		if(!login()) return;  	
		int mode;
		while(true) {
			System.out.println();
			System.out.println("[MainPage]Please input [0-3]");
			System.out.print("0:logout, 1:search, 2:reserve, 3:MyPage  :");
			//select mode 
			try {
			mode = Integer.valueOf(scan.next());	//scan mode(integer)
			}catch(Exception e) {
				System.out.println("invalid input");
				continue;
			}
			
			switch(mode) {
			case 0:		//logout
				System.out.println("user:" + member.getMemberName() + " logout");
				return;
			case 1:		//go to search mode
				search();
				break;
			case 2:		//reservation
				reserve();
				break;				
			case 3:		//go to [MyPage]
				mypage();
				break;
			default:
				System.out.println("invalid input");
			}			
		}
	}
}