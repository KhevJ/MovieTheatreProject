package backend;
public class Seat {
    private int seatID;
    private boolean availability;
    public Seat(int id, boolean a){
        this.seatID = id;
        this.availability = a;
    }
    public int getSeatID(){
        return this.seatID;
    }
    public void setSeatID(int id){
        this.seatID = id;
    }
    public boolean getAvailability(){
        return this.availability;
    }
    public void setAvailability(boolean a){
        this.availability = a; 
        return;
    }
    
}