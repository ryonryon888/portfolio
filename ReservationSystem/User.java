import java.util.*;

public class User {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);

        boolean AdmFlag = false; //initialized administrator right is off

        //input concert information
        String concertFilePass = "concert.txt";
        String memberFilePass = "member.txt";
        
        ReadWrite.readConcert(concertFilePass); // read concert file
        ReadWrite.readMember(memberFilePass);   // read member  file
         
        //main operation
        while (true) {

            System.out.print("1:Administrator 2:Member 3:Finish : ");
            int n;
            try {
            	n = Integer.valueOf(sc.next());
            }catch(Exception e) {
            	System.out.println("invalid input");
            	continue;
            }
            
            if (n == 1) {
                Administrator.admMode(concertFilePass);
            }
            else if (n == 2) {
                Reservation.main(args);//begin member mode
                ReadWrite.writeMember(memberFilePass);
            }
            else if (n == 3) {
            	sc.close();
                break;//all operation finished
            }
        }
    }
}