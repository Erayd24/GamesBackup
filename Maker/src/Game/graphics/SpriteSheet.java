package Game.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path; 
	public final int WIDTH;
	public final int HEIGHT;
	public final int SIZE;
	public int[] pixels;
	
	public static SpriteSheet environment_tiles = new SpriteSheet("/textures/environments/environments1.png", 256);
	public static SpriteSheet weapons_armors = new SpriteSheet("/textures/weaponsArmors/weapons1.png", 256);
	public static SpriteSheet characters = new SpriteSheet("/textures/characters/mainCharacter.png", 256);
	
	//Player Sprite sheets for animation
	public static SpriteSheet player_up = new SpriteSheet(characters, 0, 5, 1, 3, 32);
	public static SpriteSheet player_right = new SpriteSheet(characters, 1, 5, 1, 3, 32);
	public static SpriteSheet player_down = new SpriteSheet(characters, 2, 5, 1, 3, 32);
	public static SpriteSheet player_left = new SpriteSheet(characters, 3, 5, 1, 3, 32);
	
	//RandomPath NPC sprite sheets
	public static SpriteSheet RandomPath_up = new SpriteSheet(characters, 0, 5, 1, 3, 32);
	public static SpriteSheet RandomPath_right = new SpriteSheet(characters, 1, 5, 1, 3, 32);
	public static SpriteSheet RandomPath_down = new SpriteSheet(characters, 2, 5, 1, 3, 32);
	public static SpriteSheet RandomPath_left = new SpriteSheet(characters, 3, 5, 1, 3, 32);
	
	//Follower NPC/enemy sprite sheets
	public static SpriteSheet Follower_up = new SpriteSheet(characters, 0, 5, 1, 3, 32);
	public static SpriteSheet Follower_right = new SpriteSheet(characters, 1, 5, 1, 3, 32);
	public static SpriteSheet Follower_down = new SpriteSheet(characters, 2, 5, 1, 3, 32);
	public static SpriteSheet Follower_left = new SpriteSheet(characters, 3, 5, 1, 3, 32);
	
	//Chaser NPC/enemy sprite sheets
	public static SpriteSheet Chaser_up = new SpriteSheet(characters, 0, 5, 1, 3, 32);
	public static SpriteSheet Chaser_right = new SpriteSheet(characters, 1, 5, 1, 3, 32);
	public static SpriteSheet Chaser_down = new SpriteSheet(characters, 2, 5, 1, 3, 32);
	public static SpriteSheet Chaser_left = new SpriteSheet(characters, 3, 5, 1, 3, 32);
	
	//Shooter NPC/enemy sprite sheets
	public static SpriteSheet Shooter_up = new SpriteSheet(characters, 0, 5, 1, 3, 32);
	public static SpriteSheet Shooter_right = new SpriteSheet(characters, 1, 5, 1, 3, 32);
	public static SpriteSheet Shooter_down = new SpriteSheet(characters, 2, 5, 1, 3, 32);
	public static SpriteSheet Shooter_left = new SpriteSheet(characters, 3, 5, 1, 3, 32);
	
	private Sprite[] sprites;
	
	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		if(width == height) SIZE = width;
		else SIZE = -1;
		WIDTH = w;
		HEIGHT = h;
		pixels = new int[w * h];
		for(int y0 = 0; y0 < h; y0++) {
			int yp = yy + y0;
			for(int x0 = 0; x0 < w; x0++) {
				int xp = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.WIDTH];
			}
		}
		
		int frame = 0;
		sprites = new Sprite[width * height];
		for(int ya = 0; ya < height; ya++) {
			for(int xa = 0; xa < width; xa++) {
				int[] spritePixels = new int[spriteSize * spriteSize];
				for(int y0 = 0; y0 < spriteSize; y0++) {
					for(int x0 = 0; x0 < spriteSize; x0++) {
						spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize) + (y0 + ya * spriteSize) * WIDTH];
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
				sprites[frame++] = sprite;
			}
		}
	}
	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		WIDTH = size;
		HEIGHT = size;
		pixels = new int[SIZE*SIZE];
		load();
	} //Use this to load a new sprite sheet in another class
	
	public Sprite[] getSprites() {
		return sprites;
	}
	
	private void load() {
		try{	
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch  (IOException e) {
			e.printStackTrace();
		  } //end catch/try
	} //end load
}
