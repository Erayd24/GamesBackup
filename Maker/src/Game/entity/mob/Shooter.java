package Game.entity.mob;

import Game.graphics.AnimatedSprite;
import Game.graphics.Screen;
import Game.graphics.SpriteSheet;

public class Shooter extends Mob {
	
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.Shooter_up, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.Shooter_right, 32, 32, 3);
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.Shooter_down, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.Shooter_left, 32, 32, 3);
	private AnimatedSprite animSprite = down;
	private int speed = 1;
	private int time = 0;
	private int xa, ya = 0;

	
	public Shooter(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob(x,  y,  this);
	}

	public void move() { //Using random path movement
		if(time % (random.nextInt(60) + 30) == 0) { //Set an AI to walk in a set interval
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
	
	public void update() {
		Player p = level.getClientPlayer();
		double angle = Math.atan2((p.getY() - y), (p.getX() - x)); //direction of fire
		if(time % 60 == 0) shoot(x + 16, y + 16, angle);
		
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
