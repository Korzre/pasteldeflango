package world;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import graphics.Fonts;
import graphics.Spritesheet;
import main.Game;

public class Menu {

	public String[] options = {"novo jogo", "creditos", "sair"};
	public int currentOptions = 0;
	public int maxOptions = options.length -1;
	public boolean up, down, enter, pause;
	
	public Spritesheet menuText;
	public Spritesheet menuLogo;
	
	public void tick() {
		if(up) {
			up = false;
			currentOptions--;
			if(currentOptions < 0) 		
				currentOptions = maxOptions;
		}
		
		if(down) {
			down = false;
			currentOptions++;
			if(currentOptions > maxOptions)
				currentOptions = 0;
		}
		
		if(enter) {
			enter = false;
			if(options[currentOptions] == "novo jogo" || options[currentOptions] == "continuar") {
				Game.GameState ="GameOn";
				pause =false;
			}else if(options[currentOptions] == "sair") {
				System.exit(1);
			}else if(options[currentOptions] == "creditos") {
				Game.GameState = "Credits";
			}
		}
		
	}
	
	public void render(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.white);
		g.fillRect(0,0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		
		menuText = new Spritesheet("/menuText1.png");	
		menuLogo = new Spritesheet("/pastelFood1.png");
		
		g2.drawImage(menuLogo.getSprite(0, 0, 400, 353), 10, 90, 287, 270, null);
		g2.drawImage(menuText.getSprite(0, 0, 413, 101), 320, 60, 330, 85,null);
		
		g.setFont(new Font("Arial", Font.BOLD, 28));
		g.setColor(Color.BLACK);
		if(pause == false) {
			g.drawString("Novo jogo", 410, 210);
		}else{
			g.drawString("Continuar?", 410, 210);
		}
		
		g.setFont(new Font("Arial", Font.BOLD, 28));
		g.setColor(Color.BLACK);
		g.drawString("Créditos", 420, 260);
		
		g.setFont(new Font("Arial", Font.BOLD, 28));
		g.setColor(Color.BLACK);
		g.drawString("Sair", 445, 310);
		
		g.setFont(new Font("Arial", Font.ITALIC, 12));
		g.setColor(Color.BLACK);
		g.drawString("Um dos memes mais famosos da internet, agora na sua frente pastel de flango.", 6, 440);
		
			
		g.setColor(Color.ORANGE);
		g.setFont(Fonts.arrowFont);
		
		if(options[currentOptions] == "novo jogo") {
			g.drawString(">", 380, 210);
		}else if(options[currentOptions] == "creditos") {
			g.drawString(">", 380, 260);
		}else if(options[currentOptions] == "sair") {
			g.drawString(">", 380, 310);
		}
		
		g2.dispose();
		
	}
	
	
}
