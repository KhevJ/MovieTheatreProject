import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class UserValidation extends Decorator implements ActionListener{
    public Database dbs = Database.getInstance();
    private JPanel panel;
    private JTextField name;
    private JTextField password;
    private JLabel labelName;
    private JLabel labelPassword;
    private JButton submit;
    private JButton back;
    private Movie movie;
    private Seat seat;

  



    public UserValidation(Window window, Movie movie, Seat seat) {
        super(window);
        this.movie = movie;
        this.seat = seat;
        panel = new JPanel();
        frame.getContentPane().removeAll();
        name = new JTextField(7);
        password = new JTextField(7);
        labelName = new JLabel("Username:");
        labelPassword = new JLabel("Password:");
        submit = new JButton("Submit");
        submit.addActionListener(this);
        back = new JButton("Back");
        back.addActionListener(this);
        this.display();
    }

    public void display(){
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        panel.setLayout(new GridLayout(0,1));
        panel.add(labelName,BorderLayout.WEST);
        panel.add(name,BorderLayout.CENTER);
        panel.add(labelPassword,BorderLayout.WEST);
        panel.add(password,BorderLayout.CENTER);
        panel.add(submit,BorderLayout.CENTER);
        panel.add(back, BorderLayout.CENTER);
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);//400 width and 500 height 
        frame.setResizable(false);
        frame.setVisible(true);
        //window.display();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(back)){
            new Login(this, movie, seat);
            frame.dispose();
        }
        if(e.getSource().equals(submit)){
            String userName = name.getText();
            String userPassword = password.getText();
            try {
                if(dbs.verifyUser(userName, userPassword)){
                    if(movie != null && seat != null){
                        new Payment(this , dbs.getUser(userName, userPassword), movie ,seat );
                        frame.dispose();
                    }
                    else{
                        new Payment(this, dbs.getUser(userName, userPassword));
                        frame.dispose();
                    }
                    
                    
                }
                else{
                    name.setText(null);
                    password.setText(null);
                }
            } catch (SQLException e1) {
                
                e1.printStackTrace();
            }
        }
        else{
            name.setText(null);
            password.setText(null);
        }
    }

    

}