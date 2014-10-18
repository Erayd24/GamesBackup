package Game.data;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class File {

	private PrintWriter writer;
	
	//Variables to be saved in a file to continue the game later
	private int playerX;
	private int playerY;
	//End variables to be saved: Remember to add them to the saveFile method below
	
	public File() {
		
	}
	
	public void newFile(String file) {
		try {
			writer = new PrintWriter(file + ".txt", "UTF-8");
			System.out.println("successfully created a new file.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Unable to make new file...");
		}
		writer.close();
	}
	
	public void loadFile(String file) {
		
	}

	public void saveFile(String file) {
		try {
			writer = new PrintWriter(file + ".txt", "UTF-8");
			writer.println(playerX);
			writer.println(playerY);
			System.out.println("successfully created a new file.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Unable to make new file...");
		}
		writer.close();
	}

	public void update(int x, int y) {
		playerX = x;
		playerY = y;
	}

}
