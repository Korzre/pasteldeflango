package world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import entities.Pastel;
import main.Game;

public class Floor {

	public int width;
	public int height;
	public int x;
	public int y;
	public Pastel pastel;
	public Game game;
	public Floor(int x , int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		pastel = new Pastel(Game.x, Game.y);
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
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
	
	public boolean isColliding() {	
	Rectangle floorX = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	Rectangle pastelX = new Rectangle(pastel.getX(), pastel.getY(), pastel.getWidth(), pastel.getHeight());
		return pastelX.intersects(floorX);				
	}
	
}
