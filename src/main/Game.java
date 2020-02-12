package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import entities.Mouth;
import graphics.Spritesheet;

@SuppressWarnings("serial")
public class Game extends Canvas implements Runnable, KeyListener{

	private JFrame frame;
	public static int WIDTH = 240;
	public static int HEIGHT = 160;
	private final int SCALE=3;
	private Thread thread;
	public BufferedImage Wall;
	private boolean isRunning;
	public Spritesheet spritesheet;
	public Mouth mouth;
	public Sound sound;
	public Game() {
		initFrames();
		Sound.musicBackground.loop();
		Wall = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		spritesheet =  new Spritesheet("/BackGroundPastel.jpeg");
		mouth = new Mouth(0,280);
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
		mouth.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = Wall.getGraphics();
		
		g = bs.getDrawGraphics();
		
		g.setColor(new Color(68,114,182));
		g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
		
		
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(spritesheet.getSprite(1,2, WIDTH*SCALE+175, HEIGHT*SCALE+175), 0,0,WIDTH*SCALE, HEIGHT*SCALE, null);
		
		mouth.render(g);
		
		g2.dispose();
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
			mouth.left = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			mouth.right = true;
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
