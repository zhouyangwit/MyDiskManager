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
		File f=new File("C:\\Users\\周洋\\Desktop\\IMG_20181217_145646.jpg");
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
						System.out.println(tag.getDirectoryName()+"     "+tag.getTagName()+"     "+tag.getDescription());
				}
				System.out.println();
			}
			
		} catch (JpegProcessingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getEXIF(File jpegFile)
	{
	//	List<EXIF_Elements> list=new ArrayList<EXIF_Elements>();
		Metadata metadata=null;
		EXIF_Elements elements=new EXIF_Elements();
		try {
			metadata=JpegMetadataReader.readMetadata(jpegFile);
			for(Directory directory:metadata.getDirectories())
			{
				int index=directory.getTags().size();
	//			System.out.println(directory.getName()+"\tindex="+index);
				String[] tag_info=new String[index];
				index=0;
	//			System.out.print("public void set"+directory.getName()+"Values(");
				for(Tag tag:directory.getTags())
				{
					if(tag.getTagName().equals("File Modified Date"))
					{
						System.out.println(tag.getDirectoryName()+"\ttag_info["+index+"]");
						tag_info[index]="File Modified Date :"+tag.getDescription();
					}
					if(tag.getTagName().equals("Date/Time"))
					{
						System.out.println(tag.getDirectoryName()+"\ttag_info["+index+"]");
						tag_info[index]="Date/Time :"+tag.getDescription();
					}
					if(tag.getTagName().equals("Date/Time Original"))
					{
						System.out.println(tag.getDirectoryName()+"\ttag_info["+index+"]");
						tag_info[index]="Date/Time Original :"+tag.getDescription();
					}
					if(tag.getTagName().equals("Date/Time Digitized"))
					{
						System.out.println(tag.getDirectoryName()+"\ttag_info["+index+"]");
						tag_info[index]="Date/Time Digitized :"+tag.getDescription();
					}
					if(tag.getTagName().contains("GPS Time-Stamp"))
					{
						System.out.println(tag.getDirectoryName()+"\ttag_info["+index+"]");
						tag_info[index]="GPS Time-Stamp :"+tag.getDescription();
					}
					if(tag.getTagName().contains("GPS Date Stamp"))
					{
						System.out.println(tag.getDirectoryName()+"\ttag_info["+index+"]");
						tag_info[index]="GPS Date Stamp :"+tag.getDescription();
					}
					index++;
	//				System.out.print("String  "+tag.getTagName()+"  ,");
				}
				
				if(directory.getName().contains("File")){
					elements.setFileValues(tag_info);
				}
				if(directory.getName().contains("Exif IFD0")){
					elements.setExifIFD0Values(tag_info);;
				}
				if(directory.getName().contains("SubIFD")){
					elements.setExifSubIFDValues(tag_info);
				}
				if(directory.getName().contains("GPS")){
					elements.setGPSValues(tag_info);
				}
				if(directory.getName().contains("JPEG")){
				//	elements.setJPEGValues(tag_info);
				}
				if(directory.getName().contains("Interoperability")){
				//	elements.setInteroperabilityValues(tag_info);
				}
				if(directory.getName().contains("Thumbnail")){
				//	elements.setExifThumbnailValues(tag_info);
				}
		//		System.out.print("){\n\n}\n\n\n");
			}
		} catch (JpegProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(elements.DateTime);
	//	return null;
	}
	
	public void readFile(File file)
	{
		if(file.isDirectory())
		{
			File[] f=file.listFiles();
			for(int index=0;index<f.length;index++)
			{
				if(f[index].isFile())
				{
					if(f[index].getName().endsWith(".jpg")|f[index].getName().endsWith(".jpeg"))
					{
						getEXIF(f[index]);
					}
				}
				else if(f[index].isDirectory())
				{
					
				}
			}
		}
	}
	
}