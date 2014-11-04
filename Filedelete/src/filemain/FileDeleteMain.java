package filemain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import filemodule.FileDelete;
import filemodule.FileTruncate;

public class FileDeleteMain {

	public static void main( String[] args)
	{
		FileDeleteMain l_obj = new FileDeleteMain();
		try {
			// Check number of parameter and flag value
			if(args.length != 2 || "-h".equals(args[0]))
			{				
				l_obj.showUsage();			
			}
			else 
			{
				String strFlag = args[0];
				String strDirectory = args[1];				
				if(!"-t".equals(strFlag) && !"-d".equals(strFlag))
				{
					System.out.println("Your parameter is invalid.");
					l_obj.showUsage();
					return;
				}
				File fTarget = new File(strDirectory);			
				// If target is directory, loop all of child file and execute each one.  
				if(fTarget.isDirectory())
				{
					String[] strTarget = null;
					File[] fChild = fTarget.listFiles();
					execute(fChild, strFlag);
					
				}
				else if(fTarget.isFile())
				{
					execute(fTarget, strFlag);
				}
				else
				{
					System.out.println("Your file is invalid!");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Show usage.
	 * @throws Exception
	 */
	public void showUsage() throws Exception 
	{
//		FileChannel readChannel = null; 
		try
		{
			InputStream l_str = getClass().getResourceAsStream("/help/readMe.txt");
			byte[] l_byt = new byte[ l_str.available() ];
			l_str.read(l_byt);
			String strData = new String(l_byt);
			System.out.println(strData);
			l_str.close();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param p_File 
	 * @param p_Flag
	 * @throws IOException Occurs when there is error while IO Processing
	 * @throws FileNotFoundException Occurs when there is no file in given file
	 */
	public static void execute(File p_File, String p_Flag) throws FileNotFoundException, IOException
	{	
		if("-t".equals(p_Flag)){
			System.out.println("Truncate File : " + p_File.getPath());			
			FileTruncate.getInstance().truncate(p_File);
		}
		else if("-d".equals(p_Flag)){
			System.out.println("Erasing File : " + p_File.getPath());
			FileTruncate.getInstance().truncate(p_File);
			FileDelete.getInstance().delete(p_File);
		}
	}

	/**
	 * 
	 * @param p_File 
	 * @param p_Flag
	 * @throws IOException Occurs when there is error while IO Processing
	 * @throws FileNotFoundException Occurs when there is no file in given file
	 */
	public static void execute(File[] p_File, String p_Flag) throws FileNotFoundException, IOException
	{	
		for(File fData : p_File)
		{			
			if(fData.isFile())
			{
				execute(fData, p_Flag);
			}
			else
			{
				execute(fData.listFiles(), p_Flag);
			}
		}
	}
}
