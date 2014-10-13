package mygames.Maker.level;

import java.util.Random;

public class RandomLevel extends Level {

	//A class which can generate a randomly generated map
	private static final Random random = new Random();
	
	public RandomLevel(int width, int height) {
		super(width, height);
	}
	
	protected void generateLevel() {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tilesInt[x + y * width] = random.nextInt(4); //random number between zero and three
			}
		}
	}

}
