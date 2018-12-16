package EXIF_Rewrite;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

public class JPG_EXIF {
	
	public static void main(String[] args)
	{
		File f=new File("C:\\Users\\周洋\\Desktop\\新洲污普\\新建文件夹\\IMG_20180911_110838.jpg");
		new JPG_EXIF().getEXIF(f);
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

	public List<EXIF_Elements> getEXIF(File jpegFile)
	{
		List<EXIF_Elements> list=new ArrayList<EXIF_Elements>();
		Metadata metadata=null;
		try {
			metadata=JpegMetadataReader.readMetadata(jpegFile);
			for(Directory directory:metadata.getDirectories())
			{
				EXIF_Elements elements=new EXIF_Elements();
				int index=directory.getTags().size();
				String[] tag_info=new String[index];
				index=0;
				System.out.print("public void set"+directory.getName()+"Values(");
				for(Tag tag:directory.getTags())
				{
					tag_info[index]=tag.getDescription();
					index++;
					System.out.print("String  "+tag.getTagName()+"  ,");
				}
				
				if(directory.getName().contains("File")){
					elements.setFileValues(FileName, FileSize, FileModifiedDate);
				}
				if(directory.getName().contains("Exif IFD0")){
					
				}
				if(directory.getName().contains("SubIFD")){
					
				}
				if(directory.getName().contains("GPS")){
					
				}
				if(directory.getName().contains("JPEG")){
					
				}
				if(directory.getName().contains("Interoperability")){
					
				}
				if(directory.getName().contains("Thumbnail")){
					
				}
				
				System.out.print("){\n\n}\n\n\n");
			}
		} catch (JpegProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
}
