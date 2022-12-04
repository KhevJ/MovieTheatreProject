import java.util.ArrayList;

public class ShowRoom {
    private Movie movie;
    private ArrayList<Seat> seats;
    public ShowRoom(){
        this.movie = null;
        this.seats = null;
    }
    public ShowRoom(Movie movie){
        this.movie = movie;
        this.seats = null;
    }

    public Movie getMovie(){
        return this.movie;
    }
    public ArrayList<Seat> getSeats(){
        return this.seats;
    }
    public void setMovie(Movie movie){
        this.movie = movie;
        return;
    }
    public String updateSeats(ArrayList<Seat> seats){
        String update = "";
        for(int i = 8 ; i >= 0; i--){
            if(seats.get(i).getAvailability() == true){
                update = update + "1";
            }
            else{
                update = update + "0";
            }
        }
        return update;
    }
    public void setSeats(String avaliable){
        ArrayList<Seat> temp = new ArrayList<Seat>();
        int check = Integer.parseInt(avaliable);
        int id = 1;
        for(int i = 8; i >= 0; i--){
            
            if(check % 10 == 0){
                temp.add(new Seat(id, false));
            }
            else{
                temp.add(new Seat(id, true));
            }
            check /= 10;
            id++;
        }
        this.seats = temp;
        return;
    }
}