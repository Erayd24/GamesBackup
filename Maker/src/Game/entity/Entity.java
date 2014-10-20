package Game.entity;

import java.io.Serializable;
import java.util.Random;

import Game.graphics.Screen;
import Game.graphics.Sprite;
import Game.level.Level;

public class Entity implements Serializable{
	private static final long serialVersionUID = 7053942917105910952L;
	
		//Things that move or "exist" or are interactable
		protected int x, y;
		protected Sprite sprite;
		private boolean removed = false;
		protected Level level;
		protected final Random random = new Random();
		
		public void update() {
			
		}
		
		public void render(Screen screen) {
			if(sprite != null) screen.renderSprite(x,  y,  sprite,  true);
		}
		
		public void remove() {
			removed = true;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		public Sprite getSprite() {
			return sprite;
		}
	
		public boolean isRemoved() {
			return removed;
		}
		
		public void init(Level level) {
			this.level = level;
		}

}
