import java.awt.Color;
import java.awt.Graphics;

public class HumanPaddle implements Paddle{
	double x, xVel;
	boolean leftAccel, rightAccel;
	final double FRICTION = 0.94;  // to slow down the paddle
	int y;
	
	public HumanPaddle(){
		leftAccel = false; rightAccel = false;
		x = 210; xVel = 0; //center the paddle and set speed to 0
		y = 680;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect((int)x, y, 80, 10);
		
	}

	
	public void move() {
		if(leftAccel) {
			xVel -= 2; 
		} 
		else if(rightAccel){
			xVel += 2; 
		}
		else if (! leftAccel && !rightAccel){
			xVel *= FRICTION;
		}
		if(xVel >= 5)  //Cap the total speed
			xVel = 5;  
		else if (xVel <= -5) //Cap the total speed
			xVel = -5;
			
		x += xVel;
		
		// Keep the paddle on the screen and dont let if go off either side
		if (x <= 0) 
			x = 0;
		else if (x >= 420)
			x = 420;
		
	}

	
	public int getX() {
		// TODO Auto-generated method stub
		return (int) x;
	}
	public void setLeftAccel(boolean input) {
		leftAccel = input;
	}

	public void setRightAccel(boolean input) {
		rightAccel = input;
	}
	
	public void setX(int xPos) {
		this.x = xPos - 40;
	}
	

}
