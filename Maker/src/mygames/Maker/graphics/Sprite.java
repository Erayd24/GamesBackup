package mygames.Maker.graphics;

public class Sprite {

	//Use this class to add new sprites to the game/level
	public final int SIZE;
	private int x,y;
	private int width, height;
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
	public static Sprite player_forward = new Sprite(32, 0, 5, SpriteSheet.characters); //standing

	//Particles
	public static Sprite particle_normal = new Sprite(3, 0xaaaaaa);
	
	protected Sprite(SpriteSheet sheet, int width, int height) { //only for animated sprite class
		SIZE = (width == height) ? width : -1; //if with is equal to the height then SIZE equals width, otherwise, SIZE equals -1
		this.width = width;
		this.height = height;
		this.sheet = sheet;
	}
	
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
	
	public Sprite(int width, int height, int color) {
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		setColor(color);
	}
	
	public Sprite(int size, int color) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}
	
	public Sprite(int[] pixels, int width, int height) {
		SIZE = (width == height) ? width : -1;
		this.height = height;
		this.width = width;
		this.pixels = pixels; 
	}
	
	private void setColor(int color) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = color;
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
}
