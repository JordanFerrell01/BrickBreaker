import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class BrickBreaker extends Applet implements Runnable, KeyListener, MouseMotionListener{
	final int WIDTH = 500, HEIGHT = 700;
	Thread thread;
	HumanPaddle paddle;
	Ball b1;
	boolean alive = false;
	Image background, ball, brick;

// **** CREATE ALL OBJECTS IN GAME **** //
	
	/* JRF - CREATE ARRAYLIST OF BRICKS */
	ArrayList<Brick> bricksX = new ArrayList<Brick>();
	
	public void init(){
		this.resize(WIDTH, HEIGHT);
		this.addKeyListener(this);
		this.addMouseMotionListener(this);
		paddle = new HumanPaddle();
		b1 = new Ball();
		background = getImage(getDocumentBase(), "BrickBreaker.png");
		ball = getImage(getDocumentBase(), "ball.png");
		brick = getImage(getDocumentBase(), "brick.png");
		
		//Create all the bricks row (58 pixels wide) and add them to the array
		int incrementer = 3;
		int counter = 0;
		for (int i = 0; i < 4; i++) { //number of rows
			for (int j = 0; j < 8; j++){ // number of columns ** DONT TOUCH ** since size is hard coded
				bricksX.add(counter, new Brick(4 + (j*58) + incrementer, 4 + (34*i))); // add a new brick 4 spaces from the left + the index * width of block, by 4 pixels high + the index * height of the block
				incrementer +=3;
				counter++;
			}
			incrementer = 3;  //reset the row spacing
		}
		
		thread = new Thread(this);
		thread.start();		
	}
	
// **** DRAW THE OBJECTS **** //	
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.drawImage(background, 0, 0, this);
		paddle.draw(g);
		//b1.draw(g);
		g.drawImage(ball, b1.getX() - 8, b1.getY()-8, this);
		
		
		//draw all the bricks in the array
		for(int i = 0; i < bricksX.size(); i++){
//			bricksX.get(i).draw(g);
			g.drawImage(brick, bricksX.get(i).getX(), bricksX.get(i).getY(), this);
		}
		
		if (!alive) {
		g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		g.setColor(Color.WHITE);
		g.drawString("Press ENTER to begin...", 155, 400);
		}

	}
	
	public void update(Graphics g){
		paint(g);
	}
	
// **** GAMEPLAY **** //	
	public void run() {
		for(;;) { //infinite loop
			if (alive){
				paddle.move();
				b1.move();
				b1.checkPaddleCollision(paddle);
			}
			
			// check to see if the ball hit the brick
			for(int i = 0; i < bricksX.size(); i++){
				b1.collisionDetection(bricksX.get(i));
				if (bricksX.get(i).destroyed){
					bricksX.remove(i);
				}
			}
			
			repaint(); //used by the Applet
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
// **** MOUSE AND KEYBOARD ACTION LISTENERS **** //	
	public void keyTyped(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			paddle.leftAccel = true;
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			paddle.rightAccel = true;
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			paddle.setLeftAccel(true);
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			paddle.setRightAccel(true); 
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER){
			alive = true;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			paddle.setLeftAccel(false);
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			paddle.setRightAccel(false);
	}


	public void mouseDragged(MouseEvent e) {	}


	public void mouseMoved(MouseEvent e) {
		paddle.setX(e.getX());
		
	}

	
	
}
