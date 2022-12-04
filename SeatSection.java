import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.FlowLayout;

public class SeatSection extends Decorator implements ActionListener {
    private Movie movie;
    private JPanel panel;
    private JPanel buttons;
    private JPanel seating;
    private JPanel row1;
    private JPanel row2;
    private JPanel row3;
    private JButton back;
    private JButton confirm;
    private JButton cancel;
    private String seatString;
    private ArrayList<Seat> seats;
    private JButton seat1;
    private JButton seat2;
    private JButton seat3;
    private JButton seat4;
    private JButton seat5;
    private JButton seat6;
    private JButton seat7;
    private JButton seat8;
    private JButton seat9;
    private ShowRoom room;
    private int counter;
    private Seat chosenSeat;

    
    private Database dbs = Database.getInstance();

    public SeatSection(Window window, Movie movie){
        super(window);
        
        frame.getContentPane().removeAll();
        this.movie = movie; 
        this.counter = 1;
        seatString = dbs.getAllSeats(movie);
        //seatString = "111111110";
        room = new ShowRoom(movie);
        room.setSeats(seatString);
        seats = room.getSeats();
        this.panel = new JPanel();
        this.seating = new JPanel();
        this.buttons = new JPanel();
        this.row1 = new JPanel();
        this.row2 = new JPanel();
        this.row3 = new JPanel();
        back = new JButton("Back");
        back.addActionListener(this);
        confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        seat1 = new JButton("Seat 1");
        if(seats.get(0).getAvailability() == true){
            seat1.addActionListener(this);
        }else{
            seat1.setBackground(Color.red);
            seat1.setOpaque(true);
        }
        seat2 = new JButton("Seat 2");
        if(seats.get(1).getAvailability() == true){
            seat2.addActionListener(this);
        }else{
            seat2.setBackground(Color.red);
            seat2.setOpaque(true);
        }
        seat3 = new JButton("Seat 3");
        if(seats.get(2).getAvailability() == true){
            seat3.addActionListener(this);
        }else{
            seat3.setBackground(Color.red);
            seat3.setOpaque(true);
        }
        seat4 = new JButton("Seat 4");
        if(seats.get(3).getAvailability() == true){
            seat4.addActionListener(this);
        }else{
            seat4.setBackground(Color.red);
            seat4.setOpaque(true);
        }
        seat5 = new JButton("Seat 5");
        if(seats.get(4).getAvailability() == true){
            seat5.addActionListener(this);
        }else{
            seat5.setBackground(Color.red);
            seat5.setOpaque(true);
        }
        seat6 = new JButton("Seat 6");
        if(seats.get(5).getAvailability() == true){
            seat6.addActionListener(this);
        }else{
            seat6.setBackground(Color.red);
            seat6.setOpaque(true);
        }
        seat7 = new JButton("Seat 7");
        if(seats.get(6).getAvailability() == true){
            seat7.addActionListener(this);
        }else{
            seat7.setBackground(Color.red);
            seat7.setOpaque(true);
        }
        seat8 = new JButton("Seat 8");
        if(seats.get(7).getAvailability() == true){
            seat8.addActionListener(this);
        }else{
            seat8.setBackground(Color.red);
            seat8.setOpaque(true);
        }
        seat9 = new JButton("Seat 9");
        if(seats.get(8).getAvailability() == true){
            seat9.addActionListener(this);
        }else{
            seat9.setBackground(Color.red);
            seat9.setOpaque(true);
        }
        this.display();
    }
    public void display(){
        panel.setLayout(new BorderLayout());
        seating.setLayout(new BoxLayout(seating, 1));
        row1.setLayout(new FlowLayout());
        row2.setLayout(new FlowLayout());
        row3.setLayout(new FlowLayout());
        buttons.setLayout(new FlowLayout());
        panel.add(new JLabel("Please Select a Seat", SwingConstants.CENTER), BorderLayout.NORTH);
        panel.add(new JLabel("------SCREEN------", SwingConstants.CENTER),BorderLayout.NORTH);
        row1.add(seat1);
        row1.add(seat2);
        row1.add(seat3);
        row2.add(seat4);
        row2.add(seat5);
        row2.add(seat6);
        row3.add(seat7);
        row3.add(seat8);
        row3.add(seat9);
        buttons.add(confirm);
        buttons.add(cancel);
        buttons.add(back);
        seating.add(row1);
        seating.add(row2);
        seating.add(row3);
        panel.add(seating, BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);//400 width and 500 height 
        frame.setResizable(false);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(back)){
            new ShowtimeSelector(this, this.movie.getTime());
            frame.dispose();
        }
        if(e.getSource().equals(cancel)){
            new SeatSection(this, this.movie);
            frame.dispose();
        }
        if(counter ==1){
        if(e.getSource().equals(seat1)){
            seat1.setBackground(Color.green);
            seat1.setOpaque(true);
            seats.get(0).setAvailability(false);
            chosenSeat = seats.get(0);
            counter = 0;
        }
        if(e.getSource().equals(seat2)){
            seat2.setBackground(Color.green);
            seat2.setOpaque(true);
            seats.get(1).setAvailability(false);
            chosenSeat = seats.get(1);
            counter = 0;
        }
        if(e.getSource().equals(seat3)){
            seat3.setBackground(Color.green);
            seat3.setOpaque(true);
            seats.get(2).setAvailability(false);
            chosenSeat = seats.get(2);
            counter = 0;
        }
        if(e.getSource().equals(seat4)){
            seat4.setBackground(Color.green);
            seat4.setOpaque(true);
            seats.get(3).setAvailability(false);
            chosenSeat = seats.get(3);
            counter = 0;
        }
        if(e.getSource().equals(seat5)){
            seat5.setBackground(Color.green);
            seat5.setOpaque(true);
            seats.get(4).setAvailability(false);
            chosenSeat = seats.get(4);
            counter = 0;
        }
        if(e.getSource().equals(seat6)){
            seat6.setBackground(Color.green);
            seat6.setOpaque(true);
            seats.get(5).setAvailability(false);
            chosenSeat = seats.get(5);
            counter = 0;
        }
        if(e.getSource().equals(seat7)){
            seat7.setBackground(Color.green);
            seat7.setOpaque(true);
            seats.get(6).setAvailability(false);
            chosenSeat = seats.get(6);
            counter = 0;
        }
        if(e.getSource().equals(seat8)){
            seat8.setBackground(Color.green);
            seat8.setOpaque(true);
            seats.get(7).setAvailability(false);
            chosenSeat = seats.get(7);
            counter = 0;
        }
        if(e.getSource().equals(seat9)){
            seat9.setBackground(Color.green);
            seat9.setOpaque(true);
            seats.get(8).setAvailability(false);
            chosenSeat = seats.get(8);
            counter = 0;
        }
        }

        if(e.getSource().equals(confirm) && counter == 0){
            dbs.updateSeats(this.movie, room.updateSeats(seats));
            new Login(this, movie, chosenSeat);
            frame.dispose();
        }


    }
}