import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Polygon;
import java.awt.Rectangle;

public class Asteroid {
	private int xPos;
	private int yPos;
	Color astColor;
	private int rad;
	private Polygon asteroidBorder;
	
	//Constructor Asteroid with parameters of screen height and width as input
	public Asteroid(int width, int height){
		Random r = new Random();
		xPos = r.nextInt(width);
		yPos = r.nextInt(height);
		astColor = Color.gray;
		rad = r.nextInt(20)+15;
	}
	// Draws an asteroid
	public void drawAst(Graphics g){
		g.setColor(astColor);
		g.fillOval(xPos, yPos,rad, rad);
		//g.drawRect(xPos, yPos, rad, rad);
		//g.drawRect(getX()-14, (int) getY()-15, rad, rad);
//		int[] x = { xPos, xPos+rad, xPos+rad,xPos, xPos};
//		int[] y = { yPos, yPos, yPos+rad, yPos+rad, yPos};
//		asteroidBorder= new Polygon(x, y, x.length);
//		g.drawPolygon(asteroidBorder);
	}
	public void move(){
		Random r = new Random();
		xPos += r.nextInt(31) - 15;
		yPos += r.nextInt(31) - 15;
	}
	
	public Polygon getAst(){
		return asteroidBorder;
	}
	
	public int getX(){
		return xPos;
	}
	public int getY(){
		return yPos;
	}
	public Rectangle getBounds() {
		//return new Rectangle((int) getX()-14, (int) getY()-15, rad, rad);
		return new Rectangle((int) getX()-14, (int) getY()-15, rad, rad);
	}
}

