package gui;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class StartScreen extends Decorator implements ActionListener  {
    private JPanel panel;
    private JPanel buttonPanel;
    private JButton book;
    private JButton cancel;
    private JButton ANNpayment;


    public StartScreen(Window window){
        super(window);
        book = new JButton("Book");
        cancel = new JButton("Cancel Ticket");
        ANNpayment = new JButton("Annual Payment");
        panel = new JPanel();
        buttonPanel = new JPanel();
        ANNpayment.addActionListener(this);
        book.addActionListener(this);
        cancel.addActionListener(this);
        this.display();
    }

    public void display(){
        panel.setLayout(new BorderLayout());
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(book);
        buttonPanel.add(cancel);
        buttonPanel.add(ANNpayment);
        panel.add(new JLabel("What Would You Like To Do?", SwingConstants.CENTER), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,150);//400 width and 500 height 
        frame.setResizable(false);
        frame.setVisible(true);
        //window.display();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(book)){
            new MovieSelection(this);
            frame.dispose();
        }
        else if(e.getSource().equals(cancel)){
            new cancelScreen(this);
            frame.dispose();
        }

        else if(e.getSource().equals(ANNpayment)){
            new UserValidation(window, null, null);
            frame.dispose();
        }
    }       
}