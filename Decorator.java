import javax.swing.*;

public abstract class Decorator implements Window {
    public Window window;
    public JFrame frame;
    
    public Decorator(Window window){
        this.window = window;
        frame = new JFrame("Movie Theatre");
    }

    @Override
    public void display() {
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,150);//400 width and 500 height 
        frame.setVisible(true);
        window.display();
    }
    
}
