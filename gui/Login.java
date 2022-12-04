package gui;
import javax.swing.*;
import backend.*;
import database.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class Login extends Decorator implements ActionListener  {
    private JPanel panel;
    private JPanel buttonPanel;
    private JButton oldUser;
    private JButton newUser;
    private JButton guest;
    private Seat seat;
    private Movie movie;


    public Login(Window window, Movie movie, Seat seat){
        super(window);
        this.movie = movie;
        this.seat = seat;
        oldUser = new JButton("Login");
        newUser = new JButton("Register");
        guest = new JButton("Guest");
        panel = new JPanel();
        buttonPanel = new JPanel();
        guest.addActionListener(this);
        oldUser.addActionListener(this);
        newUser.addActionListener(this);
        this.display();
    }

    public void display(){
        panel.setLayout(new BorderLayout());
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(oldUser);
        buttonPanel.add(newUser);
        buttonPanel.add(guest);
        panel.add(new JLabel("What Would You Like To Do?", SwingConstants.CENTER), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,150);//400 width and 500 height 
        frame.setResizable(false);
        frame.setVisible(true);
        //window.display();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(oldUser)){
            new UserValidation(this, movie, seat);
            frame.dispose();
        }
        else if(e.getSource().equals(newUser)){
            new UserRegistration(this, movie, seat);
            frame.dispose();
        }

        else if(e.getSource().equals(guest)){
            try {
                new Payment( this, movie, seat);
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            frame.dispose();
        }
    }       
}
