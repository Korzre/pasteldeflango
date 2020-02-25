package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import graphics.Spritesheet;


public class Pastel {

	public static int x;
	private static int y;
	private int width=50;
	private int height=40;
	
	public static double velocity=1;
	private Spritesheet pastel;
		
	public Pastel(int x, int y) {
		Pastel.x = x;
		Pastel.y=  y;	
		pastel = new Spritesheet("/pastelFood.png");		
	}
	
	public static int getX() {
		return x;
	}
	
	public static int getY() {
		return y;
	}
	
	public void tick() {		
		y=(int) (y+velocity);
//		if(y ==230) {
//			y=-30;
//		}
	}
	
//	public void setX(int x) {
//		this.x = x;
//	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void render(Graphics g) {
//		g.setColor(Color.orange);
//		g.fillRect(50, 70, 120, 30);
		g.drawImage(pastel.getSprite(1, 1, 160, 145), Pastel.getX(),Pastel.getY(),this.getWidth(),this.getHeight(),null);
	}
}
