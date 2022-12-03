import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket {
    private String email;
    private LocalDateTime dateBought;
    private int Id;
    private int seatNo;
    private Database dbs = Database.getInstance();

    public Ticket(String email, int id, int seatNo){
        this.email = email;
        this.Id = id;
        this.seatNo = seatNo;
        this.dateBought = LocalDateTime.now(); //current date and time
        dbs.insertTicket(this);


    }

    public String getEmail(){
        return email;
    }

    public String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateBought.format(formatter);

        return formattedDateTime;
    }


    public int getID(){
        return Id;
    }

    public int seatNo(){
        return seatNo;
    }

}
