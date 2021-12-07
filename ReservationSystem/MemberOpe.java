import java.util.*;

public class MemberOpe {
	private static ArrayList<Member> members = new ArrayList<Member>();
	
	//add new member
	public static void setMember(Member info) {members.add(info);}
	
	//get a member  (*refer to index)
	public static Member getMember(int i) {return members.get(i);}

	//print all members' information
	public static void printAllMember() {
		for(int i = 0; i < members.size(); i++)
			System.out.println(members.get(i).makeCSVstring());
	}

	//convert id to index (if not find, then return -1)
	public static int searchMember(int id) {
		for (int j=0; j<members.size(); j++) {
			if (members.get(j).getMemberNum() == id) {
				return j;
			}
		}
		return -1;
	}

	//return array size
	public static int getSizeOfmembers() {return members.size();}

	//return members who have reserved the concert with ID
	public static ArrayList<Member> getMemRvdConcert(int Id) {
		ArrayList<Integer> array = new ArrayList<Integer>();
		ArrayList<Member> M = new ArrayList<Member>();
		for (int i=0; i<MemberOpe.getSizeOfmembers(); i++) {
			if (members.get(i).getHasConcert().contains(Id)) {
				array.add(i+1);
			}
		}
		for (int i=0; i<getSizeOfmembers(); i++) {
			if (array.contains(getMember(i).getMemberNum())) {
				M.add(getMember(i));
			}
		}
		return M;
	}
}
