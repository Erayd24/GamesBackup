package Game.entity.mob;

import java.util.List;

import Game.graphics.AnimatedSprite;
import Game.graphics.Screen;
import Game.graphics.SpriteSheet;
import Game.level.Node;
import Game.util.Vector2i;

public class Follower extends Mob{

	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.Follower_up, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.Follower_right, 32, 32, 3);
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.Follower_down, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.Follower_left, 32, 32, 3);
	
	private AnimatedSprite animSprite = down;
	private int xa, ya = 0;
	private double speed = 1;
	private List<Node> path = null;
	private int time = 0;
	
	public Follower(int x, int y) {
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
		int px = (int)level.getPlayerAt(0).getX();
		int py = (int)level.getPlayerAt(0).getY();
		Vector2i start = new Vector2i((int)getX() >> 4, (int)getY() >> 4);
		Vector2i destination = new Vector2i(px >> 4, py >> 4);
		if(time % 3 == 0) path = level.findPath(start, destination); //Run search alg. 20 times per second
		if(path != null) {
			if(path.size() > 0) {
				Vector2i vec = path.get(path.size() - 1).tile; //To get correct side of the list of nodes
				if(x < vec.getX() << 4) xa += speed;
				if(x > vec.getX() << 4) xa -= speed;
				if(y < vec.getY() << 4) ya += speed;
				if(y > vec.getY() << 4) ya -= speed;
			}
		} //an else here will set a seperate AI
		
		if(xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
	}
	
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
