package database;


import backend.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Database {
    public final String DBURL;
    public final String USERNAME;
    public final String PASSWORD;    
    
    public Connection dbConnect;
    public ResultSet results;

    
    
    private static Database onlyInstance;


    private Database(String url, String user, String pw){
       // Database URL
       this.DBURL = url;

       //  Database credentials
       this.USERNAME = user;
       this.PASSWORD = pw;

    }


    //sql  connection
    public void createConnection(){
                
        try{
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            results.close();
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }               

    }



    //sql stuff to insert/delete
    public void insertUsers(User user){
        this.createConnection();
        try {
            String query = "INSERT IGNORE INTO USERS (Name, email, password, creditCard, DateRegistered) VALUES (?,?,?,?,?)";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            myStmt.setString(1, user.getName());
            myStmt.setString(2, user.getEmail());
            myStmt.setString(3, user.getPassword());
            myStmt.setString(4, user.getCreditCard());
            myStmt.setString(5, user.getTime());
            myStmt.executeUpdate();
            myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void updateSeats(Movie movie, String seats){
        this.createConnection();
        try {
            String query = "update SEATS set Seats = ? where FMovieID = ?";
            PreparedStatement preparedStmt = dbConnect.prepareStatement(query);
            preparedStmt.setString  (1, seats);
            preparedStmt.setInt(2, getMovieID(movie) );
            preparedStmt.executeUpdate();
            preparedStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public User getUser(String name, String password){
        User user = null;
        this.createConnection();
        try {
			
            String query=  "SELECT * FROM USERS WHERE name=? AND password = ?";
			PreparedStatement myStmt = dbConnect.prepareStatement(query);
            
             myStmt.setString(1, name);
             myStmt.setString(2, password);
             results = myStmt.executeQuery();
            while (results.next()){
                user= new User(results.getString("name"), results.getString("password"), results.getString("email"), results.getString("creditCard"));
            }
            myStmt.close();
		}
	    catch (SQLException ex) {
        ex.printStackTrace();
        }
        return user;
        }

    public void removeUser(User user){
        this.createConnection();
        try {
            String query = "DELETE FROM USERS WHERE Name = ?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);

            myStmt.setString(1, user.getName());
                        
            myStmt.executeUpdate();
            
            
            myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

    public boolean verifyCredit(String email) throws SQLException{
        this.createConnection();
        int count = 0;
        PreparedStatement stmt = null;
        ResultSet rset = null;
        try {
            stmt = dbConnect.prepareStatement(
            "SELECT Count(CreditID) from CREDIT WHERE email = ?");
            stmt.setString(1, email);
            rset = stmt.executeQuery();
            if (rset.next())
            count = rset.getInt(1);
            return count > 0;

        } finally {
            if(rset != null) {
                try {
                    rset.close();
                } catch(SQLException e) {
                e.printStackTrace();
      }
    }        
            if(stmt != null) {
                try {
                stmt.close();
            } catch(SQLException e) {
             e.printStackTrace();
      }
    }        
  }    


    }
    
    public  float getCredit(String email) throws SQLException{
        float amount = 0;
        this.createConnection();
        if(verifyCredit(email)){
            try {
			
                String query=  "SELECT * FROM CREDIT WHERE email= ?";
                PreparedStatement myStmt = dbConnect.prepareStatement(query);
                
                 myStmt.setString(1, email);
                 
                 results = myStmt.executeQuery();
                while (results.next()){
                    amount += results.getInt("Amount");
                }
                myStmt.close();
            }
            catch (SQLException ex) {
            ex.printStackTrace();
            }


        }
        return amount/100;
        

    }

    

    public void checkCredit(){
        this.createConnection();
        try {
			
            
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM CREDIT ");
            
            while (results.next()){
               if(todayGreaterThanOneYear(results.getString("dateCredit"))){
                   removeCredit(results.getString("email"));
               }
            }
            myStmt.close();
        }
        catch (SQLException ex) {
        ex.printStackTrace();
        }
    
         

    }

    public void checkTicket(){
        this.createConnection();
        try {
			
            
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM TICKETS ");
            
            while (results.next()){
               if(todayGreaterThanThreeDays(results.getString("DateBought"))){
                   removeTicket(results.getInt("TicketID"));
               }
            }
            myStmt.close();
        }
        catch (SQLException ex) {
        ex.printStackTrace();
        }

    }

    public boolean  todayGreaterThanThreeDays(String time){
        LocalDateTime threeDays = getLocalDateTime(time).plusDays(3);
        LocalDateTime now = LocalDateTime.now();
        if(now.isAfter(threeDays)){
            return true;
        }
        return false;
    }

    public void removeCredit(String email){
        this.createConnection();
        try {
            String query = "DELETE FROM CREDIT WHERE email = ?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);

            myStmt.setString(1, email);
                        
            myStmt.executeUpdate();
            
            
            myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

    

    

    public void updateCredit(String email, float amount) throws SQLException{
        this.createConnection();
        int totalAmount = (int)(Math.round((amount *100)));
        try {

            String query = "update CREDIT set Amount = ? where email = ?";
            PreparedStatement preparedStmt = dbConnect.prepareStatement(query);
            preparedStmt.setInt  (1, totalAmount);
            preparedStmt.setString(2,  email);
            preparedStmt.executeUpdate();
            preparedStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void insertCredit(String email, float amount, String time){
        int totalAmount = (int)(Math.round((amount *100)));
        this.createConnection();
        try {
            String query = "INSERT IGNORE INTO CREDIT(email,Amount,dateCredit)  VALUES (?,?,?)";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            myStmt.setString(1,  email);
            myStmt.setInt  (2, totalAmount);
            myStmt.setString(3, time);
            myStmt.executeUpdate();
            myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public boolean verifyRegistered(String email) throws SQLException{
        this.createConnection();
        int count = 0;
        PreparedStatement stmt = null;
        ResultSet rset = null;
        try {
            stmt = dbConnect.prepareStatement(
            "SELECT Count(UserID) from USERS WHERE email = ?");
            stmt.setString(1, email);
            
            rset = stmt.executeQuery();
            if (rset.next())
            count = rset.getInt(1);
            return count > 0;
        } finally {
            if(rset != null) {
                try {
                    rset.close();
                } catch(SQLException e) {
                e.printStackTrace();
      }
    }        
            if(stmt != null) {
                try {
                stmt.close();
            } catch(SQLException e) {
             e.printStackTrace();
      }
    }        
  } 
        
    }

    



    public boolean verifyUser(String name, String password) throws SQLException{
        this.createConnection();
        int count = 0;
        PreparedStatement stmt = null;
        ResultSet rset = null;
        try {
            stmt = dbConnect.prepareStatement(
            "SELECT Count(UserID) from USERS WHERE name=? AND password = ?");
            stmt.setString(1, name);
            stmt.setString(2, password);
            rset = stmt.executeQuery();
            if (rset.next())
            count = rset.getInt(1);
            return count > 0;
        } finally {
            if(rset != null) {
                try {
                    rset.close();
                } catch(SQLException e) {
                e.printStackTrace();
      }
    }        
            if(stmt != null) {
                try {
                stmt.close();
            } catch(SQLException e) {
             e.printStackTrace();
      }
    }        
  }    
}

    //get all movies
    public ArrayList<Movie> getAllMovies(){
        this.createConnection();
        ArrayList<Movie> Movies = new ArrayList<Movie>();
        try {
			Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM MOVIES ");
            while (results.next()){
                Movies.add(new Movie(results.getInt("MovieID"), results.getString("MovieName"), results.getString("MovieTime"), results.getInt("Amount")));
            }
            myStmt.close();
		}
	    catch (SQLException ex) {
        ex.printStackTrace();
        }
		return Movies;

    }

    public Movie getMovieFromId(int id){
        this.createConnection();
        Movie movie = null;
        try {
            String query = "SELECT * FROM MOVIES WHERE MovieID  = ?";
			PreparedStatement myStmt = dbConnect.prepareStatement(query);
            myStmt.setInt(1,  id);
            results = myStmt.executeQuery();
            while(results.next()){
                movie = new Movie(results.getInt("MovieID"), results.getString("MovieName"), results.getString("MovieTime"), results.getInt("Amount"));
            }
            myStmt.close();
		}
	    catch (SQLException ex) {
        ex.printStackTrace();
        }
        return movie;
    }

    public String getAllSeats(Movie movie){
        this.createConnection();
        String roomStatus= "";
        try {
            String query = "SELECT * FROM SEATS WHERE FMovieID  = ?";
			PreparedStatement myStmt = dbConnect.prepareStatement(query);
            myStmt.setInt(1,  getMovieID(movie));
            results = myStmt.executeQuery();
            while(results.next()){
                roomStatus = results.getString("Seats");
            }
            myStmt.close();
		}
	    catch (SQLException ex) {
        ex.printStackTrace();
        }
        
        return roomStatus;
    }

    public int getMovieID(Movie movie){
        this.createConnection();
        int id = 0;
        try {
            String query=  "SELECT * FROM MOVIES WHERE MovieName = ? AND MovieTime = ?";
			PreparedStatement myStmt = dbConnect.prepareStatement(query);
            
             myStmt.setString(1, movie.getName());
             myStmt.setString(2, movie.getTime());
             results = myStmt.executeQuery();
            while(results.next()){
                id = results.getInt("MovieID");
            }
           
            myStmt.close();
		}
	    catch (SQLException ex) {
        ex.printStackTrace();
        }
        
        return id;
    }


    public boolean verifyTicket(int number) throws SQLException {
        this.createConnection();
        int count = 0;
        PreparedStatement stmt = null;
        ResultSet rset = null;
        try {
            stmt = dbConnect.prepareStatement(
            "SELECT Count(TicketID) from TICKETS WHERE TicketID = ?");
            stmt.setInt(1, number);
            rset = stmt.executeQuery();
            if (rset.next())
            count = rset.getInt(1);
            return count > 0;
        } finally {
            if(rset != null) {
                try {
                    rset.close();
                } catch(SQLException e) {
                e.printStackTrace();
      }
    }        
            if(stmt != null) {
                try {
                stmt.close();
            } catch(SQLException e) {
             e.printStackTrace();
      }
    }        
  } 
        
    }

    public int getTicketID(Ticket ticket){
        this.createConnection();
        int id = 0;
        try {
            String query=  "SELECT * FROM TICKETS WHERE email = ? AND DateBought = ?";
			PreparedStatement myStmt = dbConnect.prepareStatement(query);
            
             myStmt.setString(1, ticket.getEmail());
             myStmt.setString(2, ticket.getTime());
             results = myStmt.executeQuery();
            while(results.next()){
                id = results.getInt("TicketID");
            }
           
            myStmt.close();
		}
	    catch (SQLException ex) {
        ex.printStackTrace();
        }
        return id;
    }

    public void insertTicket(Ticket ticket, float amount) {
        this.createConnection();
        int Amount = (int)(Math.round((amount *100)));
        try {
            String query = "INSERT IGNORE INTO TICKETS (FMovieID, email, SeatNo, Amount, DateBought ) VALUES (?,?,?,?,?)";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            myStmt.setInt(1, ticket.getMovie().getMovieId());
            myStmt.setString(2, ticket.getEmail());
            myStmt.setInt(3, ticket.getSeatNo());
            myStmt.setInt(4, Amount);
            myStmt.setString(5, ticket.getTime());
            myStmt.executeUpdate();
            myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void removeTicket(int id){
        this.createConnection();
        updateSeatsAfterTicket(id);
        try {

            String query = "DELETE FROM TICKETS WHERE TicketID = ?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);

            myStmt.setInt(1, id);
                  
            myStmt.executeUpdate();
            
            
            myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private int getSeatNoFromTicket(int id){
        this.createConnection();
        int number = 0;
        try {
            String query=  "SELECT * FROM TICKETS WHERE TicketID = ?";
			PreparedStatement myStmt = dbConnect.prepareStatement(query);
            
            myStmt.setInt(1, id);
             
            results = myStmt.executeQuery();
            while(results.next()){
                number = results.getInt("SeatNo");
            }
           
            myStmt.close();
		}
	    catch (SQLException ex) {
        ex.printStackTrace();
        }
        
        return number;
        

    }

    public int getAmountFromTicket(int id){
        this.createConnection();
        int number = 0;
        try {
            String query=  "SELECT * FROM TICKETS WHERE TicketID = ?";
			PreparedStatement myStmt = dbConnect.prepareStatement(query);
            
            myStmt.setInt(1, id);
             
            results = myStmt.executeQuery();
            while(results.next()){
                number = results.getInt("Amount");
            }
           
            myStmt.close();
		}
	    catch (SQLException ex) {
        ex.printStackTrace();
        }
        
        return number/100;
        

    }

    public String getEmailFromTicket(int id){
        this.createConnection();
        String email = "";
        try {
            String query=  "SELECT * FROM TICKETS WHERE TicketID = ?";
			PreparedStatement myStmt = dbConnect.prepareStatement(query);
            
            myStmt.setInt(1, id);
             
            results = myStmt.executeQuery();
            while(results.next()){
                email = results.getString("email");
            }
           
            myStmt.close();
		}
	    catch (SQLException ex) {
        ex.printStackTrace();
        }
        
        return email;
        

    }

    private int getMovieIDFromTicketID(int id){
        this.createConnection();
        int number = 0;
        try {
            String query=  "SELECT * FROM TICKETS WHERE TicketID = ?";
			PreparedStatement myStmt = dbConnect.prepareStatement(query);
            
            myStmt.setInt(1, id);
             
            results = myStmt.executeQuery();
            while(results.next()){
                number = results.getInt("FMovieID");
            }
           
            myStmt.close();
		}
	    catch (SQLException ex) {
        ex.printStackTrace();
        }
        
        return number;

    }

    private void updateSeatsAfterTicket(int id){
        int MovieID = getMovieIDFromTicketID(id);
        Movie movie = getMovieFromId(MovieID);
        
        String seats = getAllSeats(movie);
       
        int seatNo = getSeatNoFromTicket(id);

        char[] seatsChars = seats.toCharArray();
        seatsChars[9 - seatNo] = '1';
        seats = String.valueOf(seatsChars);
        updateSeats(movie, seats);


    }

    




    //getting instance
    public static Database getInstance(){
        if(onlyInstance == null){
            onlyInstance = new Database("jdbc:mysql://localhost/MOVIE_THEATRE", "root", "vanilla@1451");
        }

        return onlyInstance;
    }

   

    private LocalDateTime getLocalDateTime(String time){
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        return dateTime;

    }

    public boolean todayGreaterThanOneYear(String time){
        LocalDateTime oneYear = getLocalDateTime(time).plusYears(1);
        LocalDateTime now = LocalDateTime.now();
        if(now.isAfter(oneYear)){
            return true;
        }
        return false;
    }


    


   
   

}
