package entities;

import java.awt.Graphics;
import java.awt.Rectangle;



import graphics.Spritesheet;
import main.Game;

public class Mouth{
	
	private int x, y;
	public boolean right, left;
	private final double velocity = 2;
	public Spritesheet mouth;
	public int width=70;
	public int height = 40;
	public Mouth(int x, int y) {
		this.x =x;
		this.y = y;		
		mouth = new Spritesheet("/OpenMouth.png");
	}
	
	public void tick() {
		if(right) {
			x+=velocity;
		}else if(left) {
			x-=velocity;
		}
		
		if(x+width > 510) {
			x = 510 - width;
		}else if (x<0) {
			x=0;
		}
		
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void render(Graphics g) {
		g.drawImage(mouth.getSprite(1, 1, 1194, 950), this.getX(),this.getY(),width,height,null);
	}
	
//	public static boolean isColliding(Entity e1, Entity e2) {
//		Rectangle e1Mask = new Rectangle(e1.getX() + e1.maskx, e1.getY()+e1.masky, e1.mw,e1.mh);
//		Rectangle e2Mask = new Rectangle(e2.getX() + e2.maskx, e2.getY()+e2.masky, e2.mw,e2.mh);
//
//		return e1Mask.intersects(e2Mask);
//	}
	
}
