import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket {
    private String email;
    private LocalDateTime dateBought;
    private Movie movie;
    private int seatNo;
    private Database dbs = Database.getInstance();

    public Ticket(String email,  int seatNo, Movie movie, float amount){
        this.email = email;
        this.seatNo = seatNo;
        this.movie = movie;
        this.dateBought = LocalDateTime.now(); //current date and time
        dbs.insertTicket(this , amount);


    }

    public String getEmail(){
        return email;
    }
    
    public Movie getMovie(){
        return movie;
    }

    public String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateBought.format(formatter);

        return formattedDateTime;
    }


    

    public int getSeatNo(){
        return seatNo;
    }

}
