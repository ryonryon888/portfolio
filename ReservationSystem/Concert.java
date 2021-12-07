

public class Concert {
    private int concertId; //concert id
    private String concertName; //concert name
    private String genre; //concert genre
    private String date; //event date
    private String venue; //concert venue
    private int capacity; //capacity of venue
    private int price; //ticket price

	//generate concert information
    Concert(int concertId, String concertName, String genre, String date, String venue, int capacity, int price) {
        this.concertId = concertId;
        this.concertName = concertName;
        this.genre = genre;
        this.date = date;
        this.venue = venue;
        this.capacity = capacity;
        this.price = price;
    }

    public int getConcertId() { return concertId; } //return concert id
    public String getConcertName() { return concertName; } //return concert name
    public String getGenre() { return genre; } //return concert genre
    public String getDate() { return date; } //return event date
    public String getVenue() { return venue; } //return concert venue
    public int getCapacity() { return capacity; } //return capacity of venue
    public int getPrice() { return price; } //return ticket price
    //method in order to display all concert information by User.java
    public int geti(int i) {
        if (i==0) return getConcertId();
        else if (i==5) return getCapacity();
        else return getPrice();
    }
    //method in order to display all concert information by User.java
    public String gets(int i) {
        if (i==1) return getConcertName();
        else if (i==2) return getGenre();
        else if (i==3) return getDate();
        else return getVenue();
    }
}