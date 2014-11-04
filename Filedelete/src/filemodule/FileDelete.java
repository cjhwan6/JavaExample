package filemodule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileDelete {

	private static FileDelete c_Instance = new FileDelete();
	
	public static FileDelete getInstance()
	{
		return c_Instance;
	}
	
	public void delete(File p_file) throws FileNotFoundException, IOException {
		
		p_file.delete();		
	}
	
}
