package Game.data;

import java.io.Serializable;

import Game.level.Level;

public class Data implements Serializable {
	private static final long serialVersionUID = -4581667590087218630L;
	
	private int playerX;
	private int playerY;
	
	public void update(Level level) {
		this.playerX = level.getClientPlayer().getX();
		this.playerY = level.getClientPlayer().getY();
	}

	public int getPlayerX() {
		return playerX;
	}

	public int getPlayerY() {
		return playerY;
	}
}
