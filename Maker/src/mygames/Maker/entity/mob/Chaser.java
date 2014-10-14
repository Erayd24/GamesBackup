package mygames.Maker.entity.mob;

import java.util.List;

import mygames.Maker.graphics.AnimatedSprite;
import mygames.Maker.graphics.Screen;
import mygames.Maker.graphics.SpriteSheet;

public class Chaser extends Mob{

	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
	
	private AnimatedSprite animSprite = down;
	private int xa, ya = 0;
	
	public Chaser(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
	}
	
	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob(x - 16, y - 16, this);
		sprite = sprite.player_forward;
	}

	private void move() {
		xa = 0;
		ya = 0;
		List<Player> players = level.getPlayers(this, 60);
		if(players.size() > 0) {
		Player player = players.get(0);
			if(x < player.getX()) xa++;
			if(x > player.getX()) xa--;
			if(y < player.getY()) ya++;
			if(y > player.getY()) ya--;
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
