import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class success extends Decorator implements ActionListener{
    private JPanel panel;
    private JButton okButton;
    public success(Window window, boolean check){
        super(window);
        this.panel = new JPanel();
        panel.setLayout(new BorderLayout());
        this.okButton = new JButton("Ok");
        okButton.addActionListener(this);
        if(check == true){
            panel.add(new JLabel("Successful!", SwingConstants.CENTER), BorderLayout.CENTER);
        }else{
            panel.add(new JLabel("Unsuccessful!", SwingConstants.CENTER), BorderLayout.CENTER);
        }
        panel.add(okButton, BorderLayout.SOUTH);
        this.display();
    }
    public void display(){
        frame.add(panel);
        frame.pack();
        frame.setSize(200, 100);//400 width and 500 height 
        frame.setResizable(false);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    if(e.getSource().equals(okButton)){
        frame.dispose();
        return;
    }        
    }
    
}