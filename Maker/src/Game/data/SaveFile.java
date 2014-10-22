package Game.data;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;

public class SaveFile {

	private PrintWriter writer;
	private File newfile; 
	
	//Constructor - Make a new file
	public SaveFile() {
		
	}
	
	public void newFile(String file) {
		newfile = new File("/gamedata/saves/" + file);
		newfile.getParentFile().mkdirs();
		
		try {
			writer = new PrintWriter(newfile, "UTF-8");
			System.out.println("successfully created a new file.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Unable to make new file...");
		}
		writer.close();
	}
}
