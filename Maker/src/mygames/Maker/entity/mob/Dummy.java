package mygames.Maker.entity.mob;

import mygames.Maker.graphics.AnimatedSprite;
import mygames.Maker.graphics.Screen;
import mygames.Maker.graphics.Sprite;
import mygames.Maker.graphics.SpriteSheet;

public class Dummy extends Mob{
	
	private boolean walking = false;
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);

	private int time = 0;
	private int xa = 0;
	private int ya = 0;
	private AnimatedSprite animSprite = down;
	
	public Dummy(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.player_forward; //Default dummy sprite upon spawn
	}
	
	public void render(Screen screen) {
		screen.renderMob((int)x, (int)y, sprite);
	}

	public void update() {
		time++;
		if(time % (random.nextInt(60) + 30) == 0) { //Set an AI to walk in a set interval
			xa = random.nextInt(3) - 1;
			ya = random.nextInt(3) - 1;
			if(random.nextInt(4) == 0) { //stopping
				xa = 0;
				ya = 0;
			}
		}
		
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
		
		if(xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
		sprite = animSprite.getSprite();
	}

}
