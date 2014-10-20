package Game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

public class Keyboard implements KeyListener, Serializable{
	private static final long serialVersionUID = 3549887181991201640L;
	
	private boolean[] keys = new boolean[200];
	public boolean up, down, left, right, inventory, action, back;
	
	public void update() {
		inventory = keys[KeyEvent.VK_I];
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		action = keys[KeyEvent.VK_ENTER] || keys[KeyEvent.VK_E];
		back = keys[KeyEvent.VK_BACK_SPACE];
	}
	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {
		
	}

}
