import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class cancelScreen extends Decorator implements ActionListener{
    private JPanel panel;
    private JPanel buttons;
    private JButton confirm;
    private JButton back;
    private JTextField ticketID;
    private Database dbs = Database.getInstance();
    private LocalDateTime date;

    public cancelScreen(Window window){
        super(window);
        dbs.checkTicket();
        this.panel = new JPanel();
        panel.setLayout(new BorderLayout());
        this.buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        this.confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        this.back = new JButton("Back");
        back.addActionListener(this);
        this.ticketID = new JTextField(7);
        buttons.add(confirm);
        buttons.add(back);
        panel.add(new JLabel("Please Enter Your Ticket ID.", SwingConstants.CENTER), BorderLayout.NORTH);
        panel.add(ticketID, BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);
        date  = LocalDateTime.now();
        this.display();
    }
    public void display(){
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(back)){
            new StartScreen(window);
            frame.dispose();
        }
        if(e.getSource().equals(confirm)){
            String id = ticketID.getText();
            
            if(isInteger(id)){
                
                int number = Integer.parseInt(id);
                
                try {
                    if (dbs.verifyTicket(number)){ 
                       if(dbs.verifyRegistered(dbs.getEmailFromTicket(number))){
                        dbs.insertCredit(dbs.getEmailFromTicket(number), dbs.getAmountFromTicket(number), getTime(date));
                        
                       }
                       else{
                        float value =  (float) (dbs.getAmountFromTicket(number) * 0.85);
                        dbs.insertCredit(dbs.getEmailFromTicket(number), value, getTime(date));

                       }
                        dbs.removeTicket(number);
                        new success(window, true);
                        frame.dispose();
                       }
                       else{
                          new success(window, false);
                          frame.dispose();
                       }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

            else{
                new success(window, false);
                frame.dispose();
            }

            
            
        }
        
    }

    public  boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException e) { 
            return false; 
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }

    private String getTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = time.format(formatter);

        return formattedDateTime;
    }


}
    
