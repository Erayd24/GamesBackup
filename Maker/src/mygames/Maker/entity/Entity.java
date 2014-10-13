package mygames.Maker.entity;

import java.util.Random;

import mygames.Maker.graphics.Screen;
import mygames.Maker.level.Level;

public class Entity {

	//Things that move or "exist" or are interactable
		public int x, y;
		private boolean removed = false;
		protected Level level;
		protected final Random random = new Random();
		
		public void update() {
			
		}
		
		public void render(Screen screen) {
			
		}
		
		public void remove() {
			removed = true;
		}
		
		public boolean isRemoved() {
			return removed;
		}
		
		public void init(Level level) {
			this.level = level;
		}
}
