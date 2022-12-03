/*
* File Name: Text.java
* Assignment: Lab 6 Exercise A and B
* Completed by: Hajin Kim
* Submission Date: Nov 16, 2022
*/
import java.awt.Graphics;
import java.awt.Graphics2D;
public class Text implements Component{
    public String str;
    public int x,y;
    public String name = "hajin";
    public String email = "kimhajin2001@gmail.com";
    public String amount = "$20.00";
    public String title = "Receipt";

    public Text(String s, int x, int y){
        str = s;
        this.x = x;
        this.y = y;
    }
    @Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
        g2d.drawString(title, 100, 25);
        if(title.equals("Receipt")){
            g2d.drawString("Name:", 30, 50);
            g2d.drawString("Email:", 30, 70);
            g2d.drawString("Amount:", 30, 90);
            g2d.drawString(name, 80, 50);
            g2d.drawString(email, 80, 70);
            g2d.drawString(amount, 80, 90);
        }
        else if(title.equals("Movie")){
            g2d.drawString("Name:", 30, 50);
            g2d.drawString("Email:", 30, 70);
            g2d.drawString("Amount:", 30, 90);
            g2d.drawString(name, 80, 50);
            g2d.drawString(email, 80, 70);
            g2d.drawString(amount, 80, 90);
        }
		
        

	}
}
