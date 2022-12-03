/*
* File Name: BorderDecorator.java
* Assignment: Lab 6 Exercise A and B
* Completed by: Hajin Kim
* Submission Date: Nov 16, 2022
*/
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class BorderDecorator extends Decorator{
    public BorderDecorator(Component t, int x, int y, int width, int height) {
		super(t, x, y, width, height);
	}
    public void draw(Graphics g) {
		t.draw(g);
		Graphics2D g2d = (Graphics2D) g;
		Stroke oldStroke = g2d.getStroke();
		Color oldColor = g2d.getColor();
		g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,0, new float[] {9}, 0));
		g2d.setColor(Color.black);
		g2d.drawRect(x, y, width, height);
		g2d.setStroke(oldStroke);
		g2d.setColor(oldColor);
	}
}
