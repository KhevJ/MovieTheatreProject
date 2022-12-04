package gui;
import javax.swing.*;

import backend.*;
import database.*;

import java.awt.*;

public class receiptScreen extends Decorator{
    private Movie movie;
    private Ticket ticket;
    private JPanel panel;
    private Database dbs = Database.getInstance();

    public receiptScreen(Window window, Movie movie, Ticket ticket){
        super(window);
        frame.getContentPane().removeAll();
        this.ticket = ticket;
        this.movie = movie;
        this.panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        panel.setLayout(new GridLayout(0,1));
        panel.add(new JLabel("Movie: " + this.movie.getName()), BorderLayout.CENTER);
        panel.add(new JLabel("Time: " + this.movie.getTime()), BorderLayout.CENTER);
        panel.add(new JLabel("Seat: " + this.ticket.getSeatNo()), BorderLayout.CENTER);
        panel.add(new JLabel("Email: " + this.ticket.getEmail()), BorderLayout.CENTER);
        
        panel.add(new JLabel("Ticket ID: " + dbs.getTicketID(ticket)), BorderLayout.CENTER);
        this.display();

    }
    public void display(){
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}