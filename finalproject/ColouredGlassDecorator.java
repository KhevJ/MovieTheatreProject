/*
* File Name: ColouredGlassDecorator.java
* Assignment: Lab 6 Exercise A and B
* Completed by: Hajin Kim
* Submission Date: Nov 16, 2022
*/
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
public class ColouredGlassDecorator extends Decorator {

    public ColouredGlassDecorator(Component T, int x, int y, int width, int height) {
        super(T, x, y, width, height);
        //TODO Auto-generated constructor stub
    }
    public void draw(Graphics g) {
		t.draw(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.green);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1 * 0.1f));
		g2d.fillRect(x, y, width, height);
	}
}
