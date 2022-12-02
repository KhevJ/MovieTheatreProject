import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class User {
    protected boolean isRegistered = false;
    private String email;
    private String name;
    private LocalDateTime time;
    private String password;
    protected Database dbs = Database.getInstance();
    public User(String name ,String password, String email){
        this.name = name;
        this.email = email;
        this.password = password;
        this.time = LocalDateTime.now();
        dbs.insertUsers(this);
        
    }

    public String getEmail(){
        return email;
    }

    public String getName(){
        return name;
    }

    
   
    public String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = time.format(formatter);

        return formattedDateTime;
    }

    public String getPassword() {
        return password;
    }

    
    



}