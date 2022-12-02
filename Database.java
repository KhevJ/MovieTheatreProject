import java.sql.*;
import java.util.ArrayList;
public class Database {
    public final String DBURL;
    public final String USERNAME;
    public final String PASSWORD;    
    
    public Connection dbConnect;
    public ResultSet results;

    public ArrayList<User> Users;

    public ArrayList<Ticket> Tickets; //movieName , time , seat
    //public ArrayList<Movie><Seat> seats;
    
    
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
            String query = "INSERT IGNORE INTO USERS (Name,email,password, DateRegistered, VALUES (?,?,?,?,?,?,?)";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            myStmt.setString(1, user.getName());
            myStmt.setString(2, user.getEmail());
            myStmt.setString(3, user.getPassword());
            myStmt.setString(4, user.getTime());
            myStmt.executeUpdate();
            myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public User getUser(String name, String password){
        User user = null;
        this.createConnection();
        try {
			Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM USERS WHERE name=? AND password = ?");
            ((PreparedStatement) myStmt).setString(1, name);
            ((PreparedStatement) myStmt).setString(2, password);
            while (results.next()){
                user= new User(results.getString("name"), results.getString("password"), results.getString("email "));
            }
            myStmt.close();
		}
	    catch (SQLException ex) {
        ex.printStackTrace();
        }
        return user;
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

    public String getAllSeats(Movie movie){
        this.createConnection();
        String roomStatus= "";
        try {
			Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM SEATS WHERE FMOVIEID = MOVIEID ");
            while (results.next()){
                roomStatus = results.getString("Seats");
            }
            myStmt.close();
		}
	    catch (SQLException ex) {
        ex.printStackTrace();
        }
        return roomStatus;
    }




    //getting instance
    public static Database getInstance(){
        if(onlyInstance == null){
            onlyInstance = new Database("jdbc:mysql://localhost/MOVIE_THEATRE", "root", "vanilla@1451");
        }

        return onlyInstance;
    }


}
