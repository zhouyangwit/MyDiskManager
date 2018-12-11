package DiskManager_5;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

import com.drew.imaging.ImageMetadataReader;  
import com.drew.imaging.ImageProcessingException;  
import com.drew.metadata.Directory;  
import com.drew.metadata.Metadata;  
import com.drew.metadata.Tag;  

public class getImageInfomation {
	public static File getFile()
	{
		JFileChooser jfc=new JFileChooser("G:/");
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		jfc.showDialog(new JLabel(),"´ò¿ª");
		return jfc.getSelectedFile();
	}
	
	 public static void main(String[] args) {  
			File jpegFile=getFile();
			try {  
	            Metadata metadata = ImageMetadataReader.readMetadata(jpegFile);  
	            for (Directory directory : metadata.getDirectories()) {  
	                for (Tag tag : directory.getTags()) {  
	                    System.out.println(tag);  
	                }  
	            }  
	        } catch (ImageProcessingException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	 }
	 
}
	
	
