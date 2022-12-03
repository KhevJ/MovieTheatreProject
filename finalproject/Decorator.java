/*
* File Name: Decorator.java
* Assignment: Lab 6 Exercise A and B
* Completed by: Hajin Kim
* Submission Date: Nov 16, 2022
*/
import java.awt.Graphics;
public class Decorator implements Component{
    public Component t;
    public int x,y,width,height;
    public Decorator(Component T, int x, int y, int width, int height) {
        t = T;
        this.x = x;
        this.y = y;
		this.width = width;
		this.height = height;
    }
    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        
    }
}
