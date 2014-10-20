package Game.data;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class File {

	private PrintWriter writer;
	
	//Constructor - Make a new file
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
}
