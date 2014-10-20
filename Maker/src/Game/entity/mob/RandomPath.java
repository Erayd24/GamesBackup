package Game.entity.mob;

import Game.graphics.AnimatedSprite;
import Game.graphics.Screen;
import Game.graphics.SpriteSheet;

public class RandomPath extends Mob{
	private static final long serialVersionUID = -8518314043225004523L;
	
	private boolean walking = false;
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.RandomPath_up, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.RandomPath_right, 32, 32, 3);
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.RandomPath_down, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.RandomPath_left, 32, 32, 3);

	private int time = 0;
	private int xa, ya = 0;
	private int speed = 1;
	private AnimatedSprite animSprite = down;
	
	//Constructor - In tile precision, spawn a randomPath NPC/Mob at x, y
	public RandomPath(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
	}
	
	//Render the mob to the screen
	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob(x, y, this);
	}

	//Move the random path mob - "RandomPath AI"
	public void move() {
		if(time % (random.nextInt(60) + 30) == 0) {
			xa = random.nextInt(3) - speed;
			ya = random.nextInt(3) - speed;
			if(random.nextInt(4) == 0) { //stopping
				xa = 0;
				ya = 0;
			}
		}
		 
		if(xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
	}
	
	//Update the mob
	public void update() {
		time++;
		move();
		
		if(walking) animSprite.update();
		else animSprite.setFrame(0);
		if(ya < 0) {
			animSprite = up;
			dir = Direction.UP;
		} else if(ya > 0) {
			animSprite = down;
			dir = Direction.DOWN;
		}
		if(xa < 0) {
			animSprite = left;
			dir = Direction.LEFT;
		} else if(xa > 0) {
			animSprite = right;
			dir = Direction.RIGHT;
		}
	}
}