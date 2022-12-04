import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class Payment extends Decorator implements ActionListener {
    private Movie movie;
    private User user;
    private JPanel panel;
    private JPanel buttons;
    private JButton cancel;
    private JButton unregister;
    private JButton confirm;
    private JButton annualPay;
    private JButton regConfirm;
    private JButton regCancel;
    private JPanel info;
    private Seat seat;
    private JTextField email;
    private JTextField creditCard;
    private int height;
    private Ticket ticket;
    private Database dbs = Database.getInstance();
    private float amount = 0;
    
    
    public Payment(Window window, User user){ // For Annual Payment
        super(window);
        this.user = user;
        this.height = 150;
        frame.getContentPane().removeAll();
        this.panel = new JPanel();
        panel.setLayout(new BorderLayout());
        this.buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        this.info = new JPanel();
        info.setLayout(new BoxLayout(info, 1));
        this.unregister = new JButton("Unregister");
        unregister.addActionListener(this);
        this.annualPay = new JButton("Confirm");
        annualPay.addActionListener(this);
        buttons.add(annualPay);
        buttons.add(unregister);
        panel.add(new JLabel("Would You Like to Make Your Annual Payment?", SwingConstants.CENTER),BorderLayout.NORTH);
        info.add(new JLabel("Name: "+ user.getName()));
        info.add(new JLabel("Email: " + user.getEmail()));
        panel.add(info, BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);
        this.display();
    }
    public Payment(Window window, User user, Movie movie, Seat seat) throws SQLException{ // Registered movie payment
        super(window);
        this.user = user;
        this.movie = movie;
        this.seat = seat;
        this.height = 150;
        frame.getContentPane().removeAll();
        this.panel = new JPanel();
        panel.setLayout(new BorderLayout());
        this.buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        this.info = new JPanel();
        info.setLayout(new BoxLayout(info,1));
        this.regCancel = new JButton("Cancel");
        regCancel.addActionListener(this);
        this.regConfirm = new JButton("Confirm");
        regConfirm.addActionListener(this);
        buttons.add(regConfirm);
        dbs.checkCredit();
        compareCredit(movie.getAmount(), user.getEmail());
        info.add(new JLabel("Name: " + user.getName(), SwingConstants.CENTER));
        info.add(new JLabel("Email: " + user.getEmail(), SwingConstants.CENTER));
        info.add(new JLabel("Cost:" + amount, SwingConstants.CENTER)); 
        info.add(new JLabel("Seat:" + seat.getSeatID()));
        panel.add(new JLabel("Would You Like to Make This Purchase?", SwingConstants.CENTER), BorderLayout.NORTH);
        panel.add(info, BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);
        
       
        this.display();
    }
    private void compareCredit(int value, String email) throws SQLException {
        
        if(dbs.verifyCredit(email)){
            float creditAmount = dbs.getCredit(email);
            if(creditAmount > value){
                 dbs.updateCredit(email, (creditAmount - value));
                 this.amount = 0;
                 return;
            }
            else{
                
                this.amount = value - creditAmount ;
                dbs.removeCredit(email);
                return;
            }
        }
        else{
            this.amount = value;
        }
        
        return;


    }
    public Payment(Window window, Movie movie, Seat seat) throws SQLException{ // Unregistered movie payment
        super(window);
        this.movie = movie;
        this.seat = seat;
        this.height = 200;
        frame.getContentPane().removeAll();
        this.panel = new JPanel();
        this.buttons = new JPanel();
        this.info = new JPanel();
        info.setLayout(new BoxLayout(info, 1));
        panel.setLayout(new BorderLayout());
        buttons.setLayout(new FlowLayout());
        this.cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        this.confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        this.email = new JTextField(7);
        this.creditCard = new JTextField(7);
        info.add(new JLabel("Email:"));
        info.add(email);
        info.add(new JLabel("Credit Card Number:"));
        info.add(creditCard);
        buttons.add(confirm);
        buttons.add(cancel);
        panel.add(new JLabel("Please Enter Your Information."), BorderLayout.NORTH);
        panel.add(info, BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);
       
        
        this.display();
    }
    public void display(){
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, height);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(annualPay)){
           
            new success(window, true);
            frame.dispose();
        }
        if(e.getSource().equals(unregister)){
            // take user out of the database
            try {
                if(dbs.verifyUser(user.getName(), user.getPassword())){
                    dbs.removeUser(user);

                }
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            new success(window, true);
            new StartScreen(window);
            frame.dispose();
        }
        if(e.getSource().equals(regConfirm)){
            this.ticket = new Ticket(this.user.getEmail(), this.seat.getSeatID(), this.movie, amount); 
            new receiptScreen(this, this.movie, this.ticket);
            new success(window, true);
            frame.dispose();
        }
       
        if(e.getSource().equals(confirm)){
            dbs.checkCredit();
            String emailString = email.getText();
            String creditString = creditCard.getText();
            
            
            if(isValidEmail(emailString) && is16Digits(creditString)){
                try {
                    compareCredit(movie.getAmount(), emailString);
                } catch (SQLException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }
                    this.ticket = new Ticket(emailString, this.seat.getSeatID(), this.movie, amount); 
                    new receiptScreen(this, this.movie, this.ticket);
                    new success(window, true);
                frame.dispose();
            }else{
                    try {
                        new Payment(window, movie, seat);
                    } catch (SQLException e1) {
                        
                        e1.printStackTrace();
                    }
                    new success(window, false);
                frame.dispose();
            }
        }
        
    }

    public  Boolean is16Digits(String str){
        int len = str.length();
        if(len == 16){
            for(int i=0; i<len;i++){
                if(Character.isDigit(str.charAt(i))==false){
                    return false;
                }
            }
            return true;
        }
        else{
            return false;
        }
    }
   
    public Boolean isValidEmail(String str){
        if(str.contains("@")){
            String a[] = str.split("@",2);
            
            if(a[1].contains(".")){
                String b[] = a[1].split("\\.",2);
                String checkall = a[0]+b[0]+b[1];
                int len = checkall.length();
                for(int i=0; i<len;i++){
                    if(Character.isLetterOrDigit(checkall.charAt(i))==false){
                        return false;
                    }
                }
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
}
