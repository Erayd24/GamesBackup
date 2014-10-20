package Game.entity;

import java.io.Serializable;
import java.util.Random;

import Game.graphics.Screen;
import Game.graphics.Sprite;
import Game.level.Level;

public class Entity implements Serializable{
	private static final long serialVersionUID = 7053942917105910952L;
	
		//Things that move or "exist"
		protected int x, y; //Location
		protected Sprite sprite;
		private boolean removed = false;
		protected Level level;
		protected final Random random = new Random();
		//No constructor, this is specifically for use in sub-classes
		
		//Method for updating any movements of an entity
		public void update() {
			
		}
		
		//Render the entities on the screen
		public void render(Screen screen) {
			if(sprite != null) screen.renderSprite(x,  y,  sprite,  true);
		}
		
		//Remove an entity
		public void remove() {
			removed = true;
		}
		
		//Get a position of an entity
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		//Get the sprite of an entity
		public Sprite getSprite() {
			return sprite;
		}
	
		//Tell the system that the entity is set to be removed
		public boolean isRemoved() {
			return removed;
		}
		
		//Inititalize an entity in the Level
		public void init(Level level) {
			this.level = level;
		}
}