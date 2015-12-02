/* Name: Bhawana Mishra

*/

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.awt.event.MouseListener;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.concurrent.TimeUnit;

public class Screen extends JPanel implements ActionListener, ComponentListener, MouseMotionListener, MouseListener, KeyListener{

	private  SpaceShip ship;
	private Star[] stars;
	private Timer timer1;
	private Timer timer2;
	private Timer timer3;
	private ArrayList<Asteroid> ast = new ArrayList<Asteroid>();
	private final int NUM_ASTEROIDS= 15; 
	private final int NUM_STARS= 100; 
	private final int DELAY = 500;
	private final int DELAY1 = 500;
	static final int DIM = 600;
	private int time_left = 15;
	private int current_score = 0;
	private int lives_left = 3;
	private String msg1="Time Left : ";
	private String msg2="Current Score : ";
	private String msg3="Lives Left : ";
	private String msg4="";
	

	public Screen() {
		timer1 = new Timer(DELAY, this);
		timer2 = new Timer(DELAY1, this);
		timer3 = new Timer(500, this);
		//New space ships
		ship = new SpaceShip();
		//Changing color for default space ships
		ship.setColor(Color.blue);
		// Array of 100 Stars in the space
		stars = new Star[100];
		for(int i = 0; i<NUM_STARS; i++){
			stars[i]=new Star(DIM,DIM);
		}
		//Start of timer
		timer1.start();
		timer2.start();
		//Array of 15 asteroids
		for(int i = 0; i<NUM_ASTEROIDS; i++){
			ast.add(new Asteroid(DIM,DIM));
		}
		addMouseMotionListener(this);
		addMouseListener(this);
		addComponentListener(this);
		addKeyListener(this);
		this.setFocusable(true);
		//Setting background of space as black
		setBackground (Color.black);
	    setPreferredSize (new Dimension(600, 600));
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		setBackground(Color.black);
		//Drawing stars
		for(int i = 0; i<100; i++){
			stars[i].drawStar(g);
		}
		//Drawing Asteroids
		for(int i = 0; i<ast.size(); i++){
			ast.get(i).drawAst(g);
		}
		//Drawing SpaceShips
		ship.draw(g,2000);
		g.setColor(Color.white);
		//Draws messages and other game info
		g.setFont(new Font("TimesRoman", Font.BOLD, 20));
		g.drawString(msg1+" "+ Integer.toString(time_left), 10, 20);
		g.drawString(msg2+" "+ Integer.toString(current_score), 10, 40);
		g.drawString(msg3+" "+ Integer.toString(lives_left), 10, 60);
		g.setColor(Color.red);
		g.setFont(new Font("TimesRoman", Font.ITALIC, 30));
		g.drawString(msg4, 150, 300);
		 
	}
	
	public static void main (String[] args)
	{
		JFrame frame = new JFrame ("Space Ships!!");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		Screen panel = new Screen();
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed (ActionEvent e)
	{
		//Removes the messages HIT and COLLIION DETECTED after sometime
		if (e.getSource() == timer3){

			if (!msg4.equals("*****GAME OVER*****")){
				msg4 = "";
				timer3.stop();
			}
			}		
		
		//Stops the game timer if time is over
		if (e.getSource() == timer2){
			if (time_left>0){time_left-=1;}
			else {
				msg4 = "*****GAME OVER*****";
				timer1.stop();
			}
			}
		
		//Performs action if the source is a timer
		if (e.getSource() == timer1) {
			// Moves the asteroids 30% of the time
			for (int i = 0; i < ast.size(); i++) {
				Random r = new Random();
				if(r.nextInt(100)+1 <=30)
					ast.get(i).move();
			}
		}
		repaint();	
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	//Moves the SpaceShip with Mouse movement
		if(lives_left!=0 && time_left!=0){
		
		ship.move(e.getX()-25,e.getY()-10);
		// Detects Collision of Spaceship and asteroids and reduces number of lives and removes the asteroid
		for(int i=0; i<ast.size(); i++){
			if (ship.getBounds().intersects(ast.get(i).getBounds())){
				Collision();
				timer3.start();
				ast.remove(i);
			}
		}
		}
		if (lives_left==0){
			msg4="*****GAME OVER*****";
			timer1.stop();
		}
		repaint();
	}
	
	// Messages to be displayed and colors of spaceship depending on number of lives left
	public void Collision(){
		if(lives_left==3){
			msg4 = "COLLISION DETECTED!!";
			ship.setColor(Color.yellow);
			lives_left--;
		}
		else if (lives_left==2){
			msg4 = "COLLISION DETECTED!!";
			ship.setColor(Color.red);
			lives_left--;
		}
		else if (lives_left==1){
			msg4 = "COLLISION DETECTED!!";
			ship.setColor(Color.red);
			lives_left--;
		}
	
		try{
			TimeUnit.SECONDS.sleep(1);
		}
		catch(InterruptedException e){
			
		}

	}
	@Override
	public void mousePressed(MouseEvent e) {
//	Fires when Mouse is pressed
		ship.setShooting(true);
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
//	Stops firing when mouse is released
		ship.setShooting(false);
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//Removes the asteroid if shot by the Spaceship, increases the score, displays message on screen
		if(lives_left!=0 && time_left != 0 ){
		for(int i=0; i<ast.size(); i++){
			if (ast.get(i).getBounds().contains(new Point(ast.get(i).getX(), e.getY())) && e.getX() < ast.get(i).getX() )
			{
				timer3.start();
				msg4="HIT";
				if(lives_left!=0){
				current_score +=1;
				}
				ast.remove(i);
				
			}

		}
		}
		else{
			timer1.stop();
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent e) {
//		Gets screen size and width
		Dimension newSize = e.getComponent().getBounds().getSize();
//		Clears existing asteroids
		ast.clear();
//		Stars spread across the resized screen
		for(int i = 0; i<NUM_STARS; i++){
			stars[i]=new Star(newSize.width,newSize.height);
		}
		
//		Asteroids spread across the resized screen
		for(int i = 0; i<NUM_ASTEROIDS; i++){
			ast.add(new Asteroid(newSize.width,newSize.height));
		}
		repaint();
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//Shoots asteroids with Space bar key, removes them and displays the message HIT
		 if (e.getKeyCode() == KeyEvent.VK_SPACE) {
	            ship.setShooting(true);
	            
	            for (int i = 0; i < ast.size(); i++) {
	                
	            	if ((MouseInfo.getPointerInfo().getLocation().getY() <(ast.get(i).getY()+15)) 
	            			&& (MouseInfo.getPointerInfo().getLocation().getY()> (ast.get(i).getY()-15))
	                        && MouseInfo.getPointerInfo().getLocation().getX() < ast.get(i).getX()) {
	             
	                    msg4 = "HIT";
	                    ast.remove(i);
	                    current_score += 1;
	                    timer3.start();
	                }
	            }
	            repaint();
	        }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
	            ship.setShooting(false);
	            repaint();
	        }
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}

