import java.util.*;
public class ConcertOpe {
    private static ArrayList<Concert> concerts = new ArrayList<Concert>();//List which have all concert information
    private static String password = "Keio"; //add
    public static boolean passwd(String pass) { return pass.equals(password); } //add

    public static void setConcert(Concert info) { concerts.add(info); } //method in order to add concert
    public static Concert getConcert(int i) { return concerts.get(i); } //method in order to get concert information //maybe need to nothing
    //method in order to delete concert which has argument as ID
    //if not find, display error message
    public static void delConcert(int i) {
        for (int j=0; j<concerts.size(); j++) {
            if (concerts.get(j).getConcertId() == i) {
                concerts.remove(j);
                System.out.println("No" + i + " concert was deleted!");
                return;
            }
        }
        System.out.println("Not Found!");
    }
    //method in order to check if concert which has argument as ID exists
    public static boolean includeOfConcert(int i) {
        for (int j=0; j<concerts.size(); j++) {
            if (concerts.get(j).getConcertId() == i) {
                return true;
            }
        }
        return false;
    }
    
    //method in order to check if concert which has argument as ID exists
    public static int searchConcert(int id) {
        for (int j=0; j<concerts.size(); j++) {
            if (concerts.get(j).getConcertId() == id) {
                return j;
            }
        }
        return -1;
    }
    
    //method in order to convert information to a String
    public static String toString(int i) { return "No." + concerts.get(i).getConcertId() + ", Name: "
            + concerts.get(i).getConcertName() + ", Genre: " + concerts.get(i).getGenre() + ", Date: "
            + concerts.get(i).getDate() + ", Venue: " + concerts.get(i).getVenue() + ", Capacity: "
            + concerts.get(i).getCapacity() + ", Price: " + concerts.get(i).getPrice(); }
    public static int getSizeOfconcerts() { return concerts.size(); } //method in order to count the number of concerts
    public static void revConcert(Concert info, int i) {
        for (int j=0; j<concerts.size(); j++) {
            if (concerts.get(j).getConcertId() == i) {
                concerts.set(j, info);
                System.out.println("No" + i + " concert was revised!");
                return;
            }
        }
        System.out.println("Not Found!");
    } //method in order to revise concert information
    
    // print concert information
    public static void printConcertInfo(int i) {
    	System.out.println("<Concert Information>");
    	System.out.println("Concert Name : " + concerts.get(i).getConcertName());
    	System.out.println("Genre : " + concerts.get(i).getGenre() );
    	System.out.println("Venue : " + concerts.get(i).getVenue());
    	System.out.println("Capacity : " + concerts.get(i).getCapacity());
    	System.out.println("Price : " + concerts.get(i).getPrice() + "(yen)");
    }
    
    //search concert by name
    public static void genreSearch(String key) {
		int i;
		for(i=0; i<concerts.size(); i++) {
			if(ConcertOpe.getConcert(i).getGenre().compareTo(key)==0) {
				System.out.println(ConcertOpe.toString(i));
			}
		}
	}
}