import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;


public class UserRegistration extends Decorator implements ActionListener{
    private JPanel panel;
    private JTextField name;
    private JTextField password;
    private JTextField email;
    private JTextField creditCard;
    

    private JLabel labelName;
    private JLabel labelPassword;
    private JLabel labelEmail;
    private JLabel labelCreditCard;

    private JButton submit;
    private JButton back;
    private Movie movie;
    private Seat seat;

    public UserRegistration(Window window, Movie movie, Seat seat) {
        super(window);
        frame.getContentPane().removeAll();
        this.movie= movie;
        this.seat =seat;
        this.panel = new JPanel();
        name = new JTextField(7);
        password = new JTextField(7);
        email = new JTextField(7);
        creditCard = new JTextField(7);
        labelName = new JLabel("Name");
        labelPassword = new JLabel("Password");
        labelEmail = new JLabel("Email");
        labelCreditCard = new JLabel("CreditCard");
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
        panel.add(labelEmail,BorderLayout.WEST);
        panel.add(email,BorderLayout.CENTER);
        panel.add(labelCreditCard,BorderLayout.WEST);
        panel.add(creditCard,BorderLayout.CENTER);
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
            new MovieSelection(this);
            frame.dispose();
            //String userName = name.getText();
            //String userPassword = password.getText();
            if(name.getText().isBlank() || password.getText().isBlank() || creditCard.getText().isBlank() || email.getText().isBlank()){
                name.setText(null);
                password.setText(null);
                email.setText(null);
                creditCard.setText(null);
            }
            else{
                User user = new User(name.getText(), password.getText(), email.getText(), creditCard.getText());
                user.insertUserDB();
                try {
                    new Payment(this, user, movie, seat);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                frame.dispose();
            }
        }
        else{
            name.setText(null);
            password.setText(null);
            email.setText(null);
            creditCard.setText(null);
        }
    }



    

}
