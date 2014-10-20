package Game.graphics;

import java.io.Serializable;

public class Sprite implements Serializable {
	private static final long serialVersionUID = 1433057551120829618L;
	
	public final int SIZE; //Of a sprite
	private int x,y;
	private int width, height; //Of a sprite
	public int[] pixels;
	protected SpriteSheet sheet;
	
	//Environment
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.environment_tiles);
	public static Sprite flower = new Sprite(16, 1, 0, SpriteSheet.environment_tiles);
	public static Sprite rock = new Sprite(16, 2, 0, SpriteSheet.environment_tiles);
	public static Sprite wallBrick = new Sprite(16, 0, 1, SpriteSheet.environment_tiles);
	public static Sprite voidSprite = new Sprite(16, 0);
	
	//Weapons
	public static Sprite arrow = new Sprite(16, 0, 0, SpriteSheet.weapons_armors);
	
	//Player
	public static Sprite player_forward = new Sprite(32, 0, 5, SpriteSheet.characters);

	//Particles
	public static Sprite particle_normal = new Sprite(3, 0xaaaaaa);
	
	//Constructor - split up sheets in to individual sheets - used in Animated Sprites
	protected Sprite(SpriteSheet sheet, int width, int height) {
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
	}
	
	//Constructor - Make a sprite from a coordinate in a sheet
	// x and y are based on the size of the sprite
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}
	
	//Constructor - Make a sprite out of nothing of a specific color and height/width
	public Sprite(int width, int height, int color) {
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		setColor(color);
	}
	
	//Constructor - Make a sprite out of nothing of a specific uniform size
	public Sprite(int size, int color) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}
	
	//Constructor TODO: what does this do?
	public Sprite(int[] pixels, int width, int height) {
		SIZE = (width == height) ? width : -1;
		this.height = height;
		this.width = width;
		this.pixels = new int[pixels.length];
		for(int i = 0; i < pixels.length; i++) {
			this.pixels[i] = pixels[i];
		} 
	}
	
	//Set the color for a new blank sprite
	private void setColor(int color) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = color;
		}
	}
	
	//Get width of the current sprite
	public int getWidth() {
		return width;
	}
	
	//Get height of the current sprite
	public int getHeight() {
		return height;
	}
	
	//Cut out a piece of a spritesheet for sprite constructors
	private void load() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.spriteWidth];
			}
		}
	}

	//Split all sprites in a sheet in to sprites automatically
	// Return them as an array
	public static Sprite[] split(SpriteSheet sheet) {
		int amount = (sheet.getWidth() * sheet.getHeight()) / (sheet.spriteWidth * sheet.spriteHeight);
		Sprite[] sprites = new Sprite[amount];
		int current = 0;
		int[] pixels = new int[sheet.spriteWidth * sheet.spriteHeight];
		for(int yp = 0; yp < sheet.getHeight() / sheet.spriteHeight; yp++) {
			for(int xp = 0; xp < sheet.getWidth() / sheet.spriteWidth; xp++) {
				
				for(int y = 0; y < sheet.spriteHeight; y++) {
					for(int x = 0; x < sheet.spriteWidth; x++) {
						int xo = x + xp * sheet.spriteWidth;
						int yo = y + yp * sheet.spriteHeight;
						pixels[x + y * sheet.spriteWidth] = sheet.getPixels()[xo + yo * sheet.getWidth()];
					}
				}
				sprites[current] = new Sprite(pixels, sheet.spriteWidth, sheet.spriteHeight);
				current++;
			}
		}
		return sprites;
	}
}
