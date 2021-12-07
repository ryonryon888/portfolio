import java.util.*;

import java.io.*;
public class ReadWrite {
	// convert file to ArrayList<String> strings
    public static ArrayList<String>readFile(String path) {
    	ArrayList<String> strings = new ArrayList<String>();
    	try {
            File inputfile = new File(path);
            FileReader input = new FileReader(inputfile);
            BufferedReader br = new BufferedReader(input);                       
            String text;
            while ((text = br.readLine()) != null) {
            	strings.add(text);
            }    
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        return strings;
    }    
    
    // convert ArrayList<String> strings to concerts
    public static void readConcert(String pass) {
    	ArrayList<String> strings = readFile(pass);
    	String text;
    	for(Iterator<String> itr = strings.iterator();itr.hasNext();) {
    		text = itr.next();
    		try {
                ConcertOpe.setConcert(scanConcert(text)); // scan concert data
            } catch(Exception e) {
    		    System.err.println("Format Error!");
    		    System.exit(1);
    		    break;
            }
    	}
    }
    
    // convert ArrayList<String> strings to members
    public static void readMember(String pass) {
    	ArrayList<String> strings = readFile(pass);
    	String text;
    	for(Iterator<String> itr = strings.iterator();itr.hasNext();) {
    		text = itr.next();
    		try {
    			MemberOpe.setMember(scanMember(text));  // scan concert data
            } catch(Exception e) {
    		    System.err.println("Format Error!");
    		    System.exit(1);
    		    break;
            }
    	}
    }
    
    //method in order to output file
    public static void writeConcert(String path) {
        try {
            File outputfile = new File(path);
            FileWriter output = new FileWriter(outputfile);
            for (int i=0; i<ConcertOpe.getSizeOfconcerts(); i++) {
                for (int j=0; j<7; j++) {
                    if (j >= 1 && j <= 4) {
                        output.write(String.valueOf(ConcertOpe.getConcert(i).gets(j)));
                    }
                    else {
                        output.write(String.valueOf(ConcertOpe.getConcert(i).geti(j)));
                    }
                    if (j != 6) {
                        output.write(",");
                    }
                }
                if (i != ConcertOpe.getSizeOfconcerts()-1) {
                    output.write("\n");
                }
            }
            output.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    //output CSV file(Member)
    public static void writeMember(String path) {
        try {
            File outputfile = new File(path);
            FileWriter output = new FileWriter(outputfile);

            for (int i=0; i< MemberOpe.getSizeOfmembers(); i++) {
            	output.write(MemberOpe.getMember(i).makeCSVstring());
                if (i != MemberOpe.getSizeOfmembers() - 1 ) {
                    output.write("\n");
                }
            }
            output.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    //method in order to convert String type to Concert type
    public static Concert scanConcert(String text) throws Exception {
        try {
            String str = "";
            int count = 0;
            ArrayList<String> arrays = new ArrayList<String>();
            //divide concert information into 7 parts and add them to ArrayList
            for (int i = 0; i < text.length(); i++) {
                if (!text.substring(i, i + 1).equals(",")) {
                    count = -1;
                    str += text.substring(i, i + 1);
                } else {
                    arrays.add(str);
                    str = "";
                }
            }
            arrays.add(str);
            if (arrays.size() >= 8) throw new Exception();
            Concert concert = new Concert(Integer.valueOf(arrays.get(0)), arrays.get(1),
                    arrays.get(2), arrays.get(3), arrays.get(4), Integer.valueOf(arrays.get(5)),
                    Integer.valueOf(arrays.get(6)));
            return concert;
       }
        catch(Exception e) {
            throw new Exception();
        }
    }
   
    //convert String type to Member type
    public static Member scanMember(String text) {
        String[] items = text.split(","); 
        Member m = new Member(Integer.valueOf(items[0]), items[1], items[2]);
        for(int i = 3; i < items.length; i++) {
        	m.addConcert(Integer.valueOf(items[i]));  //add hasConcert's components
        }
        return m;
    }
}