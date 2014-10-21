package Game.graphics.menus;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.Game;
import Game.graphics.AnimatedSprite;
import Game.graphics.Font;
import Game.graphics.Screen;
import Game.graphics.Sprite;
import Game.graphics.SpriteSheet;
import Game.input.Keyboard;
import Game.input.Mouse;

public class TitleMenu extends Menu {

	private Font font;
	private Keyboard input;
	private Sprite sprite;
	
	private int wait;
	private int width;
	private int height;
	private int location;
	private int menuHeight = 134;
	private int x = 125, y = 145;
	
	private static OPTION option;
	public int[] pixels;
	
	private AnimatedSprite menuArrow = new AnimatedSprite(SpriteSheet.menuArrow, 32, 32, 4);
	
	public TitleMenu(Keyboard input, Mouse mouse) {
		this.input = input;
		font = new Font();
		option = OPTION.NONE;
		load("/menus/titlemenu1.png");
		menuArrow.setFrameRate(12);
	}
	
	public void update() {
		wait++;
		if(input.action && wait > 10 && location == 0) {
			Game.load("axoh17"); //Change this to file name variable for when creating names is an option
			wait = 0;
		}
		if(input.action && location == 1) {
			load("/menus/titlemenu2.png");
			Game.newGame();
		}
		if(input.down) location--;
		if(input.up) location++;
		menuArrow.update();
	}

	
	public void render(Screen screen) {
		screen.renderMenu(0, 0, this);
		setText(screen);
		showText(screen);
		sprite = menuArrow.getSprite();
		screen.renderSprite(x, y, sprite, false);
	}

	
	public void setText(Screen screen) {
		font.render(130, 135, -4, 0, "Load Game", screen);
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
