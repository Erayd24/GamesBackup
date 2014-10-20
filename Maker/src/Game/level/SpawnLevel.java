package Game.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.entity.mob.Chaser;
import Game.entity.mob.RandomPath;
import Game.entity.mob.Shooter;

public class SpawnLevel extends Level{
	private static final long serialVersionUID = -3424311632225505497L;

	//Constructor
	public SpawnLevel(String path) {
		super(path);
	}
		
	//Load the new level - Designed using colors in a sheet designated towards tiles
	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception! Could not load level file!");
		}
		//Add Mobs to the level
		add(new Chaser(6, 4));
		//add(new Follower(8, 8));
		add(new Shooter(9, 8));
		add(new RandomPath( 4, 3));
	}
	
	//Generate the new level
	protected void generateLevel() { 
		
	}
}