package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

import world.Credits;
import world.Floor;
import world.Menu;

import javax.swing.JFrame;
import javax.swing.JRootPane;

import entities.Mouth;
import entities.Pastel;
import graphics.Fonts;
import graphics.Spritesheet;

@SuppressWarnings("serial")
public class Game extends Canvas implements Runnable, KeyListener{

	private JFrame frame;
	public static int WIDTH = 240;
	public static int HEIGHT = 160;
	public static int SCALE=3;
	
	private Thread thread;
	public BufferedImage Wall;
	private boolean isRunning;
	public Spritesheet spritesheet;
	public Mouth mouth;
	public Menu menu;
	public Credits credits;
	public Fonts fonts;
	public Sound sound;
	public Pastel pastel;
	public Random randomx;
	public Spritesheet pastelBanner;
	public Spritesheet pastelogo;
	public Spritesheet tryAgain;
	public Spritesheet sadMouth;
	public Floor floor;
	
	private boolean showMessage = false;
	private int framesContinue=0;
			
	public final int maxBorder=470;
	public final int minBorder=50;
	
	public static String GameState = "Menu";
	
	public final int mouthOrigin =-10;
	public final int mouthY = 280;
	
	public static int x;
	public static int y;

	public int count = 0;
	public int level = 1;
	
	public int[] control = {0};
	public String[] map = {"BackGroundPastel.jpeg", "BackGroundPastel5050.png","BackGroundPastel100.jpg"};
	public Spritesheet map50;
	public Spritesheet map100;
	public static int[] num= {0,1,2,3,4,5,6,7,8,9};
	
	public Game() {
		initFrames();
		Sound.swallow.loop();
		//Sound.musicBackground.loop();
		randomx = new Random();
		Wall = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		spritesheet =  new Spritesheet("/"+map[0]);
		map50 = new Spritesheet("/"+map[1]);
		map100 = new Spritesheet("/"+map[2]);
		pastelBanner = new Spritesheet("/PastelDeFlangoBanner.png");
		pastelogo = new Spritesheet ("/PastelBanner1.png");
		tryAgain = new Spritesheet("/TryAgain.png");
		sadMouth = new Spritesheet("/sadMouth.png");
		credits = new Credits();
		fonts = new Fonts();
		x= randomx.nextInt((maxBorder - minBorder)+1)+minBorder;
		y=-20;
		
		pastel = new Pastel(x,y);
		mouth = new Mouth(mouthOrigin,mouthY);
		floor = new Floor(0,320,650, 10);
		menu = new Menu();		
		
	}
	
	public void initFrames() {	
		addKeyListener(this);
		frame = new JFrame("Pastel de Flango!");
		frame.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(this);
		
		Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icon1.png"));    
		frame.setIconImage(icon);   		
	}
	
	public synchronized void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() throws InterruptedException {
		thread.join();
	}
	
	public void tick() {
	switch(GameState) {
		case "GameOn":
			mouth.tick();
			pastel.tick();
			if(floor.isColliding()) {
				GameState = "Lose";		
			}
			
			if(mouth.isColliding()) {
				Pastel.velocity = randomx.nextInt((3 - 1)+1)+1;
				Mouth.velocity = 3;
				level = randomx.nextInt((2 - 1)+1)+1;; 
				new Pastel(randomx.nextInt((maxBorder - minBorder)+1)+minBorder, y);
				count=count+level;

				if((count % 50) == 0 && (count % 100) !=0) {
					for(int i=0;i<num.length;i++) {
						num[i] = num[i]+count;
					}					
				}
				
				if(count >= 50) {
					for(int i =0;i<num.length;i++) {
						if(num[i] != count) {
							control[0] =0;
						}
					}
					
					for(int i =0;i<num.length;i++) {
						if(num[i] == count) {
							control[0] =1;
							Pastel.velocity = 3;
						}
					}
				}else if(count < 50){
					control[0] =0;
				}
				
				System.out.println(Arrays.toString(num));			
								
			}
			break;
		case "Lose":
				
				this.framesContinue++;
				
				if(this.framesContinue  == 45) { 
					this.framesContinue =0;
					
					if(this.showMessage) {
						
						this.showMessage =false;
						
					}else {						
						this.showMessage = true;
					}
				}
				break;
			case "Menu":
				menu.tick();
			break;
			case "Credits" :
				credits.tick();
			break;
		
		default:
			System.out.println("ERROR");
		}
	}	
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = Wall.getGraphics();
		
		g = bs.getDrawGraphics();      
		switch(GameState) {
		case ("GameOn"):
		g.setColor(new Color(68,114,182));
		g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
		
		Graphics2D g2 = (Graphics2D) g;
		 if(control[0] == 1){
			g2.drawImage(map100.getSprite(1,2, WIDTH*SCALE+175, HEIGHT*SCALE+175), 0,0,WIDTH*SCALE, HEIGHT*SCALE, null);
		}else {
			g2.drawImage(spritesheet.getSprite(1,2, WIDTH*SCALE+175, HEIGHT*SCALE+175), 0,0,WIDTH*SCALE, HEIGHT*SCALE, null);
		}
		g2.drawImage(pastelBanner.getSprite(1, 1, 285, 85), 4, 342, 120, 35, null);
		g2.drawImage(pastelogo.getSprite(0, 0, 296, 66), 550, 165, 140, 25, null);
		mouth.render(g);
		pastel.render(g);
		floor.render(g);
		
		g2.setFont(Fonts.font1);
		g2.setColor(Color.white);
		g2.drawString("Points", 228,390);
		
		g2.setFont(Fonts.font2);
		g2.setColor(Color.white);
		g2.drawString(""+count, 495,390);
		
		g2.dispose();
		break;
		case("Lose"):
			g.setColor(Color.white);
			g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
			
			Graphics2D g3 = (Graphics2D) g;
			g3.drawImage(tryAgain.getSprite(1, 1, 750,220), (WIDTH*SCALE)/2-145, -40+(HEIGHT/2), 250, 100,null);
			g3.drawImage(sadMouth.getSprite(1, 1, 170, 140), (WIDTH*SCALE)/2-60, 80+(HEIGHT/2), 100,80,null);
			
			g3.setFont(Fonts.font3);
			g3.setColor(Color.BLACK);
			g3.drawString("You left our Asian friend", (WIDTH*SCALE)/2-160,200+(HEIGHT/2));
			g3.drawString("without eating his pastry", (WIDTH*SCALE)/2-160,228+(HEIGHT/2));
			
			g.setFont(new Font("Arial",Font.BOLD,12));
			g.setColor(Color.red);
			String infoPoints;
			if(count == 0) {
				infoPoints = "You didn't eat any pastry";
			}else if(count == 1) {
				infoPoints = "You just ate a pastry";
			}else {
				infoPoints = "You ate "+count+" pasties";
			}
			
			g.drawString(infoPoints.toUpperCase(),(WIDTH*SCALE)/2-160,248+(HEIGHT/2) );
			
			if(showMessage) {
				g3.setFont(Fonts.font4);
				g3.setColor(Color.BLACK);
				g3.drawString("Press enter to continue...", (WIDTH*SCALE/2)+160,332+(HEIGHT/2));
			}
			break;
		case ("Menu"):
			Graphics2D g4 = (Graphics2D) g;
			menu.render(g4);
			break;
		case ("Credits"):
			Graphics2D g1 = (Graphics2D) g;
			credits.render(g1);
		break;
		
		default:
			System.out.println("ERROR");
			break;
		}
		
		bs.show();
	}
	
	public void run() {
		requestFocus();
		long last = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = (1*Math.pow(10, 9))/amountOfTicks;
		double timer = System.currentTimeMillis();
		
		int frames =0;
		double delta = 0;
		
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now-last)/ns;
			last = now;
			
			if(delta>=1) {
				frames++;
				tick();
				render();
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: "+frames);
				frames = 0;
				timer += 1000;
			}
		}
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(GameState == "GameOn") {
				mouth.left = true;
			}else if(GameState == "Credits") {
				GameState = "Menu";
			}
			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			mouth.right = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			if(GameState == "Menu") {
				menu.up = true;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(GameState == "Menu") {
				menu.down = true;
			}
				
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(GameState == "Menu") {
				menu.enter = true;
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			GameState = "Menu";
			menu.pause = true;
		}
	
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(GameState == "Lose") {
				GameState = "GameOn";
				new Pastel(randomx.nextInt((maxBorder - minBorder)+1)+minBorder, y);
				mouth = new Mouth(mouthOrigin,mouthY);
				Pastel.velocity = 1.0;
				for(int i = 0; i<num.length;i++) {
					num[i] =i;
				}
				control[0] =0;
				level=1;
				count=0;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			mouth.left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			mouth.right = false;
		}
		
	
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
