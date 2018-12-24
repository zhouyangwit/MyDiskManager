package DiskManager;

import java.io.File;
import java.io.IOException;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

public class Image {
	
	
	public String readEXIF(File jpegFile)
	{
		String EXIF=null;
		try {
			Metadata metadata=JpegMetadataReader.readMetadata(jpegFile);
			EXIF="";
			for(Directory directory:metadata.getDirectories())
			{
				
				for(Tag tag:directory.getTags())
				{
					EXIF+=tag.toString();
				}
			}
			
		} catch (JpegProcessingException | IOException e) {
			e.printStackTrace();
		}
		return EXIF;
	}

}
