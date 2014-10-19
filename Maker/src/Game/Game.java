package Game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import javax.swing.JFrame;

import Game.data.Save;
import Game.entity.mob.Player;
import Game.graphics.Screen;
import Game.graphics.menus.InGameMenu;
import Game.graphics.menus.TitleMenu;
import Game.input.Keyboard;
import Game.input.Mouse;
import Game.level.Level;
import Game.level.TileCoordinate;

public class Game extends Canvas implements Runnable, Serializable {
	private static final long serialVersionUID = 1099950990443161867L;
	
	private static int width = 400;
	private static int height = (width / 16 * 9) - 1;
	private static int scale = 3;
	private static String title = "The Judgement";
	
	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private Mouse mouse;
	private Level level;
	private Player player;
	private boolean running = false;
	private Screen screen;
	private InGameMenu inGameMenu;
	private TitleMenu titleMenu;
	private static STATE State;
	private static Save save;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
			
	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		
		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		mouse = new Mouse();
		level = Level.spawn;
		TileCoordinate playerSpawn = new TileCoordinate(9, 12); //Player spawn location
		player = new Player(playerSpawn.x(), playerSpawn.y(), key); 
		level.add(player);
		State = STATE.TITLE;
		
		inGameMenu = new InGameMenu(key, mouse);
		titleMenu = new TitleMenu(key, mouse);
		addKeyListener(key);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);	
		save = new Save();
	}
	
	//Constructor for Loading old files
	public Game(String file) {
		//Load a serialized file
		FileInputStream file_in = null;
		ObjectInputStream reader = null;
		Object obj = null;
		
		try {
			file_in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
		//Read objects
		try {
			reader = new ObjectInputStream (file_in);
			System.out.println("Load successful.");
		} catch (Exception e) {
			System.err.println("Load file failed...");
		}
	
		try {
			obj = reader.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		if(obj instanceof Player) {
			Player player = (Player) obj;
			this.player = player;
		}
		if(obj instanceof Level) {
			Level level = (Level) obj;
			this.level = level;
		}
		//End Load
		
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		
		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		mouse = new Mouse();
		level.add(player);
		State = STATE.GAME;
		
		inGameMenu = new InGameMenu(key, mouse);
		titleMenu = new TitleMenu(key, mouse);
		addKeyListener(key);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);	
		save = new Save();
	}
	
	public static void saveState(String file) {
		save.saveState(file);
	}
	
	public static int getWindowWidth() {
		return width * scale;
	}
	
	public static int getWindowHeight() {
		return height * scale;
	}
	
	public static void changeState(STATE state) {
		State = state;
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	public synchronized void stop() {
		running = false;
		try{
		thread.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//create a timer  and an fps counter as well as rendering the game as much as possible
	//and updating as close to 60 times a second as possible
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		
		while(running) { 
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			//Using this next loop assures that update is only completed once every specific
			//amount of time, in this case, 60 times a second
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title + " | " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
	}
	
	public void update() {
		if(State == STATE.TITLE){
			titleMenu.update();
		}
		if(State == STATE.GAME) {
			level.update();
		}
		if(State == STATE.INGAMEMENU) {
			inGameMenu.update();
		}
		key.update();
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		if(State == STATE.GAME) {
			int xScroll = player.getX() - screen.width / 2;
			int yScroll = player.getY() - screen.height / 2;
			level.render(xScroll, yScroll, screen);
		} else if(State == STATE.INGAMEMENU) {
			inGameMenu.render(screen);
		} else if(State == STATE.TITLE) {
			titleMenu.render(screen);
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		//obtain the pixels which will be changed
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		} 
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle("Thunder");
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
	}
}