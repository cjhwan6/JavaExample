package filemodule;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Truncate file. 
 * This class makes file content into null no matter how big it is.  
 * @author Jo Ji Hwan
 *
 */
public class FileTruncate {
	private static FileTruncate c_Instance = new FileTruncate();
	
	public static FileTruncate getInstance()
	{
		return c_Instance;
	}
	
	/**
	 * Write contents of given file into NULL value
	 * @param p_file target file to be truncated.
	 * @throws FileNotFoundException throws when given file not found
	 * @throws IOException throws when there is error while IO processing
	 */
	public void truncate(File p_file) throws FileNotFoundException, IOException {
		if(!p_file.isFile()) throw new FileNotFoundException("Could not find file : " + p_file.getPath());
		FileWriter l_write = new FileWriter(p_file);
		// Write null array into parameter file.		
		l_write.flush();
		l_write.write("\0");
		l_write.close();
	}
	
}
