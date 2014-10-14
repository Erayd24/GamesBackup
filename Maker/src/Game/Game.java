package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import Game.entity.mob.Player;
import Game.graphics.Screen;
import Game.input.Keyboard;
import Game.input.Mouse;
import Game.level.Level;
import Game.level.TileCoordinate;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private static int width = 300;
	private static int height = width / 16 * 9;
	private static int scale = 3;
	private static String title = "Thunder";
	
	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private Level level;
	private Player player;
	private boolean running = false;
	private Screen screen;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
		
	//set the screen size for your game
	public Game() {
		Dimension size = new Dimension(width*scale, height*scale);
		setPreferredSize(size);
		
		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		level = level.spawn;
		TileCoordinate playerSpawn = new TileCoordinate(9, 12); //Player spawn location
		player = new Player(playerSpawn.x(), playerSpawn.y(), key); //Add a player character and adjust spawn location
		level.add(player);
		
		addKeyListener(key);
		
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	} //end game
	
	public static int getWindowWidth() {
		return width * scale;
	}
	
	public static int getWindowHeight() {
		return height * scale;
	}
	
	//Begin a new thread and start the game
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	} //end start
	
	//Stop the program when this is called
	public synchronized void stop() {
		running = false;
		try{
		thread.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		} //end catch
	} //end stop
	
	//create a timer  and an fps counter as well as rendering the game as much as possible
	//and updating as close to 60 times a second as possible
	public void run() {
		long lastTime = System.nanoTime(); //Initial nanoseconds
		long timer = System.currentTimeMillis(); //Initial milliseconds
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		
		//While running is equal to true, this while loop keeps running, which updates and
		//renders the game constantly until told to stop with stop()
		while(running) { 
			long now = System.nanoTime(); //What the time is now
			delta += (now - lastTime) / ns; //How long has passed since last checked
			lastTime = now; //Update lastTime so we count time correctly
			//Using this next loop assures that update is only completed once every specific
			//amount of time, in this case, 60 times a second
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			} //end delta while
			render();
			frames++;
			
			//Subtracting the initial timer makes sure that a specific amount of time has 
			//passed since starting the game
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title + " | " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			} //end fps and update counter if statement
		} //end running while
	} //end run
	
	//Update the game 60 times a second by for example, moving the player forward
	public void update() {
		key.update();
		level.update();
	} //end update
	
	//set a buffer strategy to render the game as much as possible, graphics are drawn here
	public void render() {
		//creating a buffer strategy with 3 layers
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		} //end bufferstrategy if
		
		//clean the screen each time something is rendered
		screen.clear();
		double xScroll = player.getX() - screen.width / 2;
		double yScroll = player.getY() - screen.height / 2;
		level.render((int)xScroll, (int)yScroll, screen);
		
		//Sprite sprite = new Sprite(2, 2, 0xffff00ff); //Allows for on screen graphics that move with player, or particles
		//screen.renderSprite(0, 0, sprite, false); //This can also be used with a for loop, math.random, with an x and y random to make rain
		
		
		//obtain the pixels which will be changed
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		} //end pixel for
		
		//get the method which draws on the screen
		Graphics g = bs.getDrawGraphics();
		{
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());	
			g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
			//g.fillRect(Mouse.getX() - 32, Mouse.getY() - 32, 64, 64);
		} //end graphics code block
		
		g.dispose();
		//show what was made to the user
		bs.show();
	} //end render
	
	//set the window to a size and let it appear on screen, start the game
	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle("Maker");
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
		
	} //end main
} //end Game class
