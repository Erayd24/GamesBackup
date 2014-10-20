package Game.data;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import Game.Game;
import Game.entity.mob.Player;
import Game.level.Level;

public class Save implements Serializable {
	private static final long serialVersionUID = 5719848248859586331L;
	
	private transient FileOutputStream file_out;
	private transient ObjectOutputStream obj_out;
	
	//Constructor - Create a save object to save the state
	public Save() {
		
	}
	
	public void saveState(String file) {
		//Objects set for saving
		Player player = Game.getPlayer(0);
		Level level = Game.getLevel();
		
		//Open existing file and write to it
		try {
			file_out = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			obj_out = new ObjectOutputStream(file_out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try { //Player
			obj_out.writeObject(player);
			System.out.println("Save Successful");
		} catch (Exception e) {
			System.err.println("Save Failed.");
			e.printStackTrace();
		}
		
		try { //Level
			obj_out.writeObject(level);
			System.out.println("Save Successful");
		} catch (Exception e) {
			System.err.println("Save Failed.");
			e.printStackTrace();
		}
		
		try {
			obj_out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}