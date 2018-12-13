package Omap_Location;

import java.io.File;
import java.io.IOException;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

public class JPG_EXIF {
	
	public static void main(String[] args)
	{
		File f=new File("E:\\新洲污普\\新建文件夹\\IMG_20180911_110902.jpg");
		new JPG_EXIF().readEXIF(f);
	}
	
	
	public void readEXIF(File jpegFile)
	{
		try {
			Metadata metadata=JpegMetadataReader.readMetadata(jpegFile);
			for(Directory directory:metadata.getDirectories())
			{
				for(Tag tag:directory.getTags())
				{
						System.out.println(tag);
				}
			}
			
		} catch (JpegProcessingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
