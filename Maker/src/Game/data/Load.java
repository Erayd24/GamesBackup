package Game.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import Game.Game;
import Game.graphics.Screen;
import Game.level.Level;
import Game.util.Vector2i;

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
		} catch (IOException e) {
			e.printStackTrace();
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
		}
		/*if (obj instanceof Level) {
			Level level = (Level) obj;
		}
		if (obj instanceof Screen) {
			Screen screen = (Screen) obj;
		}*/
	}
}
