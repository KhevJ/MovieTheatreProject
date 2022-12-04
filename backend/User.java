package backend;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import database.Database;

public class User {
    
    private String email;
    private String name;
    private LocalDateTime time;
    private String password;
    private String creditCard;
    private Database dbs = Database.getInstance();
  
    public User(String name ,String password, String email, String creditCard){
        this.name = name;
        this.email = email;
        this.password = password;
        this.creditCard = creditCard;
        this.time = LocalDateTime.now();
       
        
    }

    public String getEmail(){
        return email;
    }

    public String getName(){
        return name;
    }

    public String getCreditCard(){
        return creditCard;
    }

    public void insertUserDB(){
        dbs.insertUsers(this);
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