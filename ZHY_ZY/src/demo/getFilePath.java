package demo;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class getFilePath {
	
	public File getFile()
	{
		JFileChooser jfc=new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jfc.showDialog(new JLabel(),"打开");
		File file=jfc.getSelectedFile();
		return file;
	}
	public String getFileString()
	{
		return getFile().toString();
	}
	
	public File getFolder()
	{
		JFileChooser jfc=new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jfc.showDialog(new JLabel(),"打开");
		File file=jfc.getSelectedFile();
		return file;
	}
	public String getFolderString()
	{
		return getFolder().toString();
	}
	
}
