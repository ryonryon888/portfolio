import java.util.*;

public class Administrator {

    public static void admMode(String concertFilePass) {
        Scanner sc = new Scanner(System.in);
        boolean admFlag = false;
        admFlag = pass();
        if (admFlag == false) {
            System.out.println("You cannot use.");
        }
        while (admFlag == true) {
            System.out.println("Please input operation!");
            System.out.print("1:Add concert 2:Delete concert 3:revise concert 4:browse concert 5:All concert 6:Finish : ");
            //add
            try {
                String m = sc.next();

                switch (Integer.valueOf(m)) {
                    //add concert
                    case 1: {
                        System.out.println("Please input a concert information");
                        System.out.println("Format example: Number,\'Concert Name\',\'genre\',\'year/month/date\',\'Venue\',Capacity,Price");
                        System.out.print(":");
                        String dummy = sc.nextLine();
                        String text = sc.nextLine();

                        try {
                            //already exists? or not?
                            if (ConcertOpe.includeOfConcert(ReadWrite.scanConcert(text).getConcertId())) {
                                System.out.println("No" + ReadWrite.scanConcert(text).getConcertId() + " concert already exists!");
                            } else {
                                //add a concert information
                                ConcertOpe.setConcert(ReadWrite.scanConcert(text));
                                System.out.println("No" + ReadWrite.scanConcert(text).getConcertId() + " concert was added!");
                            }
                        } catch (Exception e) {
                            System.err.println("Format Error!");
                        }
                        break;
                    }
                    case 2: {
                        //input concert ID user want to delete
                        System.out.print("Please input a concert ID : ");
                        String ID = sc.next();
                        try  {
                            ConcertOpe.delConcert(Integer.valueOf(ID));
                        }
                        catch (NumberFormatException e) {
                            System.err.println("Please input Number!");
                        }
                        break;
                    }
                    case 3: {
                        //input ID user want to revise and concert information want to add
                        System.out.print("Please input a concert ID : ");
                        String ID = sc.next();
                        try {
                            int id = Integer.valueOf(ID);
                            System.out.println("Please input a concert information");
                            System.out.println("Format example: Number,\'Concert Name\',\'genre\',\'year/month/date\',\'Venue\',Capacity,Price");
                            System.out.print(":");
                            String dummy = sc.nextLine();
                            String text = sc.nextLine();
                            ConcertOpe.revConcert(ReadWrite.scanConcert(text), id);
                        }
                        catch (NumberFormatException e) {
                            System.err.println("Please input Number!");
                        }
                        catch (Exception e) {
                            System.err.println("Format Error!");
                        }
                        break;
                    }
                    //add
                    case 4: {
                        //browse members who have reserved selected concert
                        System.out.print("Please input a concert ID: ");
                        String ID = sc.next();
                        try {
                            for (int i = 0; i < MemberOpe.getMemRvdConcert(Integer.valueOf(ID)).size(); i++) {
                                System.out.println(MemberOpe.getMemRvdConcert(Integer.valueOf(ID)).get(i).toString());
                            }
                        }
                        catch (NumberFormatException e) {
                            System.err.println("Please input Number!");
                        }
                        break;
                    }
                    case 5: {
                        //display all concert information
                        for (int i = 0; i < ConcertOpe.getSizeOfconcerts(); i++) {
                            System.out.println(ConcertOpe.toString(i));
                        }
                        break;
                    }
                    case 6: {
                        //finish administrator mode
                        admFlag = false; //administrator right is off
                        //output all concert information into concert.txt
                        ReadWrite.writeConcert(concertFilePass);    // ### change to variable pass
                        break;
                    }
                    //add
                    default: {
                        System.out.println("Please input Number from 1 to 6!");
                        break;
                    }
                }
            }
            catch (NumberFormatException e) {
                System.err.println("Please input Number from 1 to 6!!");
            }
        }
    }

    public static boolean pass() {
        Scanner sc = new Scanner(System.in);
        int count = 0;
        while (true) {
            System.out.print("Please input password: ");
            String pass = sc.next();
            if (ConcertOpe.passwd(pass)) {
                System.out.println("Login succeed");
                return true;
            }
            else {
                count++;
                if (count >= 3) {
                    return false;
                }
            }
        }
    }
}