import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MovieSelection extends Decorator implements ActionListener {
    private JPanel panel;
    private JButton confirm;
    private JComboBox movies;
    private Database dbs = Database.getInstance();
    private ArrayList<Movie> Movies = dbs.getAllMovies();
    private ArrayList<String> distinctNames;

   

    public MovieSelection(Window window){
        super(window);
       
        frame.getContentPane().removeAll();
        distinctNames = getAllDistinctNames();
        this.panel = new JPanel();
        movies = new JComboBox<>();
        confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        
        this.display();
    }
   
    public void display(){
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        panel.setLayout(new GridLayout(0,1));
        panel.add(new JLabel("Please Select a Movie", SwingConstants.CENTER), BorderLayout.NORTH);
        addToComboBox();
        panel.add(movies, BorderLayout.CENTER);
        panel.add(confirm, BorderLayout.SOUTH);
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
            new ShowtimeSelector(this,  movies.getSelectedItem().toString());
            frame.dispose();
        }     
    }


   

    public ArrayList<String> getAllDistinctNames(){
        ArrayList<String> names = new ArrayList<>();
        for(Movie movie: Movies){
            names.add(movie.getName());
        }
        ArrayList<String> distinctNames = new ArrayList<String>();
  
        
        for (String element : names) {
  
            // If this element is not present in newList
            // then add it
            if (!distinctNames.contains(element)) {
  
                distinctNames.add(element);
            }
        }
        // return the new list
        return distinctNames;
    }

    private void addToComboBox() {
        for(String name:distinctNames){
           movies.addItem(name);
        }
    }
}