package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import graphics.Spritesheet;
import main.Game;
import main.Sound;


public class Mouth{
	
	private int x, y;
	public boolean right, left;
	public static double velocity = 2;
	public Spritesheet mouth;
	public int width=70;
	public int height = 40;
	
	public Pastel pastel;
	
	private final int subX=1;
	private final int subY=1;
	private final int subW=1194;
	private final int subH=950;
	
	private final int origin=-5;
	private final int toaster = 510;
	
	public Mouth(int x, int y) {
		this.x =x;
		this.y = y;		
		pastel = new Pastel(Game.x, Game.y);
		mouth = new Spritesheet("/OpenMouth.png");
	}
	
	public void tick() {
		if(right) {
			x+=velocity;
		}else if(left) {
			x-=velocity;
		}
		
		if(x+width > toaster) {
			x = toaster - width;
		}else if (x<origin) {
			x=origin;
		}
		
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void render(Graphics g) {
		g.drawImage(mouth.getSprite(subX, subY, subW, subH), this.getX(),this.getY(),this.getWidth(),this.getHeight(),null);
		//g.fillRect(this.getX()+10, 305, 48,6);
	}
	
	public boolean isColliding() {
		Rectangle mouthTouch = new Rectangle(this.getX()+10, 305, 48,6);
		Rectangle pastelTouch = new Rectangle(Pastel.getX(), Pastel.getY(), pastel.getWidth(), pastel.getHeight());
		return pastelTouch.intersects(mouthTouch);
		
	}	
}
