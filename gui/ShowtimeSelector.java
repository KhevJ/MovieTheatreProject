package gui;

import database.*;
import backend.*;

import javax.swing.*;



import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ShowtimeSelector extends Decorator implements ActionListener {
    private JPanel panel;
    private JButton confirm;
    private JButton back;
    private JComboBox times;
    private String movieName;
    private Database dbs = Database.getInstance();
    private ArrayList<Movie> movies = dbs.getAllMovies();
    private ArrayList<Movie> movieTimes;



    public ShowtimeSelector(Window window, String movieName) {
        super(window);
        this.movieName = movieName;
        frame.getContentPane().removeAll();
        
        this.panel = new JPanel();
        times = new JComboBox<>();
        confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        back = new JButton("Back");
        back.addActionListener(this);
        movieTimes = new ArrayList<Movie>();
        this.display();
    }
    public void display(){
        getTimes();
        addToComboBox();
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        panel.setLayout(new GridLayout(0,1));
        panel.add(new JLabel("Please Select a Showtime", SwingConstants.CENTER), BorderLayout.CENTER);
        panel.add(times, BorderLayout.CENTER);
        panel.add(confirm, BorderLayout.CENTER);
        panel.add(back, BorderLayout.CENTER);
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);//400 width and 500 height 
        frame.setResizable(false);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(confirm)){
            new SeatSection(this, findMovie(times.getSelectedItem().toString()));
            frame.dispose();
        }   
        if(e.getSource().equals(back)){
            new MovieSelection(this);
            frame.dispose();
        }  
    }

    private void getTimes(){
        for(Movie movie : movies){
            if(movie.getName().equals(movieName)){
                movieTimes.add(movie);
            }
        }
    }

    private void addToComboBox() {
        for(Movie movie: movieTimes){
            times.addItem(movie.getTime());
        }
    }

    public Movie findMovie(String time){
        for(Movie movie: movies){
            if(movie.getName().equals(movieName) && movie.getTime().equals(time)){
                return movie;
            }
        }
        return null;
    }

}