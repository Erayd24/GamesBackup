package Game.entity.mob;

import java.util.List;

import Game.graphics.AnimatedSprite;
import Game.graphics.Screen;
import Game.graphics.SpriteSheet;

public class Chaser extends Mob{

	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.Chaser_up, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.Chaser_right, 32, 32, 3);
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.Chaser_down, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.Chaser_left, 32, 32, 3);
	
	private AnimatedSprite animSprite = down;
	private int xa, ya = 0;
	private double speed = 1;
	
	public Chaser(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
	}
	
	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob(x - 16, y - 16, this);
	}

	private void move() {
		xa = 0;
		ya = 0;
		List<Player> players = level.getPlayers(this, 60);
		if(players.size() > 0) {
		Player player = players.get(0);
			if(x < player.getX()) xa += speed;
			if(x > player.getX()) xa -= speed;
			if(y < player.getY()) ya += speed;
			if(y > player.getY()) ya -= speed;
		}
		
		if(xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
	}
	
	public void update() {
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
