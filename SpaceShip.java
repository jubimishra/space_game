import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Rectangle;

public class SpaceShip {
	private int xPos;
	private int yPos;
	private boolean shooting;
	private Color clr = Color.red;
	private final int WIDTH = 80, HEIGHT = 80;
	
	public SpaceShip() {
		Random r = new Random();
		xPos = r.nextInt(251);
		yPos = r.nextInt(251);
	}
	
	public SpaceShip(int x, int y) {
		xPos = x;
		yPos = y;
	}

	public void setShooting(boolean s) {
		shooting = s;
	}
	//Sets the color according to given color
	public void setColor(Color newclr) {
        this.clr = newclr;
        
    }
	//Draws a Space Ship!
	public void draw (Graphics page, int x) {
	   //Shooting line
	   if (this.shooting == true) {
		   page.setColor(Color.red);
		   //page.drawLine(xPos + WIDTH, yPos + 30, x, yPos + 30);
		   page.fillRect(xPos + WIDTH, yPos + 30, 1000, 5);
		   //page.draw3DRect(xPos + WIDTH, yPos + 30, 5, 5, true);
       }
	  
	   //Body of space ship
	   page.setColor(this.clr);
	   page.fillOval(xPos, yPos, WIDTH,HEIGHT);
	   
	   //Ovals for the eyes of the space ship
	   page.setColor(Color.black);
	   page.fillOval(xPos + 60,yPos + 15, 10, 10);
	   page.fillOval(xPos + 60,yPos + 35, 10, 10);
	   
	   //Lower part of space ship
	   page.setColor(Color.green);
	   page.fillArc(xPos, yPos, WIDTH, HEIGHT, 90, 180); 
	   
	   //Wings of the space ship
	   page.drawLine(xPos, yPos, xPos + 30, yPos + 30);
	   page.drawLine(xPos, yPos, xPos - 30, yPos);
	   page.drawLine(xPos - 30, yPos, xPos, yPos + 30);
	   page.drawLine(xPos, yPos + 30, xPos - 30, yPos + 60);
	   page.drawLine(xPos, yPos + 60, xPos - 30, yPos + 60);
	   page.drawLine(xPos, yPos + 60, xPos + 30, yPos + 30);
	   
	   //int[] xbound = { xPos, xPos+WIDTH, xPos+HEIGHT, xPos, xPos};
	   //int[] ybound = { yPos, yPos, yPos+HEIGHT, yPos+HEIGHT, yPos };
	   //page.drawPolyline(xbound, ybound, xbound.length);
	   //page.setColor(Color.black);
	   //page.drawRect((int) getX() , (int) getY(), WIDTH-10, HEIGHT-10);
	}
	
	public void move(int x, int y) {
		xPos = x;
		yPos = y;
    }
	
	public int getX(){
		return xPos;
	}
	public int getY(){
		return yPos;
	}
	public Rectangle getBounds() {
		//return new Rectangle((int) getX()-14 , (int) getY()-15 , WIDTH, HEIGHT);
		return new Rectangle((int) getX()-14 , (int) getY()-15 , WIDTH, HEIGHT);
	}
}
