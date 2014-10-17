package Game.data;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Save {

	private FileOutputStream file_out;
	private ObjectOutputStream obj_out;
	
	public Save() {
		
	}
	
	public void saveState(String file) {
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
		
		try {
			obj_out.writeObject(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			obj_out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
