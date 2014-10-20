package Game.level;

import java.util.Random;

public class RandomLevel extends Level {
	private static final long serialVersionUID = -3015225239040157881L;
	
	//A class which can generate a randomly generated map
	private static final Random random = new Random();
	
	//Constructor
	public RandomLevel(int width, int height) {
		super(width, height);
	}
	
	//Generate the newrandom level
	protected void generateLevel() {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tilesInt[x + y * width] = random.nextInt(4);
			}
		}
	}
}