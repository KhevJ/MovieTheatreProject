/*
* File Name: ColouredFrameDecorator.java
* Assignment: Lab 6 Exercise A and B
* Completed by: Hajin Kim
* Submission Date: Nov 16, 2022
*/
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
public class ColouredFrameDecorator extends Decorator{
    public int thickness;

    public ColouredFrameDecorator(Component t, int x, int y, int width, int height, int thickness) {
        super(t, x, y, width, height);
		this.thickness = thickness;
    }

    public void draw(Graphics g) {
		t.draw(g);
		Graphics2D g2d = (Graphics2D) g;
		Stroke oldStroke = g2d.getStroke();
		Color oldColor = g2d.getColor();
		g2d.setStroke(new BasicStroke(thickness));
		g2d.setColor(Color.red);
		g2d.drawRect(x, y, width, height);
		g2d.setStroke(oldStroke);
		g2d.setColor(oldColor);
	}
    
}
