package Game.data;

import java.io.Serializable;

import Game.level.Level;

public class Data implements Serializable {
	private static final long serialVersionUID = -4581667590087218630L;
	
	private int playerX = 16 * 9; //Initial player spawn location to the start of the game
	private int playerY = 16 * 12;
	private String fileName;
	
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
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileName() {
		return fileName;
	}
}
