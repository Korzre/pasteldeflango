package world;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import graphics.Fonts;
import graphics.Spritesheet;
import main.Game;

public class Credits {

	public Spritesheet madeBy;
	public Spritesheet pastelBanner;
	public Spritesheet Background;
	public boolean showMessage = true;
	public int frames = 0;
	
	public void tick() {
		this.frames++;
		
		if(this.frames  == 10) { 
			this.frames =0;
			
			if(this.showMessage) {
				
				this.showMessage =false;
				
			}else {						
				this.showMessage = true;
			}
		}
		
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Background = new Spritesheet("/BackGroundPastel.jpeg");
		madeBy = new Spritesheet("/korzre.PNG");
		pastelBanner = new Spritesheet("/pastelFood1.png");
		
		g2.drawImage(Background.getSprite(1,2, Game.WIDTH*Game.SCALE+175, Game.HEIGHT*Game.SCALE+175), 0,0,Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE, null);

		g.setColor(new Color(0,0,0,100));		
		g.fillRect(0,0,Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		
		
		g2.drawImage(pastelBanner.getSprite(0, 0, 400, 353), ((Game.WIDTH*Game.SCALE)/2-90), 30, 124, 112, null);
		if(showMessage) {
			g2.drawImage(madeBy.getSprite(0, 0, 273, 92), ((Game.WIDTH*Game.SCALE)/2-160), ((Game.WIDTH*Game.SCALE)/2-220), 280, 100, null);
		}
		
		g.setFont(Fonts.font3L);
		g.setColor(Color.WHITE);
		g.drawString("2020", ((Game.WIDTH*Game.SCALE)/2-80), 280);
		
		g.setFont(Fonts.font4L);
		g.setColor(Color.WHITE);
		g.drawString("Special thanks to Bruno.", ((Game.WIDTH*Game.SCALE)/2-100), 430);

	}
	
}
