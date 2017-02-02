import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Ball {
	double xVel, yVel, x, y;
	
	public Ball() {
		x = 250;  // ball is set to the center of the screen
		y = 350;
//		xVel = getRandomSpeed() * getRandomDirection();
		xVel = 2;		
		yVel = 4;
	}	
	
	public void draw(Graphics g){
		g.setColor(Color.WHITE);
		g.fillOval((int)x-8, (int)y-8, 16, 16); //minus ten because the image is draw from the top left corner but is 20 pixels tall
	}	
	
	public void checkPaddleCollision(Paddle p1){
		if (y >= 672){
			if(x >= p1.getX() && x <= p1.getX() + 80){
				yVel = -yVel;
				// Check if hit edge of paddle (1st 20 pixels or last 20 pixels)
				//Hit the left side and ball is going to the right
				if(x >= p1.getX() && x<= p1.getX() + 20 && xVel > 0){
					xVel = -xVel;
				}
				//Hit the right side and the ball is going to the left
				if (x >= p1.getX() + 60 && x <= p1.getX() + 80 && xVel < 0){
					xVel = -xVel;
				}
			}			
		}
	}	
	
	public void collisionDetection(Brick br1) {
		if (this.x-8 >= br1.getX() && this.x-8 <= br1.getX() + br1.WIDTH  && this.y-8 < br1.getY() + br1.HEIGHT){
			br1.destroyed = true;
			yVel = -yVel;
		}
		
	}
	
	public void move(){
		x += xVel;
		y += yVel;
		
		if(y < 8) //top edge
			yVel = -yVel;  
		if(y > 692) //bottom edge
			yVel = -yVel;	
		if(x < 8) //left edge
			xVel = -xVel;  
		if(x > 492) //right edge
			xVel = -xVel;			
	}
	
	public int getX(){
		return (int)x;
	}
	
	public int getY(){
		return (int)y;
	}	

}
