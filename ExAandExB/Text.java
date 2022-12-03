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

    public Text(String s, int x, int y){
        str = s;
        this.x = x;
        this.y = y;
    }
    @Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawString("Hello world", x, y);
	}
}
