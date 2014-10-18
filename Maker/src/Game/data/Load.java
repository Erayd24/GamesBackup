package Game.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import Game.Game;

public class Load {

	private FileInputStream file_in;
	private ObjectInputStream reader;
	private Object obj;
	
	public Load() {
		
	}
	
	public void loadState(String file) {
		//Read file
		try {
			file_in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
		//Read objects
		try {
			reader = new ObjectInputStream (file_in);
			System.out.println("Load successful.");
		} catch (Exception e) {
			System.err.println("Load file failed...");
		}
	
		try {
			obj = reader.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		if (obj instanceof Game) {
			Game game = (Game) obj;
			System.out.println("Game Object found.");
			game.start();
		}
		
	}
}