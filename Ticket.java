import java.time.LocalDateTime;

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


}
