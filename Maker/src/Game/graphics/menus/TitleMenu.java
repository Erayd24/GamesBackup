package Game.graphics.menus;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.Game;
import Game.data.SaveFile;
import Game.graphics.AnimatedSprite;
import Game.graphics.Font;
import Game.graphics.Screen;
import Game.graphics.Sprite;
import Game.graphics.SpriteSheet;
import Game.input.Keyboard;
import Game.input.Mouse;
import Game.input.Typing;

public class TitleMenu extends Menu {

	private Font font;
	private Keyboard input;
	private Sprite sprite;
	private static Typing typing;
	private SaveFile file;
	private File checkFile;
	private String fileName = "";
	
	private String[] files;
	
	private int wait;
	private int width;
	private int height;
	private int location;
	private int x = 118, y = 153;
	private int xp, yp;
	private boolean deleting = false;
	boolean getFileName = false;
	
	private static OPTION option;
	public int[] pixels;
	
	private AnimatedSprite menuArrow = new AnimatedSprite(SpriteSheet.menuArrow, 32, 32, 4);
	
	public TitleMenu(Keyboard input, Mouse mouse) {
		this.input = input;
		file = new SaveFile();
		typing = new Typing(input);
		font = new Font();
		option = OPTION.NONE;
		load("/menus/titlemenu1.png");
		menuArrow.setFrameRate(12);
		checkFile = new File("/gamedata/saves/");
		getFileNames();
	}
	
	public void getFileNames() {
		files = checkFile.list();
	}
	
	public void update() {
		wait++;
		//'Getting controls' controls
		if(input.c && wait > 5 && option == OPTION.NONE) {
			wait = 0;
			y += 80;
			load("/menus/controls1.png");
			option = OPTION.CONTROLS;
		}
		
		if(option == OPTION.CONTROLS) {
			if(input.back && wait > 5) {
				wait = 0;
				option = OPTION.NONE;
				y -= 80;
				load("/menus/titlemenu1.png");
			}
		}
		
		if(input.enter && wait > 5 && location == 0 && option == OPTION.NONE) { //Load game
			wait = 0;
			location = 0;
			x = 85;
			y = 75;
			load("/menus/titlemenu2.png");
			option = OPTION.LOADGAME;
		}
		
		if(input.enter && wait > 5 && location == 1 && option == OPTION.NONE) { //New Game
			wait = 0;
			location = 0;
			x = 85;
			y = 75;
			load("/menus/titlemenu2.png");
			option = OPTION.NEWGAME;
		}
		
		//For when a newgame or load game choice hasnt been made
		if(input.down && wait > 5 && location < 1 && option == OPTION.NONE) {
			wait = 0;
			x -= 10;
			y += 30;
			location++;
		}
		
		if(input.up && wait > 5 && location > 0 && option == OPTION.NONE) {
			wait = 0;
			x += 10;
			y -= 30;
			location--;
		}
		
		//Controls for after the first choice has been made
		if(option == OPTION.NEWGAME || option == OPTION.LOADGAME) {
			//Inputting a file name and starting a new Game
			if(option == OPTION.NEWGAME) { 
				if(input.enter && wait > 10 && fileName.length() == 0 && !getFileName && location + 1 > files.length) {
					x += 10;
					wait = 0;
					getFileName = true;
				}
			
				if(getFileName) {
					if(input.enter && fileName.length() > 0) {
						file.newFile(fileName);
						getFileName = false;
						Game.data.setFileName(fileName);
						Game.newGame(fileName);
					}
					if(input.escape){
						x -= 10;
						fileName = "";
						getFileName = false;
					}
					if(input.back && fileName.length() > 0 && wait > 3) {
						wait = 0;
						fileName = fileName.substring(0, fileName.length() - 1);
					}
					
					if(wait > 4) {
						wait = 0;
						if(fileName.length() < 10) { //set a ten character maximum filename
							if(typing.checkInput() != null) fileName += typing.checkInput();
						}
					}
				}
			}
			
			//Choose a game to load and load it from the array of possible files
			if(option == OPTION.LOADGAME) {
				if(input.enter && wait > 10) {
					wait = 0;
					if(files.length == 3) Game.load(files[location]); 
					if(files.length == 2 && location <= 1) Game.load(files[location]); 
					if(files.length == 1 && location == 0) Game.load(files[location]); 
				}
			}
			
			//NewGame and load menu movement
			if(!getFileName) { 
				if(input.back && wait > 5) {
					wait = 0;
					x = 118;
					y = 153;
					location = 0;
					option = OPTION.NONE;
					load("/menus/titlemenu1.png");
				}
				
				//Deleting existing files
				if(input.d && wait > 5){ 
					wait = 0;
					deleting = true;
				}
				
				//If your sure you want to delete the file
				if(deleting) {
					if(input.y && wait > 5) {
						wait = 0;
						System.out.println("inside");
						if(files.length == 3) {
							checkFile = new File("/gamedata/saves/" + files[location]);
							checkFile.delete();
						}
						if(files.length == 2 && location <= 1)  {
							checkFile = new File("/gamedata/saves/" + files[location]);
							checkFile.delete();
						}
						if(files.length == 1 && location == 0) {
							checkFile = new File("/gamedata/saves/" + files[location]);
							checkFile.delete();
						}
						checkFile = new File("/gamedata/saves/");
						deleting = false;
					}
				}
				
				//if your not sure your ready to delete the file
				if(input.n) {
					deleting = false;
				}
				
				if(input.down && wait > 6 && location < 2) {
					y += 41;
					location++;
					wait = 0;
				}
				if(input.up && wait > 6 && location > 0) {
					y -= 41;
					location--;
					wait = 0;
				}
			}
		} //end in option controls
		getFileNames();
		menuArrow.update();
	} //end update

	
	public void render(Screen screen) {
		screen.renderMenu(0, 0, this);
		showText(screen);
		if(option == OPTION.NEWGAME) font.render(x + 20, y + 5, -1, 0, fileName, screen); //For typing in new name
		if(option != OPTION.NONE && option != OPTION.CONTROLS) {
			xp = 100;
			yp = 82;
			for(int i = 0; i < files.length; i++) { //Render the saved files names
				font.render(xp, yp + i * 40, files[i], screen);
			}
		}
		sprite = menuArrow.getSprite();
		screen.renderSprite(x, y, sprite, false);
	}

	public void showText(Screen screen) {
		if(option == OPTION.NONE) font.render(140, 160, -4, 0, "Load Game", screen);
		if(option == OPTION.NONE) font.render(130, 190, -4, 0, "New Game", screen);
		if(option == OPTION.NONE) font.render(-10, 180, -2, 0xffd3eded, "Info\n 'c'", screen);
		if(option == OPTION.NONE) font.render(120, 50, -2, 0xffD8B400, "The\n  Judgement", screen);
		if(!deleting && option == OPTION.NEWGAME) font.render(145, 30, -4, 0, "New Game", screen);
		if(!deleting && option == OPTION.LOADGAME) font.render(135, 30, -4, 0, "Load Game", screen);
		if(option != OPTION.NONE && option != OPTION.CONTROLS) font.render(90, 195, -3, 0xff7F3900, "Delete File: 'd'", screen);
		if(deleting) font.render(94, 19, -3, 0xffbf1d1d, "Are you sure?:\n     'y/n'", screen);
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

	public void setText(Screen screen) {
	}
}