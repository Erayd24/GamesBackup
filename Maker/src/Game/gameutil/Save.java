package Game.gameutil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Save {

	private FileOutputStream f_out;
	private ObjectOutputStream obj_out;
	
	public Save(String file) {
		try {
			f_out = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			obj_out = new ObjectOutputStream(f_out);
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
