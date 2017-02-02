import java.awt.Color;
import java.awt.Graphics;

public class Brick implements BrickPrototype{
	int x, y;
	final int WIDTH = 58;
	final int HEIGHT = 30;
	boolean destroyed;
	
	public Brick(int xLoc, int yLoc) {
		// draw first brick in upper left hand corner
		x = xLoc;
		y = yLoc;
		destroyed = false;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(x, y, WIDTH, HEIGHT);
				
	}
	
//	public void collisionDetection(Ball b1) {
//		if (b1.getX() >= this.x && b1.getX() <= this.x + this.WIDTH && b1.getY() <= this.y + this.HEIGHT){
//			destroyed = true;
//		}
//		
//	}

	public int getX() {
		return x;
		
	}

	public int getY() {
		return y;
		
	}

}
