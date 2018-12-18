package Omap_Location;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

public class JPG_EXIF {
	
	public static void main(String[] args)
	{
		JFileChooser jfc =new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		jfc.showDialog(null, null);
		File f=jfc.getSelectedFile();
		new JPG_EXIF().readFolder(f);
	}
	
	
	public void readEXIF(File jpegFile)
	{
		String tag_info="";
		try {
			Metadata metadata=JpegMetadataReader.readMetadata(jpegFile);
			for(Directory directory:metadata.getDirectories())
			{
				for(Tag tag:directory.getTags())
				{
					tag_info+=tag.getDescription()+"\t";
						System.out.println(tag);
				}
			}
			
		} catch (JpegProcessingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		write(tag_info);
	}

	public void write(String taginfo)
	{
		try {
			FileWriter fw=new FileWriter("D:\\照片信息.xls",true);
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write(taginfo);
			bw.newLine();
			bw.flush();
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readFolder(File file)
	{
		if(file.isFile())
		{
			if(file.getName().endsWith("jpg")|file.getName().endsWith("jpeg"))
			{
				readEXIF(file);
			}
		}else if(file.isDirectory())
		{
			File[] path=file.listFiles();
			for(int i=0;i<path.length;i++)
			{
				if(path[i].isFile())
				{
					if(path[i].getName().endsWith("jpg")|path[i].getName().endsWith("jpeg"))
					{
						readEXIF(path[i]);
					}
				}else if(path[i].isDirectory())
				{
					readFolder(path[i]);
				}
			}
		}
	}
	
	
}
