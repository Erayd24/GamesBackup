package mygames.Maker.entity.mob;

import mygames.Maker.graphics.Screen;
import mygames.Maker.graphics.Sprite;

public class Dummy extends Mob{

	public Dummy(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.player_forward;
	}
	
	public void render(Screen screen) {
		screen.renderMob(x, y, sprite);
	}

	public void update() {
		
	}

}
