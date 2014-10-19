package Game.graphics.menus;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.Game;
import Game.graphics.Font;
import Game.graphics.Screen;
import Game.graphics.SpriteSheet;
import Game.input.Keyboard;
import Game.input.Mouse;

public class TitleMenu extends Menu {

	private Font font;
	private Keyboard input;
	private Game game;
	
	private int wait = 0;
	private int width;
	private int height;
	private int location = 0;
	private int menuHeight = 134;
	
	private static OPTION option;
	public int[] pixels;
	
	public TitleMenu(Keyboard input, Mouse mouse) {
		this.input = input;
		font = new Font();
		option = OPTION.NONE;
	}
	
	public void update() {
		if(input.action) game = new Game("axoh17"); //Change this to file name variable for when creating names is an option
	}

	
	public void render(Screen screen) {
		screen.renderMenu(0, 0, this);
		setText(screen);
		showText(screen);
	}

	
	public void setText(Screen screen) {
		font.render(11, 135, -4, 0xffffffff, "Load Game", screen);
	}

	
	public void showText(Screen screen) {
		
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void load(String path) {
		try{	
			System.out.print("Trying to load: " + path + " ... ");
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			System.out.println(" succeeded!");
			this.width = image.getWidth();
			this.height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch  (IOException e) {
			e.printStackTrace();
		  } catch (Exception e) {
			  System.err.println(" failed!");
		  }
	}
	

}
